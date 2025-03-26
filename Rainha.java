package Entidades;



public class Rainha extends Peca {
    public Rainha(Cor cor) {
        super(TpPeca.RAINHA, cor, 'R');
    }

    // Função para checar se um determinado movimento é válido para a rainha

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        // a rainha consegue andar para qualquer posição que combine o movimento da
        // Torre
        // com o do Bispo
        return ((Math.abs(linhaOrigem - linhaDestino) == Math.abs(colunaOrigem - colunaDestino)
                && Math.abs(colunaOrigem - colunaDestino) != 0) ||
                (linhaOrigem == linhaDestino && colunaOrigem != colunaDestino) ||
                (colunaOrigem == colunaDestino && linhaOrigem != linhaDestino));
    }

    public boolean caminhoLivre(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tab) {
        TpMovimento tp;

        if (linhaOrigem == linhaDestino && colunaOrigem != colunaDestino) {
            tp = TpMovimento.HORIZONTAL;
        } else if (linhaOrigem != linhaDestino && colunaOrigem == colunaDestino) {
            tp = TpMovimento.VERTICAL;
        } else if (colunaOrigem < colunaDestino && linhaOrigem < linhaDestino) {
            tp = TpMovimento.DGN_SUP_DIR;
        } else if (colunaOrigem > colunaDestino && linhaOrigem < linhaDestino) {
            tp = TpMovimento.DGN_SUP_ESQ;
        } else if (colunaOrigem < colunaDestino && linhaOrigem > linhaDestino) {
            tp = TpMovimento.DGN_INF_DIR;
        } else {
            tp = TpMovimento.DGN_INF_ESQ;
        }

        // Movimento diagonal
        int colunaInicio = Math.min(colunaOrigem, colunaDestino); // Recebe o menor índice da coluna
        int linhaInicio = Math.min(linhaOrigem, linhaDestino); // Recebe o menor índice da linha
        int linhaFim = Math.max(linhaOrigem, linhaDestino); // Recebe o maior

        // Diagonal superior direita
        if (tp == TpMovimento.DGN_SUP_DIR) {
            for (int i = linhaInicio + 1, j = colunaInicio + 1; i < linhaFim; i++, j++) {
                if (tab.getPeca(i, j) != null) {
                    System.out.println("O caminho está bloqueado!");
                    return false;
                }
            }
        }

        // Diagonal superior esquerda
        else if (tp == TpMovimento.DGN_SUP_ESQ) {
            for (int i = linhaInicio + 1, j = colunaInicio - 1; i < linhaFim; i++, j--) {
                if (tab.getPeca(i, j) != null) {
                    System.out.println("O caminho está bloqueado!");
                    return false;
                }
            }
        }

        // Diagonal inferior direita
        else if (tp == TpMovimento.DGN_INF_DIR) {
            for (int i = linhaInicio - 1, j = colunaInicio + 1; i > linhaFim; i--, j++) {
                if (tab.getPeca(i, j) != null) {
                    System.out.println("O caminho está bloqueado!");
                    return false;
                }
            }
        }

        // Diagonal inferior esquerda
        else if (tp == TpMovimento.DGN_INF_ESQ) {
            for (int i = linhaInicio - 1, j = colunaInicio - 1; i > linhaFim; i--, j--) {
                if (tab.getPeca(i, j) != null) {
                    System.out.println("O caminho está bloqueado!");
                    return false;
                }
            }
        }

        // Movimento horizontal
        if (tp == TpMovimento.HORIZONTAL) {
            int inicio = Math.min(colunaOrigem, colunaDestino); // Recebe o menor índice da coluna
            int fim = Math.max(colunaOrigem, colunaDestino); // Recebe o maior

            // Itera do menor ao maior procurando por obstáculos
            for (int i = inicio + 1; i < fim; i++) {
                if (tab.getPeca(linhaOrigem, i) != null) {
                    System.out.println("O caminho está bloqueado!");
                    return false;
                }
            }
        }

        // movimento vertical
        if (tp == TpMovimento.VERTICAL) {
            int inicio = Math.min(linhaOrigem, linhaDestino); // Recebe o menor índice da linha
            int fim = Math.max(linhaDestino, linhaDestino); // Recebe o maior

            // Itera do menor ao maior procurando por obstáculos
            for (int i = inicio + 1; i < fim; i++) {
                if (tab.getPeca(i, colunaOrigem) != null) {
                    return false;
                }
            }
        }

        return true;
    }
}