import java.util.*;

public class Project {
    //Declaração das principais variaveis do projeto
    private static Baralho baralho;
    private static Scanner scan;
    private static double banca;
    private static boolean continuarJogando = true;

    //Tudo feito em funções para melhor manutenção do jogo e diminuir possiveis confunções
    public static void main(String[] args) {
        inicializarJogo();
        loopPrincipal();
    }

    //Função de iniciar o jogo, declarando o baralho como um novo objeto da classe baralho e o mesmo do scanner, enquanto já embaralha e uma mensagem de boas vindas ao jogador
    private static void inicializarJogo(){
        baralho = new Baralho();
        scan = new Scanner(System.in);
        System.out.println("Bem vindo ao Blackjack!");
    }

    //Principais funcionalidades do jogo nesse lop para permitir que o jogo tenha diversas rodas
    private static void loopPrincipal(){
        
            System.out.println("Você quer jogar? Selecione a opção desejada.\n 1 - Sim\n 2 - Não");
            int escolha = scan.nextInt();

            //Switch case para iniciar o loop principal
            switch (escolha) {
            case 1: 
              System.out.println("Insira o valor da sua banca!");
              banca = scan.nextDouble();
                while(continuarJogando){
                    rodada();
                    }
                break; 
            case 2:
                System.out.println("Volte sempre!");
                break;
                
            default:
                throw new Error("Não foi possível encontrar essa opção.");
        }
    }

    private static void rodada(){
        List<Carta> maoJogador = new ArrayList<>();
        List<Carta> maoDealer = new ArrayList<>();

        //Ao apostar o valor da aposta já é retirado da banca
        double aposta = apostar();
        banca -= aposta;

        distribuirCartas(maoJogador, maoDealer);

        //Mostra as cartas do jogador
        System.out.printf("Sua mão é %s e %s\n", maoJogador.get(0), maoJogador.get(1));
        // Mostra as cartas do Dealer
        System.out.printf("A mão do dealer é: %s e %s\n", maoDealer.get(0), maoDealer.get(1));

        //Pega a quantidade de pontos do jogador e do Dealer e vê se alguém ganhou e informa ao jogador, facilitar a vida dele
        int pontosJogador = calculadoraPontos(maoJogador);
        int pontosDealer = calculadoraPontos(maoDealer);
        System.out.printf("O dealer tem %d pontos e você tem %d pontos.\n", pontosDealer, pontosJogador);
        banca = verificarVencedor(pontosJogador, pontosDealer, banca, aposta);
        // aposta = 0;

        //Perguntar se o jogador quer mais cartas, desistir, dobrar, parar

    }

    private static double apostar(){
        boolean apostaValida = false;
        double aposta = 0;
        
        //Lop para checagem se a aposta é válida ou não, feito separado porque será reutilizado durante o projeto
        while (!apostaValida) {
            System.out.println("Quanto deseja apostar?");
            aposta = scan.nextDouble();
            
            if (aposta > banca) {
                System.out.printf("Saldo insuficiente! Banca atual: R$%.2f\n", banca);
            } else if (aposta <= 0) {
                System.out.println("Valor deve ser positivo!");
            } else {
                apostaValida = true;
            }
        }
        return aposta;
    }

    private static void distribuirCartas(List<Carta> maoJogador, List<Carta> maoDealer){
        baralho.embaralhar();
        maoJogador.add(baralho.selecionarCarta());
        maoDealer.add(baralho.selecionarCarta());
        maoJogador.add(baralho.selecionarCarta());
        maoDealer.add(baralho.selecionarCarta());
    }

    private static int calculadoraPontos(List<Carta> mao){
        int pontos = 0;

        //Classe map para atribuir valor as cartas que são letras
        Map<String, Integer> valorCartaLetra = new HashMap<String, Integer>();
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

    private static double verificarVencedor(int pontosJogador, int pontosDealer, double banca, double aposta){
        //Ver se empatou em 21, caso sim valor da aposta volta para a banca
        if((pontosJogador == 21) && (pontosDealer == 21)){

            //Zera as apostas e retorna o valor para banca
            banca += aposta;

            System.out.println("Houve um empate, apostas foram zeradas.");
            //Verifica se o jogador deseja continuar jogando
            continuarJogo();
        } else if(pontosJogador == 21){
            //Jogador tirou um blackjack e verifica se ele deseja continuar jogando
            System.out.println("Parabéns! Você tem um Blackjack!");
            banca = banca +(aposta * 3/2);
            continuarJogo();
        } else if(pontosDealer == 21){
            System.out.println("O Dealer tem um Blackjack! Que azar...");
            continuarJogo();
        }
    return banca;
    }

    private static void continuarJogo(){
         System.out.println("Deseja continuar jogando? \n 1 - Sim\n 2 - Não");
            int escolha = scan.nextInt();
            switch(escolha){
                case 1: 
                break; 
            case 2:
                System.out.println("Volte sempre!");
                continuarJogando = false;
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                continuarJogo();
            }
    }

    //Melhorar o verificar vencedor, para situações que ninguem tenha atingido 21, esse tipo de coisa
    //Dar continuidade as funcionalidades para o jogador receber mais uma carta, poder dobrar a aposta desistir, ou esperar para ver o que o dealer tem
    //Lembrar dq o dealer sempre ira trazer mais cartas até que ele some 17 pontos. As coisas tem que acontecer ao mesmo tempo
}