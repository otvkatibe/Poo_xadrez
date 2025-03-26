package Entidades;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private Cor cor;
    private ArrayList<Peca> pecas;

    public Jogador(String nome, Cor cor) {
        this.nome = nome;
        this.cor = cor;
        this.pecas = new ArrayList<Peca>();
        startPecas();
    }

    private void startPecas() {
        // Adicionando as peças principais
        Peca[] principais = {
                new Torre(this.cor), new Cavalo(this.cor), new Bispo(this.cor),
                new Rainha(this.cor), new Rei(this.cor), new Bispo(this.cor),
                new Cavalo(this.cor), new Torre(this.cor)
        };
        for (Peca peca : principais) {
            addPeca(peca);
        }
        // Adicionando os peões
        for (int i = 0; i < Tabuleiro.TAM_PADRAO; i++) {
            addPeca(new Peao(this.cor));
        }
    }

    // Get & set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public Peca getUmaPeca(int indice) {
        return this.pecas.get(indice);
    }

    public void addPeca(Peca peca) {
        this.pecas.add(peca);
    }

    public void resetPecas() {
        this.pecas.clear();
    }

    public boolean isReiCapturado() {
        for (Peca peca : pecas) {
            if (peca instanceof Rei) {
                return peca.isCapturada();
            }
        }
        return false;
    }
}