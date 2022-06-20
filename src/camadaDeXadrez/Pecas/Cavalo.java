package camadaDeXadrez.Pecas;

import camadaDeXadrez.Cor;
import camadaDeXadrez.PecaDeXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Cavalo extends PecaDeXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString(){
        // REI = KING(INGLES)
        return "C";

    }

    public boolean podeMover(Posicao posicao){
        PecaDeXadrez p =(PecaDeXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() !=getCor();
    }

    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);


        p.setValues(posicao.getLinha() - 1, posicao.getColuna() -2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        p.setValues(posicao.getLinha() -2, posicao.getColuna()-1 );
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        p.setValues(posicao.getLinha() -2 , posicao.getColuna() + 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValues(posicao.getLinha() -1, posicao.getColuna() + 2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValues(posicao.getLinha() +1, posicao.getColuna() + 2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValues(posicao.getLinha() +2, posicao.getColuna() + 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }


        p.setValues(posicao.getLinha() + 2, posicao.getColuna()-1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValues(posicao.getLinha() + 1, posicao.getColuna()-2);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
