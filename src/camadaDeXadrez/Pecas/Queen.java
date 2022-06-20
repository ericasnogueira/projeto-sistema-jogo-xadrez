package camadaDeXadrez.Pecas;

import camadaDeXadrez.Cor;
import camadaDeXadrez.PecaDeXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Queen extends PecaDeXadrez {

    //construtor
    //informando o tabuleiro e a cor
    public Queen(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }
    @Override
    public String toString(){

        return "Q"; // a peça é a letra // no qual ela ira aparecer no tabuleiro

    }
    @Override
    public boolean[][] possivelMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0,0);

        //verificar acima da minha peça
        p.setValues(posicao.getLinha() - 1 ,posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1 );
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // verificar esquerda da minha peça
        p.setValues(posicao.getLinha() ,posicao.getColuna() - 1 );
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1 );
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //verificar direita da minha peça
        p.setValues(posicao.getLinha() ,posicao.getColuna() + 1 );
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1 );
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //verifica para baixo
        p.setValues(posicao.getLinha() + 1 ,posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1 );
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValues(posicao.getLinha() - 1 ,posicao.getColuna() -1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() - 1, p.getColuna() -1);
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // verificar nordeste da minha peça
        p.setValues(posicao.getLinha() -1 ,posicao.getColuna() + 1 );
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() - 1, p.getColuna() +1);
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //verificar sudeste da minha peça
        p.setValues(posicao.getLinha() +1,posicao.getColuna() + 1 );
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() +1, p.getColuna() +1);
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        //verificar sudoeste da minha peça
        p.setValues(posicao.getLinha() + 1 ,posicao.getColuna() - 1) ;
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)){
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValues(p.getLinha() +1, p.getColuna() -1);
        }
        if(getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)){
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }

}
