package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;
import logic.Project;

public class App extends JFrame {
  public App() {
    // Criar a janela principal e define informações importantes
    setTitle("Blackjack");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar ao clicar no X
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    //Inicializar a classe projeto
    Project project = new Project();

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
        JLabel tituloLabel = new JLabel("Bem vindo ao Blackjack!", SwingConstants.CENTER);
        JLabel bancaLabel = new JLabel("Insira sua Banca:");
        JLabel apostaLabel = new JLabel("Insira sua aposta:");
        JLabel valorAtualBancaLabel = new JLabel();

      //Text Field
        JTextField inBancaText = new JTextField();
        JTextField inApostaText = new JTextField();

      //Botões
        //Inicias
          JButton btnJogar = new JButton("Jogar"); 
          JButton btnSair = new JButton("Sair"); 

          //Botões envolvendo dinheiro
          JButton btnBanca = new JButton("Inserir"); 
          JButton btnAposta = new JButton("Apostar"); 

          //Botões de rodada
          JButton btnHint = new JButton("Hint");
          JButton btnDouble = new JButton("Dobrar");
          JButton btnHold = new JButton("Parar");
          JButton btnDesistir = new JButton("Desistir");

    //Ação dos botões
      //Botão sair
      btnSair.addActionListener(e -> {
        //Fecha a janela e encerra os processos ao apertar no botão sair
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
      });

      //Botão Jogar
      btnJogar.addActionListener(e -> {
        //Remove o botão jogar 
        remove(btnJogar);
        remove(tituloLabel);

        //Adiciona os novos elementos
        //Banca
          gbc.gridx = 0;
          gbc.gridy = 1;
          gbc.anchor = GridBagConstraints.LINE_START;
          add(bancaLabel, gbc);

        //Banca textfield
          gbc.gridx = 1;
          add(inBancaText, gbc);

        //Btn banca
          gbc.gridx = 0;
          gbc.gridy = 2;
          add(btnBanca, gbc);
          

        //Btn sair
          gbc.gridx = 1;
          gbc.gridy = 2;
          gbc.anchor = GridBagConstraints.LAST_LINE_END;
          add(btnSair, gbc);

        //Atualiza a tela
        revalidate();
        repaint();
      });

      //Botão banca
      btnBanca.addActionListener(e -> {
        //Guarda o que foi inserido na textField
        String input = inBancaText.getText().trim();

        //Verificar se está vazio
        if(input.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Por favor insira um valor para a sua banca!");
          return;
        }

        //Try catch para apenas ser possivel inserir números
        try {
          //Guardar a banca como variavel
          double value = Double.parseDouble(input);
          //Verificar se o valor é positivo e se não é extremamente pequeno
          if(value<=1.0){
            JOptionPane.showMessageDialog(this, "Por favor insira um número maior que zero!");
            return;
          }
          //Guardar ela dentro do logic
          project.definirBanca(value);
          //Atualizar o valor mostrado na banca atual
          valorAtualBancaLabel.setText("Banca Atual:" + project.getBanca());
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this, "Por favor, insira um valor númerico válido!");
          return;
        }

        //Remove o proprio botão e o textField
        remove(btnBanca);
        remove(inBancaText);
        remove(bancaLabel);

        //Caracteristicas
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Adiciona margem

        // Linha 1: Botão de aposta e campos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(btnAposta, gbc);
        
        gbc.gridx = 1;
        add(apostaLabel, gbc);

        gbc.gridx = 2;
        add(inApostaText, gbc);

        // Linha 2: Banca atual e botão sair
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(valorAtualBancaLabel, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(btnSair, gbc);


        //Atualiza a tela
        revalidate();
        repaint();
      });

      //Botão aposta
      btnAposta.addActionListener(e -> {
        //Guarda o que foi inserido na textField
        String input = inApostaText.getText().trim();

        //Verificar se está vazio
        if(input.isEmpty()) {
          JOptionPane.showMessageDialog(this, "Por favor, insira um valor para a sua aposta!");
          return;
        }

        //Try catch para apenas ser possivel inserir números
        try {
          //Guardar a aposta como variavel
          double value = Double.parseDouble(input);
          //Verificar se o valor é positivo e se não é maior que a banca
          if(value < 0){
            JOptionPane.showMessageDialog(this, "Insira um número maior que zero!");
            return;
          } else if (value > project.getBanca()) {
            JOptionPane.showMessageDialog(this, "Insira uma aposta menor que a sua banca!");
            return;
          }
          //Guardar ela dentro do logic
          project.definirAposta(value);
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(this, "Por favor insira um valor númerico válido!");
          return;
        }
        project.iniciarRodada();

        //Remove os botões e adiciona os que tem que ser adicionados
        remove(btnAposta);
        remove(inApostaText);
        remove(bancaLabel);
        remove(apostaLabel);

        //Caracteristicas
        gbc.gridy = 3; // Posiciona na linha abaixo dos elementos existentes
        gbc.gridx = 0; // Começa na coluna 0
        gbc.gridwidth = 1; // Cada botão ocupa 1 célula

        // Adiciona os botões em sequência
        add(btnHint, gbc);
        gbc.gridx++; // Move para a próxima coluna (1)
        add(btnDouble, gbc);
        gbc.gridx++; // Move para a próxima coluna (2)
        add(btnHold, gbc);
        gbc.gridx++; // Move para a próxima coluna (3)
        add(btnDesistir, gbc);

        //Atualiza a tela
        revalidate();
        repaint();
      });

    //Caracteristicas dos elementos
      getContentPane().setBackground(verdeEscuro);

      //Titulo
        tituloLabel.setFont(importantFont);
        tituloLabel.setForeground(Color.WHITE);
      //Botões
        btnJogar.setBackground(dourado);
        btnSair.setBackground(vermelho);

    //Posicionamento
      //Titulo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(tituloLabel, gbc);

      // Botão Jogar
        gbc.gridy = 1; 
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.LINE_START;
        add(btnJogar, gbc);

      // Botão Sair
        gbc.gridx = 1; 
        gbc.anchor = GridBagConstraints.LINE_END; 
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