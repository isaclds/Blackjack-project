public class Carta {
// Declara os atributos da classe Carta, que são o valor e o naipe da carta
    private String valor;
    private String naipe;

    //Função para criar a carta, valor e naipe como parametros
    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    // Retorna o valor e o naipe da carta em uma String
    public String toString() {
        return valor + " de " + naipe;
    }
}
