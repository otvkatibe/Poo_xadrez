package Entidades;

public class Bispo extends Peca {
    public Bispo(Cor cor) {
        super(TpPeca.BISPO, cor, 'B');
    }

    // Função para checar se um determinado movimento é válido para o bispo

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        // o bispo anda apenas na diagonal
        // uma diagonal possui a diferença entre as origens e destinos iguais
        return (Math.abs(linhaOrigem - linhaDestino) == Math.abs(colunaOrigem - colunaDestino)
                && Math.abs(colunaOrigem - colunaDestino) != 0);
    }

    @Override
    public boolean caminhoLivre(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tab) {
        TpMovimento tp;

        if (colunaOrigem < colunaDestino && linhaOrigem < linhaDestino) {
            tp = TpMovimento.DGN_SUP_DIR;
        }

        else if (colunaOrigem > colunaDestino && linhaOrigem < linhaDestino) {
            tp = TpMovimento.DGN_SUP_ESQ;
        }

        else if (colunaOrigem < colunaDestino && linhaOrigem > linhaDestino) {
            tp = TpMovimento.DGN_INF_DIR;
        }

        else {
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

        return true;
    }
}