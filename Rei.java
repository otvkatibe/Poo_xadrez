package Entidades;

public class Rei extends Peca {
    public Rei(Cor cor) {
        super(TpPeca.REI, cor, 'r');
    }

    // Função para checar se um determinado movimento é válido parA o rei

    @Override
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        // o rei anda exatamente uma casa, tanto verticalmente, horizontalmente e
        // diagonalmente
        return ((Math.abs(linhaOrigem - linhaDestino) == 1 || Math.abs(colunaOrigem - colunaDestino) == 1)
                && (Math.abs(linhaOrigem - linhaDestino) <= 1 && (Math.abs(colunaOrigem - colunaDestino) <= 1)));
    }

    @Override
    public boolean caminhoLivre(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro Tab) {
        return true;
    }
}