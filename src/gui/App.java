package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class App extends JFrame {
  public App() {
    // Criar a janela principal e define informações importantes
    setTitle("Blackjack");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar ao clicar no X
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    //Configuração padrão
      gbc.insets = new Insets(10, 10, 10, 10); //Margens
      gbc.fill = GridBagConstraints.HORIZONTAL; //Preenche o tamanho dos elementos horizontalmente

    //Fontes  
      Font importantFont = new Font("Trajan pro", Font.BOLD, 24);

    //Cores
      Color verdeEscuro = new Color(27, 77, 31);
      Color dourado = new Color(255, 215, 0);
      Color vermelho = new Color(209, 0, 0);

    //Declaração dos elementos
      //Label's
        JLabel titulo = new JLabel("Bem vindo ao Blackjack!", SwingConstants.CENTER);

      //Botões
        //Inicias
          JButton btnJogar = new JButton("Jogar");
          JButton btnSair = new JButton("Sair");

          //Botões nvolvendo dinheiro
          JButton btnBanca = new JButton("Inserir");
          JButton btnAposta = new JButton("Apostar");

          //Botões de rodada
          JButton btnHint = new JButton("Hint");
          JButton btnDouble = new JButton("Dobrar");
          JButton btnHold = new JButton("Parar");
          JButton btnDesistir = new JButton("Desistir");

    //Ação dos botões


    //Caracteristicas dos elementos
      getContentPane().setBackground(verdeEscuro);

      //Titulo
        titulo.setFont(importantFont);
        titulo.setForeground(Color.WHITE);
      //Botões
        btnJogar.setBackground(dourado);
        btnSair.setBackground(vermelho);

    //Posicionamento
      //Titulo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

      // Botão Jogar
        gbc.gridy = 1; 
        gbc.gridwidth = 1; 
        add(btnJogar, gbc);

      // Botão Sair
        gbc.gridx = 1; 
        add(btnSair, gbc);

    //Finalização da janela
    pack(); //Envolve todos os componentes
    setLocationRelativeTo(null); //Centraliza
    setVisible(true); //Transforma a janela para visivel 
    }

    public static void main(String[] args) {
    // Chamar o método para criar a interface na thread de eventos
    SwingUtilities.invokeLater(App::new);
    }
}