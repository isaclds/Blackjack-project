import java.util.*;

public class Project {
    //Declaração das principais variaveis do projeto e que seram acessadas em diversas das funções
    private static Baralho baralho;
    private static Scanner scan;
    private static double banca;
    private static double bancaComp;
    private static double aposta;
    private static boolean continuarJogando = true;
    private static int pontosJogador;
    private static int pontosDealer;

    //Tudo feito em funções para melhor manutenção do jogo e diminuir possiveis confusões
    public static void main(String[] args) {
        inicializarJogo();
        loopPrincipal();
        fimDeJogo();
    }

    //Função de iniciar o jogo, declarando o baralho como um novo objeto da classe baralho e o mesmo do scanner, enquanto já embaralha e uma mensagem de boas vindas ao jogador
    private static void inicializarJogo(){
        scan = new Scanner(System.in);
        System.out.println("Bem vindo ao Blackjack!");
    }

    //Principais funcionalidades do jogo nesse lop para permitir que o jogo tenha diversas rodas
    private static void loopPrincipal(){
        
            System.out.println("Você quer jogar? Selecione a opção desejada.\n 1 - Sim\n 2 - Não");
            int escolha = scan.nextInt();

            //Switch case para iniciar o loop principal
            switch (escolha) {
            case 1 ->{ 
              System.out.println("Insira o valor da sua banca!");
              banca = scan.nextDouble();
              bancaComp = banca;
              //Embaralhar o baralho
                while(continuarJogando){
                    rodada();
                    }
                 }
            case 2 -> {
                System.out.println("Volte sempre!");
            }
            default ->{
                System.out.println("Não foi possível encontrar essa opção. Tente novamente!");
                loopPrincipal();
            }
        }
    }

     private static void fimDeJogo() {

         System.out.println("Volte sempre!");
         if(banca>bancaComp){
            System.out.printf("Você ganhou R$%.2f!\n", banca-bancaComp);
         }
    }

    private static void rodada(){
        //Cria um baralho novo a cada rodada e o embaralha
        baralho = new Baralho();
        baralho.embaralhar();
        List<Carta> maoJogador = new ArrayList<>();
        List<Carta> maoDealer = new ArrayList<>();

        //Valor da aposta sempre setado em 0 no inicio da função para evitar futuros bugs de apostas não zeradas
        aposta = 0;
        aposta = apostar();
        //Ao apostar o valor da aposta já é retirado da banca
        banca -= aposta;

        //Comprar carta de forma única para que na parte de decisão eu posso apenas reutilizar essa função
        comprarCarta(maoDealer);
        comprarCarta(maoJogador);
        comprarCarta(maoDealer);
        comprarCarta(maoJogador);

        //Pega a quantidade de pontos do jogador e do Dealer
        pontosJogador = calculadoraPontos(maoJogador);
        pontosDealer = calculadoraPontos(maoDealer);

        //Mostra as cartas do jogador e fala seus pontos
        System.out.printf("Sua mão é %s e %s, você tem %d pontos.\n", maoJogador.get(0), maoJogador.get(1), pontosJogador);
        // Mostra as cartas do Dealer
        System.out.printf("A mão do dealer é: %s e %s, ele tem %d pontos.\n", maoDealer.get(0), maoDealer.get(1), pontosDealer);

        //Verifica se alguém ganhou com um Blackjack
        verificarVencedorPorVU(pontosJogador, pontosDealer);

        decisaoRodada(maoJogador, maoDealer);

    }

    private static double apostar(){
        boolean apostaValida = false;
        
        //Lop para checagem se a aposta é válida ou não, reutilizado durante o projeto
        while (!apostaValida) {
            System.out.println("Quanto deseja apostar?");
            aposta = scan.nextDouble();
            
            if (aposta > banca) {
                System.out.printf("Saldo insuficiente! Banca atual: R$%.2f\n", banca);
            } else if (aposta <= 0) {
                System.out.println("Valor deve ser positivo!");
            } else {
                System.out.println("Bom jogo!");
                apostaValida = true;
            }
        }
        return aposta;
    }

    // private static void distribuirCartas(List<Carta> maoJogador, List<Carta> maoDealer){
    //     maoJogador.add(baralho.selecionarCarta());
    //     maoDealer.add(baralho.selecionarCarta());
    //     maoJogador.add(baralho.selecionarCarta());
    //     maoDealer.add(baralho.selecionarCarta());
    // }

     private static void comprarCarta(List<Carta> mao){
        mao.add(baralho.selecionarCarta());
    }

    private static int calculadoraPontos(List<Carta> mao){
        int pontos = 0;

        //Classe map para atribuir valor as cartas que são letras
        Map<String, Integer> valorCartaLetra = new HashMap<>();
        valorCartaLetra.put("J", 10);
        valorCartaLetra.put("Q", 10);
        valorCartaLetra.put("K", 10);
        valorCartaLetra.put("A", 11);

        //Lop para executar a soma, pega a carta da classe carta
        for(Carta carta : mao){

            //Pega o valor da carta em forma de String
            String valorCarta = carta.getValor();

            //Testa com contaisKey para ver se ela tem o Map junto, se tem ela pega o valor que foi mapeado
            if(valorCartaLetra.containsKey(valorCarta)){
                pontos += valorCartaLetra.get(valorCarta);
            } else {
                pontos += Integer.parseInt(valorCarta);
            }
        }

        if(pontos>21){
            //Filtra para ver se tem algum As presente
            int qntdAs = (int) mao.stream().filter(c -> "A".equals(c.getValor())).count();

            while(pontos>21 && qntdAs > 0){
                pontos -= 10; //Diminui o valor de As para 1 por conta que estourou a quantidade de pontos
                qntdAs --; //Corrige a quantidade de As
            }
        }
        //Retorna os pontos
        return pontos;
    }

