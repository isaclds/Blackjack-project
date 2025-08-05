package logic;

import java.util.*;

public class Project {
    //Declaração das principais variaveis do projeto e que seram acessadas em diversas das funções
    private Baralho baralho;
    private Scanner scan;
    private double banca;
    private double bancaComp;
    private double aposta;
    private boolean blackjack;
    private boolean dobrou;
    private List<Carta> maoJogador;
    private List<Carta> maoDealer;
    private int pontosJogador;
    private int pontosDealer;
    private int contador;
   

    public void definirBanca(double banca) {
        this.banca = banca;
    }

    public double getBanca() {
        return banca;
    }

    public void definirAposta(double aposta) {
        this.aposta = aposta;
    }

    public double getAposta() {
        return aposta;
    }

    public void iniciarJogo() {
        baralho = new Baralho();
        iniciarRodada();
    }

    public boolean vrdOUfalso() {
        contador = 1 + contador;
        return contador == 1;
    }

     public void iniciarRodada() {
        //Jogador não fez um blackjack
        blackjack = false;

         //Criação das mãos
        maoJogador = new ArrayList<>();
        maoDealer = new ArrayList<>();

        criarBaralho();
        criarMaos();
    }

    public List<Carta> getMaoJogador(){
        return maoJogador;
    }

    public List<Carta> getMaoDealer(){
        return maoDealer;
    }

    public void criarMaos() {
        comprarCarta(maoDealer);
        comprarCarta(maoJogador);
        comprarCarta(maoDealer);
        comprarCarta(maoJogador);
    }

    public void comprarCarta(List<Carta> mao) {
        mao.add(baralho.selecionarCarta());
    }
    
    private void criarBaralho() {
        // Quantidade de cartas de um baralho de blackjack com 6 baralhos 
        int tamanhoBaralhoInicial = 312;

        //Verifica a quantidade de cartas presentes no baralho para criação do mesmo ou embaralha-lo novamente
        if(baralho.quantidadeDeCartas() == 0){
            fazerBaralho();
        } else if(baralho.quantidadeDeCartas() < tamanhoBaralhoInicial/2) {
            baralho.limparBaralho();
            fazerBaralho();
        }
    }

    private void fazerBaralho() {
         //Criar 6 baralhos como seria em um cassino, blackjack com 6 ou 8 baralhos
        for (int i = 0; i < 6; i++) {
            baralho.criarBaralho();
        }
        baralho.embaralhar();
    }

    private void perdeu(){
        banca -= aposta;
    }

    private void ganhou(){
        if(blackjack){
            banca = banca + aposta + ( aposta * 3/2);
        } else {
            banca += aposta;
        }
    }

