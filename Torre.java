package Entidades;

public class Torre extends Peca {
    public Torre(Cor cor) {
        super(TpPeca.TORRE, cor, 'T');
    }

    // funcao para verificar se um determinado movimento é válido para a torre

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        // a torre anda em linha reta para cima, baixo,
        // esquerda e direita, então apenas uma das coordenadas muda de cada vez
        return ((linhaOrigem == linhaDestino && colunaOrigem != colunaDestino) ||
                (colunaOrigem == colunaDestino && linhaOrigem != linhaDestino));
    }

    @Override
    public boolean caminhoLivre(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tab) {
        TpMovimento tp;

        if (linhaOrigem == linhaDestino && colunaOrigem != colunaDestino) {
            tp = TpMovimento.HORIZONTAL;
        } else {
            tp = TpMovimento.VERTICAL;
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
