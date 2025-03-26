package Entidades;

public enum Cor {
    b(0, "Branco", 'b'),
    p(1, "Preto", 'p');

    private final int v;
    private final String s;
    private final char cod;

    private Cor(int v, String s, char cod) {
        this.v = v;
        this.s = s;
        this.cod = cod;
    }

    public int getValor() {
        return v;
    }

    public char getCodigo() {
        return cod;
    }

    @Override
    public String toString() {
        return s;
    }
}