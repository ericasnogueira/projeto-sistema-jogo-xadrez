package jogotabuleiro;

import camadaDeXadrez.ExcecaoXadrez;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca [][] pecas;

    //construtor

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas <1){
            throw new TabuleiroExcecao("ERROR CRIANDO TABULEIRO: É NECESSARIO QUE TENHA 1 LINHA E 1 COLUNA");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        //minha matriz de peças vai ser instanciada com quantidades de linhas e colunas informada
        pecas = new Peca[linhas][colunas];
    }

    public int getLinhas() {

        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    //metodo para retorna uma linha e uma coluna
    public  Peca peca (int linha, int coluna){
        if(!posicaoExiste(linha, coluna)){
            throw new TabuleiroExcecao("POSIÇÃO NÃO TEM NO TABULEIRO");
        }
        return pecas[linha][coluna];
    }
    //sobrecarga do metodo
    // recebendo uma posição
    public Peca peca(Posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new TabuleiroExcecao("POSIÇÃO NÃO TEM NO TABULEIRO");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    //metodo recebendo uma peça e uma posição
    public void colocarPeca(Peca peca, Posicao posicao){
        if (temPeca(posicao)){  //vendo se tem peça na posição
            throw new TabuleiroExcecao("JÁ TEM PEÇA NA POSIÇÃO : "+ posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()]= peca; // pegando a matriz na posição dada e atribuir a ela a peça que enformei
        peca.posicao = posicao;
    }
    //metodo para mover as pecas
    public Peca removePeca(Posicao posicao){
        if (! posicaoExiste(posicao)){
            throw new TabuleiroExcecao("POSIÇÃO NÃO TEM NO TABULEIRO");
        }
        if (peca(posicao) == null){
            return null;
        }
        Peca aux = peca(posicao);
        aux.posicao = null;
        pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return aux;
    }


    private boolean posicaoExiste(int linha, int coluna){
        return   linha >= 0 && linha < linhas && coluna >=0 && coluna < colunas;
    }

    public boolean posicaoExiste(Posicao posicao){
        return  posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }
    //metodo
    public boolean temPeca(Posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new TabuleiroExcecao("POSIÇÃO NÃO TEM NO TABULEIRO");
        }
        return peca(posicao) != null;
    }
}
