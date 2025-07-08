import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Baralho {
  //Lista que armazenará as cartas do baralho, armazena apenas objetos do tipo Carta, declara essa lista como "cartas"
  private final List<Carta> cartas;

  //Função que irá construir o baralho 
    public Baralho() {
      //Variavel cartas vira uma arraylist que irá armazenar as cartas do baralho
        cartas = new ArrayList<>();
        //Cria o bardistribuirCartaalho chamando a função criarBaralho
        criarBaralho();
    }

  private void criarBaralho() {
    //Cria um array para os valores e os naipes
    String[] naipes = {"ouros", "espadas", "copas", "paus"};
    String[] valores = {"2","3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    // Lop para que seja percorrido cada naipe e cada valor dentro do array o sinal ":" pode ser lido como "em" ou "para cada", criando assim uma carta de cada naipe e cada valor.
    for(String naipe : naipes) {
      for(String valor : valores) {
        cartas.add(new Carta(valor, naipe));
      }
    }
  }
public void mostrarCartas() {
    //Exibe as cartas do baralho, utilizando o método toString da classe Carta
    for (Carta carta : cartas) {
      System.out.println(carta);
    }
  }
  //Função para embaralhar as cartas do baralho, utilizando a função shuffle da classe Collections, shuffle reorganiza de forma aleatória os elementos de uma lista.	
  public void embaralhar() {
        Collections.shuffle(cartas);
    }

//Distribuir as cartas
  public Carta selecionarCarta() {
    if (cartas.isEmpty()) {
        throw new IllegalStateException("O baralho está vazio!");
    }
    //Remove a carta e mostra ela
    return cartas.remove(0); 
}

}