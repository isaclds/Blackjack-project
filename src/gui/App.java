package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class App extends JFrame {
  public App() {
    // Criar a janela principal e define informações importantes
    setTitle("Blackjack");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar ao clicar no X
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    //Configuração padrão
      gbc.insets = new Insets(5, 5, 0, 5); //Margens
      gbc.fill = GridBagConstraints.HORIZONTAL; //Preenche o tamanho dos elementos horizontalmente

    //Fontes  
      Font importantFont = new Font("Trajan pro", Font.BOLD, 24);

    //Cores
      Color verdeEscuro = new Color(27, 77, 31);
      Color dourado = new Color(255, 215, 0);
      Color vermelho = new Color(209, 0, 0);

    //Declaração dos elementos
      //Label
        JLabel titulo = new JLabel("Bem vindo ao Blackjack!", SwingConstants.CENTER);
        JLabel banca = new JLabel("Insira sua Banca:");

      //Text Field
        JTextField inBancaText = new JTextField();

      //Botões
        //Inicias
          JButton btnJogar = new JButton("Jogar"); //Feito
          JButton btnSair = new JButton("Sair"); //Feito

          //Botões envolvendo dinheiro
          JButton btnBanca = new JButton("Inserir"); //Fazer, quando pressionado o botão precisa pegar o valor inserido e armazenar dentro da variavel banca no project.java além de remover ele proprio e adicionar o botão aposta
          JButton btnAposta = new JButton("Apostar"); //Criar o textfield dele, quando pressionado armazenar o valor no aposta do project.java remover ele proprio e iniciar a rodada, displaying os botões das jogadas

          //Botões de rodada
          JButton btnHint = new JButton("Hint");
          JButton btnDouble = new JButton("Dobrar");
          JButton btnHold = new JButton("Parar");
          JButton btnDesistir = new JButton("Desistir");

    //Ação dos botões
    btnSair.addActionListener(e -> {
      //Fecha a janela e encerra os processos ao apertar no botão sair
      dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    });

    btnJogar.addActionListener(e -> {
      //Remove o botão jogar e sair
      remove(btnJogar);

      //Adiciona os novos elementos
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.weightx = 0;  //Não permite expandir horizontalmente
      gbc.anchor = GridBagConstraints.LINE_START; 
      add(banca, gbc);

      gbc.gridy = 2;
      add(btnBanca, gbc);
      gbc.gridy = 1;
      add(inBancaText, gbc);

      //Posicionamento do botão Sair modificadoo
      gbc.gridx = GridBagConstraints.RELATIVE;
      gbc.gridx = GridBagConstraints.RELATIVE;
      gbc.anchor = GridBagConstraints.LAST_LINE_END;
      add(btnSair, gbc);

      //Atualiza a tela
      revalidate();
      repaint();
    });

    btnBanca.addActionListener(e -> {
      //Tirar o botão da banca, adicionar o botão da aposta
      //Remove o proprio botão
      remove(btnBanca);

      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      gbc.weightx = 0;  //Não permite expandir horizontalmente
      gbc.anchor = GridBagConstraints.LINE_START; 
      add(btnAposta, gbc);
    });

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