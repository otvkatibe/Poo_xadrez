package Entidades;

public abstract class Peca {
    private Cor cor;
    private TpPeca tipo;
    private char id; 
    private boolean capturada;

    protected Peca(TpPeca tipo, Cor cor, char id) {
        this.tipo = tipo;
        this.cor = cor;
        this.id = id;
        this.capturada = false;
    }

    public void print() {
        System.out.printf("Peca: %s - Cor: %s\n", this.tipo, this.cor);
    }

    // Get & Set
    public Cor getCor() {
        return cor;
    }

    protected void setCor(Cor cor) {
        this.cor = cor;
    }

    public TpPeca getTipo() {
        return tipo;
    }

    protected void setTipo(TpPeca tipo) {
        this.tipo = tipo;
    }

    public char getId() {
        return id;
    }

    protected void setId(char id) {
        this.id = id;
    }

    public boolean isCapturada() {
        return this.capturada;
    }

    public void setCapturada(boolean capturada) {
        this.capturada = capturada;
    }

    public abstract boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino);
    public abstract boolean caminhoLivre(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tab);
}