    private static void verificarVencedorPorVU(int pontosJogador, int pontosDealer){
        //Ver se empatou em 21, caso sim valor da aposta volta para a banca
        if((pontosJogador == 21) && (pontosDealer == 21)){
            empate();
        } else if(pontosJogador == 21){
            //Jogador tirou um blackjack e verifica se ele deseja continuar jogando
            System.out.println("Parabéns! Você tem um Blackjack!");
            banca = banca +(aposta * 2) + ( aposta * 3/2);
            continuarJogo();

        } else if(pontosDealer == 21){
            //Dealer tem um blackjack, jogador perdeu
            System.out.println("O Dealer tem um Blackjack! Que azar...");
            continuarJogo();

        }
    }

    private static void continuarJogo(){
         System.out.println("Deseja continuar jogando? \n 1 - Sim\n 2 - Não");
            int escolha = scan.nextInt();
            switch(escolha){
                case 1 -> {
                System.out.printf("Sua banca atual é de R$: %.2f!\n", banca);
            }
            case 2 -> {
                continuarJogando = false;
            }
            default -> {
                System.out.println("Opção inválida! Tente novamente.");
                continuarJogo();
            }
            }
    }

    private static void decisaoRodada(List<Carta> maoJogador, List<Carta> maoDealer){
        //Verificar se o dealer já tem 17 pontos, se ele não tiver ele é obrigado a chegar no minimo a 17
        //Preciso mexer nesse loop por conta dq o dealer pode comprar mtas cartas e estourar antes do jogador
        while(pontosDealer < 17){
            comprarCarta(maoDealer);
            pontosDealer = calculadoraPontos(maoDealer);
        }

        System.out.println("Qual dessas opções você deseja realizar?:\n 1 - Comprar mais uma carta.\n 2 - Dobrar a aposta(comprando apenas mais uma carta).\n 3 - Parar.\n 4- Desistir(perde metade da aposta).");
        int escolha = scan.nextInt();
        switch(escolha){
            case 1 ->{
                comprarCarta(maoJogador);
                pontosJogador = calculadoraPontos(maoJogador);
                //Mostrar a ultima carta que o jogador tirou
                int index = maoJogador.size() - 1;
                System.out.printf("Sua mão agora tem %s\n", maoJogador.get(index));
                comparadorDecisor();
            }
        }
        //Mais cartas

        //Desistir

        //Dobrar

        //Parar

    }

    //Função comparadora e verificar possivel vencedor
    private static void comparadorDecisor() {
        //Ambos estouram
        if(pontosJogador>21 & pontosDealer>21){
            System.out.println("Ambos estouraram 21 pontos! O Dealer ganha por padrão.");
            continuarJogo();
        } else if(pontosJogador > 21){ //Jogador estoura
            System.out.println("Você estourou a quantidade de pontos!");
            continuarJogo();
        } else if(pontosDealer > 21) { //Dealer estoura
            System.out.println("O Dealer estourou a quantidade de pontos! Você ganhou!");
            banca = banca + (aposta * 2);
            continuarJogo();
        }  else if (pontosJogador == pontosDealer) { //Empate
            empate();
        } else if (pontosJogador>pontosDealer) { //Jogador mais pontos que o dealer
            System.out.printf("Você ganhou! Você fez %d pontos e o Dealer fez %d pontos.", pontosJogador, pontosDealer);
            banca = banca + (aposta * 2);
            continuarJogo();
        } else { // Dealer fez mais pontos
            System.out.printf("O Dealer ganhou. Você fez %d pontos e o Dealer fez %d pontos.", pontosJogador, pontosDealer);
            continuarJogo(); 
        }
    }

//Situações de pontos

    private static void empate(){
        //Zera as apostas e retorna o valor para banca
        banca += aposta;

        System.out.println("Houve um empate! As apostas foram zeradas.");
        //Verifica se o jogador deseja continuar jogando
         continuarJogo();
    }

    // if(pontosDealer>pontosJogador){
            
    //         System.out.println("O Dealer ganhou. Mais sorte da próxima vez!");
    //         continuarJogo();

    //     } else if(pontosDealer<pontosJogador){
    //         System.out.println("Você ganhou! Parabéns!");
    //         banca = banca + (aposta * 2);
    //         continuarJogo();

    //     }

    //Melhorar o verificar vencedor, para situações que ninguem tenha atingido 21, esse tipo de coisa
    //Lembrar dq o dealer sempre ira trazer mais cartas até que ele some 17 pontos. As coisas tem que acontecer ao mesmo tempo
}