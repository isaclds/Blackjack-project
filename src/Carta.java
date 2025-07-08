public class Carta {
// Declara os atributos da classe Carta, que são o valor e o naipe da carta
    private final String valor;
    private final String naipe;

    //Função para criar a carta, valor e naipe como parametros
    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public String getValor() {
        return valor;
    }

     public String getNaipe() {
        return naipe;
    }

    // Retorna o valor e o naipe da carta em uma String
    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}
