package jogotabuleiro;

public abstract class Peca {

    protected  Posicao posicao;
    private Tabuleiro tabuleiro;

    //construtor
    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        //qual vai ser a posição de peça criada recente
        posicao = null;
    }

    // somente classes do mesmo pacote e subclasses é quem pode acessa o tabuleiro de uma peça
    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    //possiveis movimentos
    public abstract boolean [][] possivelMovimentos();

    public boolean possivelMovimento(Posicao posicao){
        return possivelMovimentos()[posicao.getLinha()][posicao.getColuna()];
    }
    public boolean existeAlgumMovimentoPossivel(){
        boolean [][] mat = possivelMovimentos();
        for(int i =0; i <mat.length; i++){
            for( int j =0; j < mat.length; j++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

}
