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
                //Selecionar a carta do baralho 

                    break;
                case 2:
                System.out.println("Volte sempre!");
                default:
                    throw new Error("Não foi possível encontrar essa opção.");
            }
          
        }
    }