    public int calculadoraPontos(List<Carta> mao){
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
}


//     private  void loopPrincipal(){
//         System.out.println("Você quer jogar? Selecione a opção desejada.\n 1 - Sim\n 2 - Não");
//         int escolha = scan.nextInt();
        
//         //Loop while para evitar recursividade
//         boolean escolhaValida = false;
//         while(!escolhaValida) {
//                     //Switch case para iniciar o loop principal
//                     switch (escolha) {
//                     case 1 ->{ 
//                         //Escolha é válida
//                         escolhaValida = true;
//                         //Verficar se a banca é válida 
//                         boolean bancaValida = false;
//                         //Cria o baralho
//                         baralho = new Baralho();
//                         while(!bancaValida) {
//                             System.out.println("Insira o valor da sua banca!");
//                             banca = scan.nextDouble();
//                             bancaComp = banca;
//                             if(banca<=0){
//                                 System.out.println("O valor da banca deve ser positivo!");
//                             } else {
//                                 bancaValida = true;
//                             }
//                         }
//                         continuarJogando = true;
//                         //Enquanto o jogador quiser continuar jogando, o jogo roda
//                         while(continuarJogando){
//                             rodada();
//                             }
//                          }
//                     case 2 -> {
//                         continuarJogando = false;
//                     }
//                     default ->{
//                         System.out.println("Não foi possível encontrar essa opção. Tente novamente!");
//                     }
//             } 
//         }
//     }

//     private static void rodada(){
//         //Verificando se a banca é diferente de zero para iniciar rodada
//         if(banca <= 0){
//             continuarJogo();
//             return;
//         }

//         //Quantidade de cartas de um baralho de blackjack com 6 baralhos 
//         int tamanhoBaralhoInicial = 312;

//         //Verifica a quantidade de cartas presentes no baralho para criação do mesmo ou embaralha-lo novamente
//         if(baralho.quantidadeDeCartas() == 0){
//             criarBaralhoGrande();
//         } else if(baralho.quantidadeDeCartas() < tamanhoBaralhoInicial/2) {
//             baralho.limparBaralho();
//             criarBaralhoGrande();
//             System.out.println("Todas as cartas voltaram ao baralho e ele foi embaralhado novamente!");
//         }
    
//         //Setando caracteristicas iniciais 
//         pontosDealer = 0;
//         pontosJogador = 0;
//         dobrou = false;

//         //Valor da aposta sempre setado em 0 no inicio da função para evitar futuros bugs de apostas não zeradas
//         aposta = 0;
//         aposta = apostar();

//         //Ao apostar o valor da aposta já é retirado da banca
//         banca -= aposta; 

//         //Pega a quantidade de pontos do jogador e do Dealer
//         pontosJogador = calculadoraPontos(maoJogador);
//         pontosDealer = calculadoraPontos(maoDealer);

//         //Mostra as cartas do jogador e fala seus pontos
//         System.out.printf("Sua mão é %s e %s, você tem %d pontos.\n", maoJogador.get(0), maoJogador.get(1), pontosJogador);
//         // Mostra as cartas do Dealer
//         System.out.printf("A mão do dealer é: %s e %s, ele tem %d pontos.\n", maoDealer.get(0), maoDealer.get(1), pontosDealer);

//         //Verifica se alguém ganhou com um Blackjack
//         verificarVencedorPorVU(pontosJogador, pontosDealer);

//         decisaoRodada(maoJogador, maoDealer);

//     }

//     private static void decisaoRodada(List<Carta> maoJogador, List<Carta> maoDealer){
//         //Loop para evitar recursividade, quando chegouDecisao = true finaliza essa função por definitivo
//         boolean chegouDecisao = false;
//         while(!chegouDecisao){
//             //Criação do menu por conta do dobrar que só pode aparecer na primeira vez
//             StringBuilder menu = new StringBuilder();
//             menu.append("Qual dessas opções você deseja realizar?\n");
//             menu.append("1 - Comprar mais uma carta.\n");
    
//             //Compara com o tamanho da mão do jogador, se ela tiver só duas cartas o que acarreta em ser a primeira vez que esteja funcionando aparece a opção dobrar
//             if(!dobrou && maoJogador.size() == 2){
//                 menu.append("2 - Dobrar a aposta(comprando apenas mais uma carta).\n");
//             }
//             menu.append("3 - Parar.\n");
//             menu.append("4 - Desistir(perde metade da aposta).");
    
//             System.out.println(menu.toString());
            
//             int escolha = scan.nextInt();
//             scan.nextLine(); //Limpa o buffer do scanner
//             switch(escolha){
//                 case 1 ->{
//                     //Verifica se o dealer já tem 17 pontos, se não ele compra uma carta e atualiza a contagem de pontos
//                     if(pontosDealer < 17){
//                         comprarCarta(maoDealer);
//                         pontosDealer = calculadoraPontos(maoDealer);
//                         //Mostra a carta que o dealer comprou
//                         int indexD = maoDealer.size() - 1;
//                         System.out.printf("O dealer agora tem %s, totalizando %d pontos\n", maoDealer.get(indexD), pontosDealer);
//                     }
//                     comprarCarta(maoJogador);
//                     pontosJogador = calculadoraPontos(maoJogador);
                    
//                     int indexJ = maoJogador.size() - 1;
//                     System.out.printf("Sua mão agora tem %s e você tem %d pontos\n", maoJogador.get(indexJ), pontosJogador);
    
//                     //Verifica se alguém estourou os pontos ou igualou 21 e puxa a função 
//                     if((pontosDealer >= 21) || (pontosJogador >= 21)){
//                         comparadorDecisor();
//                         chegouDecisao = true;
//                     }else if((pontosDealer>=17) && (pontosJogador>pontosDealer)){
//                         //Dealer não irá mais comprar cartas pq já tem 17 pontos ou mais e o jogador já tem mais pontos que ele, não faz sentido dar mais opções de continuidade
//                         comparadorDecisor();
//                         chegouDecisao = true;
//                     } else if(pontosJogador < 21 && pontosDealer < 21){
//                         //Se ninguém estourou os pontos, chama a função de decisão novamente
//                     }
//                 }
//                 case 2 ->{
//                     if(dobrou || maoJogador.size() != 2){
//                         System.out.println("Você só pode dobrar na primeira rodada!");
//                         //Chama a decisao novamente
//                     } else if(0 > (banca -= aposta)){
//                         System.out.printf("Você não pode dobrar, sua banca ficaria menor que zero!\n");
//                         //Forma para que o jogador não possa tentar dobrar novamente
//                         dobrou = true;
//                         //Chama a decisao novamente
//                     }
    
//                     dobrou = true;
//                     banca -= aposta; //Diminui o valor dobrado da aposta
//                     aposta *= 2; //Dobra a aposta
    
//                     //Verifica se o dealer já tem 17 pontos, se não ele compra uma carta e atualiza a contagem de pontos
//                     if(pontosDealer < 17){
//                         comprarCarta(maoDealer);
//                         pontosDealer = calculadoraPontos(maoDealer);
//                     }
    
//                     comprarCarta(maoJogador);
//                     pontosJogador = calculadoraPontos(maoJogador);
//                     int index = maoJogador.size() - 1;
//                     System.out.printf("Sua mão agora tem %s\n", maoJogador.get(index));
    
//                     //Dealer continua até ter 17 pontos ou mais
//                     while(pontosDealer < 17){
//                         comprarCarta(maoDealer);
//                         pontosDealer = calculadoraPontos(maoDealer);
//                     }
    
//                     comparadorDecisor();
//                     //Encerra a decisao
//                     chegouDecisao = true;
    
//                 }
//                 case 3 ->{
//                     while((pontosDealer < pontosJogador ) && (pontosDealer < 17)){
//                         comprarCarta(maoDealer);
//                         pontosDealer = calculadoraPontos(maoDealer);
//                     }
//                     System.out.println("Você decidiu parar!");
//                     comparadorDecisor();
//                     chegouDecisao = true;
//                 }
//                 case 4 ->{
//                     System.out.println("Você desistiu e recuperou metade da aposta!");
//                     banca += aposta / 2;
//                     continuarJogo();
//                     chegouDecisao = true;
//                 }
//                 default ->{
//                     System.out.println("Nenhuma das opções! Selecione alguma das opções disponiveis.");
//                      //Chama a decisao novamente
//                 }
//             }
//         }
//     }

//     //Função comparadora e verificar possivel vencedor
//     private static void comparadorDecisor() {
//         //Ambos estouram
//         if(pontosJogador>21 & pontosDealer>21){
//             System.out.println("Ambos estouraram 21 pontos! O Dealer ganha por padrão.");
//             continuarJogo();
//         } else if(pontosJogador > 21){ //Jogador estoura
//             System.out.println("Você estourou a quantidade de pontos!");
//             continuarJogo();
//         } else if(pontosDealer > 21) { //Dealer estoura
//             System.out.println("O Dealer estourou a quantidade de pontos! Você ganhou!");
//             banca = banca + (aposta * 2);
//             continuarJogo();
//         }  else if (pontosJogador == pontosDealer) { //Empate
//             empate();
//         } else if (pontosJogador>pontosDealer) { //Jogador mais pontos que o dealer
//             System.out.printf("Você ganhou! Você fez %d pontos e o Dealer fez %d pontos.", pontosJogador, pontosDealer);
//             banca = banca + (aposta * 2);
//             continuarJogo();
//         } else { // Dealer fez mais pontos
//             System.out.printf("O Dealer ganhou. Você fez %d pontos e o Dealer fez %d pontos.", pontosJogador, pontosDealer);
//             continuarJogo(); 
//         }
//     }

    

//     private static void verificarVencedorPorVU(int pontosJogador, int pontosDealer){
//         //Ver se empatou em 21, caso sim valor da aposta volta para a banca
//         if((pontosJogador == 21) && (pontosDealer == 21)){
//             empate();
//         } else if(pontosJogador == 21){
//             //Jogador tirou um blackjack e verifica se ele deseja continuar jogando
//             System.out.println("Parabéns! Você tem um Blackjack!");
//             banca = banca +(aposta * 2) + ( aposta * 3/2);
//             continuarJogo();

//         } else if(pontosDealer == 21){
//             //Dealer tem um blackjack, jogador perdeu
//             System.out.println("O Dealer tem um Blackjack! Que azar...");
//             continuarJogo();

//         }
//     }

//     private static void continuarJogo(){
//         //Loop while para evitar recursividade
//         boolean escolhaValida = false;
//         while(!escolhaValida) {
            
//             if(banca <= 0) {
//                 System.out.println("Sua banca está zerada! Quer continuar jogando? \n 1 - Sim \n 2 - Não");
//                 int choise = scan.nextInt();
//                 scan.nextLine();
//                 switch(choise){
//                     case 1 ->{
//                         //Caso a banca esteja zerada jogador pode inserir valor da nova banca
//                         System.out.println("Insira o valor da nova banca!");
//                         banca = scan.nextDouble();
//                         scan.nextLine();
//                         escolhaValida = true;
//                     } 
//                     case 2 ->{
//                         continuarJogando = false;
//                         escolhaValida = true;
//                     } 
//                     default ->{
//                         System.out.println("Opção inválida! Insira uma opção válida.");
//                         //Repete a função
//                     }
//                 }
//             } else {
//                 System.out.println("Deseja continuar jogando? \n 1 - Sim\n 2 - Não");
//                 int escolha = scan.nextInt();
//                 scan.nextLine();
//                 switch(escolha){
//                     case 1 -> {
//                         System.out.printf("Sua banca atual é de R$: %.2f!\n", banca);
//                         escolhaValida = true;
//                     }
//                     case 2 -> {
//                         continuarJogando = false;
//                         escolhaValida = true;
//                     }
//                     default -> {
//                         System.out.println("Opção inválida! Tente novamente.");
//                         //Repete a função
//                     }
//                 }
//             }
//         }
//     }

// //Situações de pontos

//     private static void empate(){
//         //Zera as apostas e retorna o valor para banca
//         banca += aposta;
//         System.out.println("Houve um empate! As apostas foram zeradas.");
//         //Verifica se o jogador deseja continuar jogando
//          continuarJogo();
//     }
