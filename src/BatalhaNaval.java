import java.util.Scanner;

public class BatalhaNaval {

    static Scanner scanner = new Scanner(System.in);

    //tabuleiro do criador
    static char[][] tabuleiroCriador = new char[10][10];
    //tabuleiro do atacante
    static char[][] tabuleiroAtacante = new char[10][10];


    public static void main(String[] args) {
        menuPrincipal();
    }


    public static void menuPrincipal() {
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("<<< MENU >>>");
            System.out.println("1- Iniciar jogo");
            System.out.println("2- Instruções");
            System.out.println("3- Sair");
            System.out.println("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    iniciarJogo();
                    break;
                case 2:
                    mostrarInstrucoes();
                    break;
                case 3:
                    System.out.println("Encerrando o jogo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }


    public static void iniciarJogo() {
        inicializarTabuleiro(tabuleiroCriador);
        inicializarTabuleiro(tabuleiroAtacante);

        System.out.println("Posicione seus navios:");

        posicionarTodosNavios();

        System.out.println("Todos navios posicionados!");

        iniciarAtaques();
    }




    public static void inicializarTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = '~';
            }
        }
    }


    public static void exibirTabuleiro(char[][] tabuleiro) {


        System.out.print("   ");
        for (int j = 0; j < tabuleiro[0].length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();


        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static boolean posicionarNavio(char[][] tabuleiro, int tamanho) {

        System.out.println("Selecione a linha: ");
        int linha = scanner.nextInt();
        System.out.println("Selecione a coluna: ");
        int coluna = scanner.nextInt();
        System.out.println("Selecione H = horizontal, V = vertical, P = diagonal principal ou S = diagonal secundaria: ");
        char orientacao = scanner.next().charAt(0);



        if (orientacao == 'H') {
            if (coluna + tamanho > tabuleiro[0].length) {
                System.out.println("Navio não cabe na horizontal!");
                return false;
            }
        } else if (orientacao == 'V') {
            if (linha + tamanho > tabuleiro.length) {
                System.out.println("Navio não cabe na vertical!");
                return false;
            }
        } else if (orientacao == 'P') {
            if (linha + tamanho > tabuleiro.length || coluna + tamanho > tabuleiro[0].length) {
                System.out.println("Navio não cabe na diagonal principal!");
                return false;
            }
        } else if (orientacao == 'S') {
            if (linha + tamanho > tabuleiro.length || coluna - (tamanho - 1) < 0) {
                System.out.println("Navio não cabe na diagonal secundaria!");
                return false;
            }
        } else {
            System.out.println("Orientação inválida!");
            return false;
        }



        if (orientacao == 'H') {
            for (int i = 0; i < tamanho; i++) {
                if (tabuleiro[linha][coluna + i] != '~') {
                    System.out.println("Já existe navio nessa posição!");
                    return false;
                }
            }
        } else if (orientacao == 'V') {
            for (int i = 0; i < tamanho; i++) {
                if (tabuleiro[linha + i][coluna] != '~') {
                    System.out.println("Já existe navio nessa posição!");
                    return false;
                }
            }
        } else if (orientacao == 'P') {
            for (int i = 0; i < tamanho; i++) {
                if (tabuleiro[linha + i][coluna + i] != '~') {
                    System.out.println("Já existe navio nessa posição!");
                    return false;
                }
            }
        } else {
            for (int i = 0; i < tamanho; i++) {
                if (tabuleiro[linha + i][coluna - i] != '~') {
                    System.out.println("Já existe navio nessa posição!");
                    return false;
                }
            }
        }



        if (orientacao == 'H') {
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[linha][coluna + i] = 'N';
            }
        } else if (orientacao == 'V') {
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[linha + i][coluna] = 'N';
            }
        } else if (orientacao == 'P') {
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[linha + i][coluna + i] = 'N';
            }
        } else { // S
            for (int i = 0; i < tamanho; i++) {
                tabuleiro[linha + i][coluna - i] = 'N';
            }
        }

        System.out.println("Navio posicionado com sucesso!!");
        return true;
    }

    public static void posicionarTodosNavios() {
        int[] tamanhos = {4, 3, 2, 2, 2, 1, 1, 1};

        for (int t : tamanhos) {
            boolean colocado = false;

            while (!colocado) {
                exibirTabuleiro(tabuleiroCriador);
                System.out.println("Posicione um navio de tamanho " + t);
                colocado = posicionarNavio(tabuleiroCriador, t);
            }
        }
    }

    public static void iniciarAtaques() {
        int tentativas = 15;

        while (tentativas > 0) {
            System.out.println("\nTENTATIVAS RESTANTES: " + tentativas);

            exibirTabuleiro(tabuleiroAtacante);

            System.out.print("Escolha a linha: ");
            int linha = scanner.nextInt();

            System.out.print("Escolha a coluna: ");
            int coluna = scanner.nextInt();

            int resultado = realizarAtaque(linha, coluna);

            if (resultado == 2) {
                System.out.println("Posição já atacada! Tente novamente.");
                continue;
            }

            if (resultado == 1) {
                System.out.println("ACERTOU!");
            } else {
                System.out.println("ERROU!");
            }

            if (verificarFimDeJogo()) {
                System.out.println("\nTodas as embarcações foram destruídas! Fim de jogo!");
                return;
            }

            tentativas--;
        }

        System.out.println("\nSuas tentativas acabaram! Fim de jogo!");
    }



    public static int realizarAtaque(int linha, int coluna) {


        if (tabuleiroCriador[linha][coluna] == 'X' || tabuleiroCriador[linha][coluna] == 'O') {
            System.out.println("Você já atacou essa posição!");
            return 2;
        }


        if (tabuleiroCriador[linha][coluna] == 'N') {
            System.out.println("VOCÊ ACERTOU!!");
            tabuleiroCriador[linha][coluna] = 'O';
            tabuleiroAtacante[linha][coluna] = 'O';
            return 1;
        }


        System.out.println("~~ ÁGUA ~~");
        tabuleiroCriador[linha][coluna] = 'X';
        tabuleiroAtacante[linha][coluna] = 'X';
        return 0;
    }


    public static boolean verificarFimDeJogo() {
        for (int i = 0; i < tabuleiroCriador.length; i++) {
            for (int j = 0; j < tabuleiroCriador[i].length; j++) {
                if (tabuleiroCriador[i][j] == 'N') {
                    return false; // ainda existe navio inteiro
                }
            }
        }
        return true;
    }


        public static void mostrarInstrucoes () {
            System.out.println("\nINSTRUÇÕES DO JOGO:");
            System.out.println("- Tabuleiro 10x10.");
            System.out.println("- O criador posiciona navios manualmente.");
            System.out.println("- O atacante tenta adivinhar posições.");
            System.out.println("- As direções devem ser escritas em MAIÚSCULO!");
            System.out.println("- O atacante tem 15 tentativas.");
            System.out.println("- O jogo termina quando todos navios forem destruídos ou acabar as tentativas.\n");
        }
    }

