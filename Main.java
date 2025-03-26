package Entidades;

import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    boolean resposta = false;

    Suporte.telaInicial();

    while (true) {
      resposta = Suporte.menuInicial(sc);

      if (resposta) {
        while (jogo(sc))
          ;
      } else {
        sc.close();
        break;
      }
    }
  }

  public static boolean jogo(Scanner sc) {
	  String nomeArquivo = "jogadas.txt";
      Suporte.resetRegistro();
      Cor turno = Cor.b;
      Tabuleiro tab = new Tabuleiro();
      Jogador jogadores[] = new Jogador[2];

      jogadores[0] = new Jogador("1", Cor.b);
      jogadores[1] = new Jogador("2", Cor.p);
      tab.posicionamentoInicial(jogadores[0], jogadores[1]);
      Suporte.iniciar(sc, jogadores[0], jogadores[1], tab);
      System.out.println();

      // Carregar jogadas anteriores
      System.out.print("Deseja carregar jogadas anteriores? (S/N): ");
      String resposta = sc.nextLine().trim().toLowerCase();
      if (resposta.equals("s")) {
          Suporte.carregarJogadas();
      }

      while (true) {
          tab.print();
          System.out.println();
          System.out.println("[Turno de " + jogadores[turno.getValor()].getNome() + " (" + turno.toString() + ")]");
          System.out.println();

          tab.entradaCord(sc);

          if (tab.move(turno)) {
              if (jogadores[1 - turno.getValor()].isReiCapturado()) {
                  Suporte.fim(jogadores, turno, tab);
                  System.out.print("Deseja salvar as jogadas? (S/N): ");
                  resposta = sc.nextLine().trim().toLowerCase();
                  if (resposta.equals("s")) {
                	  Suporte.salvarJogadas(nomeArquivo);
                  }
                  return false; 
              }
              turno = turno == Cor.b ? Cor.p : Cor.b; // alternando a cor para a do jogador do proximo turno
              System.out.println();
          } else {
              System.out.print("Aperte Enter para continuar...");
              sc.nextLine();
              System.out.println();
              continue;
          }
      }
  }
}