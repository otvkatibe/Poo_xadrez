package Entidades;

public class Peao extends Peca {
    public Peao(Cor cor) {
        super(TpPeca.PEAO, cor, 'P');
    }

    // funcao para checar se um determinado movimento é valido para o peao

    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        if (this.getCor() == Cor.b) {
            return ((colunaOrigem == colunaDestino && linhaDestino - linhaOrigem == 1)
                    || (colunaOrigem == colunaDestino && linhaOrigem == 1 && linhaDestino - linhaOrigem == 2)
                    || (linhaOrigem == linhaDestino - 1
                    && (colunaOrigem == colunaDestino + 1 || colunaOrigem == colunaDestino - 1)));
        } else {
            return ((colunaOrigem == colunaDestino && linhaOrigem - linhaDestino == 1)
                    || (linhaOrigem == 6 && linhaOrigem - linhaDestino == 2) || (linhaOrigem == linhaDestino + 1
                    && (colunaOrigem == colunaDestino + 1 || colunaOrigem == colunaDestino - 1)));
        }
    }

    @Override
    public boolean caminhoLivre(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tab) {
        if (this.getCor() == Cor.b && linhaOrigem == 1 && linhaDestino == 3) {
            if (tab.getPeca(2, colunaOrigem) != null) {
                System.out.println("O caminho está bloqueado!");
                return false;
            }
        }

        else if (this.getCor() == Cor.p && linhaOrigem == 6 && linhaDestino == 4) {
            if (tab.getPeca(5, colunaOrigem) != null) {
                System.out.println("O caminho está bloqueado!");
                return false;
            }
        }

        return true;
    }
}