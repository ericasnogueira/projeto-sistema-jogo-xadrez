package camadaDeXadrez.Pecas;

import camadaDeXadrez.Cor;
import camadaDeXadrez.PecaDeXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Rei extends PecaDeXadrez {
    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString(){
     // REI = KING(INGLES)
     return "R";

    }

    public boolean podeMover(Posicao posicao){
        PecaDeXadrez p =(PecaDeXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() !=getCor();
    }

    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        // acima
         p.setValues(posicao.getLinha() - 1, posicao.getColuna());
         if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
             mat[p.getLinha()][p.getColuna()] = true;
         }
         //abaixo

        p.setValues(posicao.getLinha() + 1, posicao.getColuna());
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        // esquerda

        p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //direita
        p.setValues(posicao.getLinha() , posicao.getColuna() + 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        // noroeste
        p.setValues(posicao.getLinha() - 1, posicao.getColuna()- 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        // nordeste
        // acima
        p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //suldoeste

        p.setValues(posicao.getLinha() + 1, posicao.getColuna()-1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }
        //sudeste
        // acima
        p.setValues(posicao.getLinha() + 1, posicao.getColuna()+1);
        if(getTabuleiro().posicaoExiste(p) && podeMover(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }
}
