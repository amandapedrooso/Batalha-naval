# 🚢 Batalha Naval em Java

Este projeto consiste em uma implementação do jogo **Batalha Naval**, desenvolvida em **Java**, com execução via terminal.  
O objetivo principal foi aplicar e consolidar fundamentos de **Lógica de Programação** e **programação estruturada**, com foco em clareza, organização e legibilidade do código.

Projeto desenvolvido como atividade acadêmica em **dezembro de 2025**.

---

## 💡 O que este projeto demonstra

Este repositório evidencia competências fundamentais para desenvolvimento de software, como:

- Raciocínio lógico e resolução de problemas
- Organização de código em métodos reutilizáveis
- Manipulação de matrizes bidimensionais
- Controle de fluxo (condições e laços)
- Validação de entradas do usuário
- Separação de responsabilidades dentro do código
- Escrita de código legível e bem estruturado

---

## ⚙️ Funcionalidades

- Tabuleiro 10x10
- Posicionamento manual de navios
- Suporte a múltiplas orientações:
  - Horizontal
  - Vertical
  - Diagonal principal
  - Diagonal secundária
- Sistema de ataques com controle de tentativas
- Verificação automática de fim de jogo
- Interface textual organizada no console

---

## 🚢 Configuração dos navios

Os navios possuem tamanhos variados, permitindo maior complexidade lógica no posicionamento:

- 1 navio de tamanho 4  
- 1 navio de tamanho 3  
- 3 navios de tamanho 2  
- 3 navios de tamanho 1  

---

## 🧱 Representação no tabuleiro

| Símbolo | Descrição |
|-------|----------|
| `~` | Água |
| `N` | Navio |
| `O` | Navio atingido |
| `X` | Ataque sem sucesso |

---

## ▶️ Como executar o projeto

1. Tenha o **Java JDK** instalado
2. Compile o código:
   ```bash
   javac BatalhaNaval.java
