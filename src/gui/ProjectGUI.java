package gui;

import javax.swing.*;

public class ProjectGUI {
  public static void main(String[] args) {
    // Chamar o método para criar a interface na thread de eventos
    SwingUtilities.invokeLater(() -> criarGUI());
    }
    
    private static void criarGUI() {
      // Criar a janela principal (JFrame)
      JFrame frame = new JFrame("Blackjack");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar ao clicar no X
      frame.setSize(800, 700); // Largura x Altura em pixels
      
      // Criar um painel para organizar os componentes
      JPanel panel = new JPanel();

      
      
      // Adicionar o painel à janela
      frame.add(panel);
      frame.setVisible(true);
    }
}