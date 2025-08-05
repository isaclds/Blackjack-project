package gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import logic.*;

public class App extends JFrame {
  private JLabel pontosJogadorLabel;
  private JLabel pontosDealerLabel;
  private JPanel maoDealerLabel;
  private JPanel maoJogadorLabel;

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
        pontosJogadorLabel = new JLabel("Pontos: 0");
        pontosDealerLabel = new JLabel("Dealer: 0");

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
        gbc.weighty = 1;
        add(inApostaText, gbc);

        // Linha 2: Banca atual e botão sair
        gbc.weighty = 0;
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
        boolean primeiraVez = project.vrdOUfalso();
        if(primeiraVez){
          project.iniciarJogo();
        }
        else {
          project.iniciarRodada();
        }
    
        // Remove componentes antigos
        remove(btnAposta);
        remove(inApostaText);
        remove(apostaLabel);
        remove(valorAtualBancaLabel);
        remove(maoDealerLabel);
        remove(maoJogadorLabel);
        remove(pontosDealerLabel);
        remove(pontosJogadorLabel);

        // Cria novos painéis
        maoDealerLabel = criarPainelCartas(project.getMaoDealer());
        maoJogadorLabel = criarPainelCartas(project.getMaoJogador());
        
        // Atualiza pontos
        pontosJogadorLabel.setText("Pontos: " + project.calculadoraPontos(project.getMaoJogador()));
        pontosDealerLabel.setText("Dealer: ?");
        
        // Configura labels
        maoDealerLabel.setForeground(Color.WHITE);
        maoJogadorLabel.setForeground(Color.WHITE);
        pontosJogadorLabel.setForeground(Color.WHITE);
        pontosDealerLabel.setForeground(Color.WHITE);

        // Posiciona componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(maoDealerLabel, gbc);
        
        gbc.gridy = 1;
        add(maoDealerLabel, gbc);
        
        gbc.gridy = 2;
        add(pontosDealerLabel, gbc);
        
        gbc.gridy = 3;
        add(maoJogadorLabel, gbc);
        
        gbc.gridy = 4;
        add(maoJogadorLabel, gbc);
        
        gbc.gridy = 5;
        add(pontosJogadorLabel, gbc);
        
        // Botões de ação
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(btnHint, gbc);
        
        gbc.gridx = 1;
        add(btnDouble, gbc);
        
        gbc.gridx = 2;
        add(btnHold, gbc);
        
        gbc.gridx = 3;
        add(btnDesistir, gbc);

        revalidate();
        repaint();
      });

    getContentPane().setBackground(verdeEscuro);
    tituloLabel.setFont(importantFont);
    tituloLabel.setForeground(Color.WHITE);
    btnJogar.setBackground(dourado);
    btnSair.setBackground(vermelho);
    pontosJogadorLabel.setForeground(Color.WHITE);
    pontosDealerLabel.setForeground(Color.WHITE);

    // Posicionamento inicial
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(tituloLabel, gbc);

    gbc.gridy = 1; 
    gbc.gridwidth = 1; 
    gbc.anchor = GridBagConstraints.LINE_START;
    add(btnJogar, gbc);

    gbc.gridx = 1; 
    gbc.anchor = GridBagConstraints.LINE_END; 
    add(btnSair, gbc);

    // Finalização
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    }
    
    private JPanel criarPainelCartas(List<Carta> cartas) {
    JPanel painelCartas = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    painelCartas.setBackground(new Color(27, 77, 31));
    
    for (Carta carta : cartas) {
      //Formatação para nome das imagens
      String naipeIngles;
      naipeIngles = switch (carta.getNaipe()) {
        case "copas" -> "hearts";
        case "ouros" -> "diamonds";
        case "espadas" -> "spades";
        case "paus" -> "clubs";
        default -> carta.getNaipe();
      };
      //Formatação para nome das imagens
      String valorIngles;
      valorIngles = switch(carta.getValor()) {
        case "J" -> "jack";
        case "Q" -> "queen";
        case "K" -> "king";
        case "A" -> "ace";
        default -> carta.getValor();
      };

      String nomeImagem = valorIngles + "_of_" + naipeIngles + ".png";
      ImageIcon icon = new ImageIcon("src/img/" + nomeImagem);
      JLabel cartaLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH)));

      painelCartas.add(cartaLabel);
    }
    
    return painelCartas;
  }

    public static void main(String[] args) {
    // Chamar o método para criar a interface na thread de eventos
    SwingUtilities.invokeLater(App::new);
    }
}