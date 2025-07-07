import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Project {
        public static void main(String[] args) {
            //Cria um novo baralho, chamando a classe baralho
            Baralho baralho = new Baralho(); 
            Scanner scan = new Scanner(System.in);
            baralho.embaralhar();

            //Inicio do jogo
            System.out.println("Bem vindo! Você quer jogar? Selecione a opção desejada.\n 1 - Sim\n 2 - Não");
            int escolha = scan.nextInt();

            //Switch para iniciar todo o projeto
            switch (escolha) {
                case 1:
                //Inserir o valor na qual o usuario utilizará para apostar
                    System.out.println("Insira o valor desejado para jogar.");
                    double banca = scan.nextDouble();
                //Embaralhar o baralho
                    baralho.embaralhar();
                //Criar o armazenamento das cartas futuras
                List<Carta> maoJogador = new ArrayList<>();
                List<Carta> maoDealer = new ArrayList<>();
                //Quanto o jogador deseja apostar e comparar com a banca inicial

                //Distribuir as cartas
                maoJogador.add(baralho.selecionarCarta());
                maoDealer.add(baralho.selecionarCarta());
                maoJogador.add(baralho.selecionarCarta());
                maoDealer.add(baralho.selecionarCarta());
                //Mostrar carta
                System.out.printf("Sua mão é %s e %s\n", maoJogador.get(0), maoJogador.get(1));
                //Mostra a primeira carta do dealer
                System.out.printf("A primeira carta do dealer é: %s\n", maoDealer);
            

                    break;
                case 2:
                System.out.println("Volte sempre!");
                default:
                    throw new Error("Não foi possível encontrar essa opção.");
            }
        }
    }
