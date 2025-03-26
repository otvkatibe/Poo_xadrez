package Entidades;

import java.util.Scanner;

public class Tabuleiro {
    public static final int TAM_PADRAO = 8;
    private Peca casas[][]; // o tabuleiro eh uma matriz de pecas
    private int linhaOrigem = 0;
    private int colunaOrigem = 0;
    private int linhaDestino = 0;
    private int colunaDestino = 0;

    public Tabuleiro() {
        this.casas = new Peca[TAM_PADRAO][TAM_PADRAO];
    }

    public void posicionamentoInicial(Jogador jog1, Jogador jog2) {
        int indice = 0;

        // primeiro sao posicionadas as pecas do jogador 1 (pecas brancas)
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < TAM_PADRAO; j++) {
                this.casas[i][j] = jog1.getPecas().get(indice);
                indice++;
            }
        }
        indice = 0;
        // depois as do jogador 2 (pecas pretas)
        for (int i = (TAM_PADRAO - 1); i > (TAM_PADRAO - 3); i--) {
            for (int j = 0; j < TAM_PADRAO; j++) {
                this.casas[i][j] = jog2.getPecas().get(indice);
                indice++;
            }
        }
    }

    // imprimir estado do tabuleiro
    public void print() {
        for (int c = 'a'; c <= 'h'; c++) {
            System.out.printf("  %c", c);
        }
        System.out.println();

        // TODO: usar cores?

        System.out.printf(" ┌──┬──┬──┬──┬──┬──┬──┬──┐\n");
        // System.out.printf(" ┌───┬───┬───┬───┬───┬───┬───┬───┐\n");
        for (int i = (TAM_PADRAO - 1); i >= 0; i--) {
            System.out.print(i + 1);
            System.out.print("│");
            for (int j = 0; j < TAM_PADRAO; j++) {
                Peca casa = this.casas[i][j];
                if (casa == null) {
                    System.out.print("  │");
                } else {
                    System.out.printf("%c%c│", casa.getId(), casa.getCor().getCodigo());
                }
            }
            if (i != 0) {
                System.out.print("\n ├──┼──┼──┼──┼──┼──┼──┼──┤\n");
                // System.out.print("\n ├───┼───┼───┼───┼───┼───┼───┼───┤\n");
            }
        }
        System.out.print("\n └──┴──┴──┴──┴──┴──┴──┴──┘\n\n");
        // System.out.print("\n └───┴───┴───┴───┴───┴───┴───┴───┘\n\n");
    }

    public void entradaCord(Scanner sc) {
        int nums[] = new int[2];

        System.out.print("Insira a coordenada da peça que deseja movimentar ");
        System.out.println("(primeiro o número da linha, depois a letra da coluna, separados por um espaço)");
        System.out.println();
        System.out.println("Para visualizar um registro das jogadas: insira as coordenadas '0 b' para ver um registro das jogadas das peças brancas, ou");
        System.out.println("'0 p' para um registro das peças pretas, ou '0 g' para um registro geral");
        System.out.println();
        System.out.print("> ");
        checkForaDoTabuleiro(nums, 1, sc);
        this.linhaOrigem = nums[0];
        this.colunaOrigem = nums[1];

        System.out.println();
        System.out.print("Insira a coordenada destino da sua peça ");
        System.out.println("(primeiro o número da linha, depois a letra da coluna, separados por um espaço)");
        System.out.println();
        System.out.println("Para visualizar um registro das jogadas: insira as coordenadas '0 b' para ver um registro das jogadas das peças brancas, ou");
        System.out.println("'0 p' para um registro das peças pretas, ou '0 g' para um registro geral");
        System.out.println();
        System.out.print("> ");
        checkForaDoTabuleiro(nums, 2, sc);
        this.linhaDestino = nums[0];
        this.colunaDestino = nums[1];
    }

    public static void checkForaDoTabuleiro(int nums[], int modo, Scanner sc) {
        char colunaChar = '0';

        while (true) {
            nums[0] = sc.nextInt();
            colunaChar = sc.next().charAt(0);
            sc.nextLine();
            nums[0] -= 1;
            nums[1] = colunaChar - 'a';

            if((nums[0] == (-1) && nums[1] == 1) || (nums[0] == (-1) && nums[1] == 15) || (nums[0] == (-1) && nums[1] == 6)){
                Suporte.logPrint(nums[1]);
                if(modo == 1){
                    System.out.println();
                    System.out.print("Insira a coordenada da peça que deseja movimentar ");
                }
                else {
                    System.out.println();
                    System.out.print("Insira a coordenada destino da sua peça ");
                }
                System.out.println("(primeiro o número da linha, depois a letra da coluna, separados por um espaço)");
                System.out.println();
                System.out.println("Para visualizar um registro das jogadas: insira as coordenadas '0 b' para ver um registro das jogadas das peças brancas, ou");
                System.out.println("'0 p' para um registro das peças pretas, ou '0 g' para um registro geral");
                System.out.println();
                System.out.print("> ");
                continue;
            }

            if (nums[0] < 0 || nums[0] > 7 || nums[1] < 0 || nums[1] > 7) {
                System.out.println("Coordenada não permitida, tente novamente");
                System.out.println("------------------------------------------");
                System.out.println();
                if(modo == 1){
                    System.out.print("Insira a coordenada da peça que deseja movimentar ");
                }
                else {
                    System.out.print("Insira a coordenada destino da sua peça ");
                }
                System.out.println("(primeiro o número da linha, depois a letra da coluna, separados por um espaço)");
                System.out.println();
                System.out.println("Para visualizar um registro das jogadas: insira as coordenadas '0 b' para ver um registro das jogadas das peças brancas, ou");
                System.out.println("'0 p' para um registro das peças pretas, ou '0 g' para um registro geral");
                System.out.println();
                System.out.print("> ");
                continue;
            }
            break;
        }
    }

    public boolean move(Cor turno) {
        Peca pecaOrigem = this.getPeca(this.linhaOrigem, this.colunaOrigem);
        Peca pecaDestino = this.getPeca(this.linhaDestino, this.colunaDestino);
        TpPeca peca_tp;

        if (!this.checkCondicoesMovimento(pecaOrigem, pecaDestino, turno)) {
            return false;
        }

        peca_tp = this.getPeca(linhaOrigem, colunaOrigem).getTipo();

        if (this.getPeca(this.linhaDestino, this.colunaDestino) != null) {
            this.getPeca(this.linhaDestino, this.colunaDestino).setCapturada(true);
        }

        this.setPeca(this.linhaOrigem, this.colunaOrigem, null);
        this.setPeca(this.linhaDestino, this.colunaDestino, pecaOrigem);

        Suporte.log(turno, peca_tp, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
        String jogada = String.format("%s - %s - Origem: %d%c - Destino: %d%c",
                turno.toString(), peca_tp.toString(), linhaOrigem + 1, colunaOrigem + 'a',
                linhaDestino + 1, colunaDestino + 'a');
        Suporte.salvarJogadas(jogada);

        return true;
    }

    public boolean checkCondicoesMovimento(Peca pecaOrigem, Peca pecaDestino, Cor turno) {
        // se ha alguma peca na cordenada de origem
        if (pecaOrigem != null) {
            // se a peça pertence ao jogador que esta jogando
            if (pecaOrigem.getCor() == turno) {
                // se o destino esta vazio ou ocupado de uma peça do outro jogador
                if ((pecaDestino == null || pecaDestino.getCor() != turno)) {
                    // se o movimento eh permitido pela peca
                    if (pecaOrigem.checaMovimento(this.linhaOrigem, this.colunaOrigem, this.linhaDestino, this.colunaDestino)) {
                        // se a peça eh um peão tentando andar em diagonal
                        if (checkExtraPeao(pecaOrigem, pecaDestino)) {
                            // se o caminho esta livre
                            if (pecaOrigem.caminhoLivre(this.linhaOrigem, this.colunaOrigem, this.linhaDestino, this.colunaDestino, this)) {
                                return true;
                            } else {
                                System.out.println("Há outra peça bloqueando seu caminho!");
                                return false;
                            }
                        } else {
                            System.out.println("O peão só pode se movimentar na diagonal para capturar uma peça inimiga!");
                            return false;
                        }
                    } else {
                        System.out.println("O movimento inserido não é possível para sua peça!");
                        return false;
                    }
                } else {
                    System.out.println("A coordenada de destino já está ocupada por uma peça sua!");
                    return false;
                }
            } else {
                System.out.println("A peça na coordenada inserida é do seu oponente!");
                return false;
            }
        } else {
            System.out.println("A coordenada inserida não possui nenhuma peça!");
            return false;
        }
    }

    public boolean checkExtraPeao(Peca pecaOrigem, Peca pecaDestino) {

        if (pecaOrigem instanceof Peao) {

            if (pecaOrigem.getCor() == Cor.b) {

                if (this.linhaOrigem == this.linhaDestino - 1
                        && (colunaOrigem == colunaDestino + 1 || colunaOrigem == colunaDestino - 1)) {
                    if (pecaDestino != null) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            else {
                if (this.linhaOrigem == this.linhaDestino + 1
                        && (colunaOrigem == colunaDestino + 1 || colunaOrigem == colunaDestino - 1)) {
                    if (pecaDestino != null) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // Get & set
    public Peca[][] getCasas() {
        return casas;
    }

    public void setCasas(Peca[][] casas) {
        this.casas = casas;
    }

    public Peca getPeca(int linha, int coluna) {
        return casas[linha][coluna];
    }

    public void setPeca(int linha, int coluna, Peca peca) {
        casas[linha][coluna] = peca;
    }

    public static int getTamPadrao() {
        return TAM_PADRAO;
    }

    public int getLinhaOrigem() {
        return linhaOrigem;
    }

    public void setLinhaOrigem(int linhaOrigem) {
        this.linhaOrigem = linhaOrigem;
    }

    public int getColunaOrigem() {
        return colunaOrigem;
    }

    public void setColunaOrigem(int colunaOrigem) {
        this.colunaOrigem = colunaOrigem;
    }

    public int getLinhaDestino() {
        return linhaDestino;
    }

    public void setLinhaDestino(int linhaDestino) {
        this.linhaDestino = linhaDestino;
    }

    public int getColunaDestino() {
        return colunaDestino;
    }

    public void setColunaDestino(int colunaDestino) {
        this.colunaDestino = colunaDestino;
    }
}