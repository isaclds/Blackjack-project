# Blackjack (21) - Java Terminal game

Jogo de **Blackjack** desenvolvido em **Java**, utilizando progamação orientada a objeto (POO) e lógica de jogo interativa no terminal para facilitar a interação. Projeto produzido durante a 1° fase do curso de Análise e Desenvolvimento de Sistemas no IFSC, para a matéria de Pensamento Computacional e Algoritmos, sob orientação do Doutor e Professor [Ramon Mayor Martins](https://github.com/rmayormartins).

## Regras do Jogo

As regras utilizadas nesse projeto foram adptadas a partir da explicação disponível nesse [site](https://pt.wikipedia.org/wiki/Blackjack). Se você não está familiarizado(a) com o jogo ou quer entender melhor as mecânicas, recomendo acessar o link para conferir as regras detalhadas antes de começar!
> [!NOTE]
> Este projeto é uma implementação pessoal com base nas regras disponíveis online. Qualquer dúvida sobre as regras oficiais, consulte a fonte original.

## Funcionalidades

- **Sistema de Apostas** - Gerencie suas fichas e aposte contra o dealer.
- **Regras Clássicas do Blackjack** - Hit, stand, double down, surrender.
- **Contagem automática dos pontos** - Ás vale 1 ou 11 conforme a jogada.
- **Lógica do Dealer** - Dealer compra cartas até atingir 17+ pontos.
- **Interface simples no terminal** - Sem depedências externas(futuramente implementarei uma interface gráfica).

## Tecnologias e Conceitos Utilizados

- **Java(JDK 14+)**
- **Progamação Orientada a Objetos** - Classes `Baralho`, `Carta`, `Project`, encapsulamento.
- **Collections** - Uso de `ArrayList` para gerenciamento do baralho, mão do jogador e do dealer, `HashMap` para setar valores númericos a J, Q, K, A.
- **Terminal Interativo** - Entrada/saída via `Scanner` e `System.out`.
- **Lógica do jogo** - Calculo de pontos, lógica do dealer, fluxo de controle com switch-case e loops para gerenciar as rodadas.

## Como executar

1. Certifique-se de ter o Java instalado (JDK 14+)

```
java --version
```

2. Clone o repositório

```
git clone https://github.com/isaclds/Blackjack-project.git
cd Blackjack-project
```

3. Escolha uma das opções a baixo para executar:

- Compilar e executar manualmente (garantindo que esteja usando UTF-8)

```
javac -encoding UTF-8 -d bin src\*.java
java -cp bin Project
```

- Usar o script run.sh (Linux/macOS)

```
cd script
chmod +x run.sh  # Dar permissão de execução
./run.sh
```

- Usar o arquivo run.bat (Windows)

```
cd script
./run.sh
```
