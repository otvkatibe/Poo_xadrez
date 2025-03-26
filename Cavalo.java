package Entidades;

public class Cavalo extends Peca {
    public Cavalo(Cor cor) {
        super(TpPeca.CAVALO, cor, 'C');
    }

    @Override // Função para checar se um determinado movimento é válido para o cavalo
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        // o cavalo anda em L, tanto para a vertical quanto para a horizontal
        return (Math.abs(linhaOrigem - linhaDestino) == 2 && Math.abs(colunaOrigem - colunaDestino) == 1)
                || (Math.abs(linhaOrigem - linhaDestino) == 1 && Math.abs(colunaOrigem - colunaDestino) == 2);
    }

    @Override
    public boolean caminhoLivre(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tab) {
        return true;
    }
}