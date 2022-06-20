package camadaDeXadrez;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

//subclasse da Classe Peça
public abstract class PecaDeXadrez extends Peca {

    //atributo da classe
    private Cor cor;
    private int contagemDeMovimento;
    //construtor
    public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {

        return cor;
    }
    public int getContagemDeMovimento(){
        return contagemDeMovimento;
    }

    public void aumentarContagemMovimento(){
        contagemDeMovimento ++;
    }
    public void diminuirContagemMovimento(){
        contagemDeMovimento --;
    }

    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.fromPosicao(posicao);
    }


    protected boolean existePecaOpenente(Posicao posicao){
        PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }
}
