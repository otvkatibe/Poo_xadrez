package Entidades;

import java.io.*;
import java.util.Scanner;

public abstract class Suporte {
  private static final String NOME_ARQUIVO = "jogadas.txt";
  private static String[] registro;
  private static int count_B;
  private static int count_P;

  public static void telaInicial() {
    System.out.println("╔════════════════════╗");
    System.out.println("║       XADREZ!      ║");
    System.out.println("╚════════════════════╝");
    System.out.println();
  }

  public static boolean menuInicial(Scanner sc) {
    int entrada = 0;

    while (true) {
      System.out.println("1: Iniciar nova partida");
      System.out.println("2: Sair");
      System.out.print("> ");
      try {
        entrada = Integer.parseInt(sc.nextLine());
      } catch(NumberFormatException ex) {
        entrada = -1;
      }
      System.out.println();
      if (entrada == 1) {
        return true;
      } else if (entrada == 2) {
        return false;
      } else {
          System.out.println("Entrada inválida! Tente novamente\n");
          continue;
        }
    }
  }

  public static void iniciar(Scanner sc, Jogador jog1, Jogador jog2, Tabuleiro tab) {
    String nome = "0";

    System.out.println("Insira o nome do jogador 1 (peças brancas):");
    nome = sc.nextLine();
    jog1.setNome(nome);

    System.out.println("Insira o nome do jogador 2 (peças pretas):");
    nome = sc.nextLine();
    jog2.setNome(nome);
  }

  public static void fim(Jogador jogadores[], Cor turno, Tabuleiro tab) {
    System.out.println();
    tab.print();
    System.out.println();
    System.out.println("Parabéns " + jogadores[turno.getValor()].getNome() + ", você ganhou!");
    System.out.println();
  }

  public static void log(Cor turno, TpPeca peca_tp, int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
    if(Suporte.registro == null){
      Suporte.registro = new String[3];
    }
    int intColunaOrigem = 'a' + colunaOrigem;
    int intColunaDestino = 'a' + colunaDestino;
    linhaOrigem += 1;
    linhaDestino += 1;

    if(turno == Cor.b){
      Suporte.count_B++;
    } else Suporte.count_P++;

    if(Suporte.count_B + Suporte.count_P != 1){
      Suporte.registro[0] = Suporte.registro[0] + String.format("%d: %s - %s - Origem: %d%c - Destino: %d%c\n", count_B + count_P, turno.toString(), peca_tp, linhaOrigem, intColunaOrigem, linhaDestino, intColunaDestino);
      if(turno == Cor.b){
        Suporte.registro[1] = Suporte.registro[1] + String.format("%d: %s - %s - Origem: %d%c - Destino: %d%c\n", count_B, turno.toString(), peca_tp, linhaOrigem, intColunaOrigem, linhaDestino, intColunaDestino);
      } else {
        Suporte.registro[2] = Suporte.registro[2] + String.format("%d: %s - %s - Origem: %d%c - Destino: %d%c\n", count_P, turno.toString(), peca_tp, linhaOrigem, intColunaOrigem, linhaDestino, intColunaDestino);
      }
    } else {
      Suporte.registro[0] = String.format("%d: %s - %s - Origem: %d%c - Destino: %d%c\n", count_B + count_P, turno.toString(), peca_tp, linhaOrigem, intColunaOrigem, linhaDestino, intColunaDestino);
      if(turno == Cor.b){
        Suporte.registro[1] = String.format("%d: %s - %s - Origem: %d%c - Destino: %d%c\n", count_B, turno.toString(), peca_tp, linhaOrigem, intColunaOrigem, linhaDestino, intColunaDestino);
      } else {
        Suporte.registro[2] = String.format("%d: %s - %s - Origem: %d%c - Destino: %d%c\n", count_P, turno.toString(), peca_tp, linhaOrigem, intColunaOrigem, linhaDestino, intColunaDestino);
      }
    }
  }

  public static void logPrint(int modo){
    System.out.println();

    if(modo == 6){
      System.out.println(Suporte.registro[0]);
    } else if(modo == 1){
      System.out.println(Suporte.registro[1]);
    } else if(modo == 15){
      System.out.println(Suporte.registro[2]);
    }
  }

  public static void resetRegistro(){
    if(Suporte.registro == null){
      Suporte.registro = new String[3];
    }
    for(int i = 0; i < 3; i++){
      Suporte.registro[i] = "";
    }
    Suporte.count_B = 0;
    Suporte.count_P = 0;
  }

  public static String[] getRegistro() {
      if (registro == null) {
          registro = new String[3];
      }
      return registro;
  }
  
  public static void setRegistro(String registro[]) {
    Suporte.registro = registro;
  }

  public static void salvarJogadas(String jogada) {
      try (PrintWriter out = new PrintWriter(new FileWriter("jogadas.txt", true))) {
          out.println(jogada);
          System.out.println("Jogada salva com sucesso!");
      } catch (IOException e) {
          System.err.println("Erro ao salvar a jogada: " + e.getMessage());
      }
  }

  public static void carregarJogadas() {
      File file = new File("jogadas.txt");
      if (!file.exists()) {
          System.out.println("Arquivo de jogadas não encontrado.");
          return;
      }

      try (Scanner scanner = new Scanner(file)) {
          while (scanner.hasNextLine()) {
              String line = scanner.nextLine();
              System.out.println(line);
          }
      } catch (IOException e) {
          System.err.println("Erro ao carregar as jogadas: " + e.getMessage());
      }
  }

}