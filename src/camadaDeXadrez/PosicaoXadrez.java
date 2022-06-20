package camadaDeXadrez;

import jogotabuleiro.Posicao;

public class PosicaoXadrez  {


    private char coluna;
    private int linha;

    //construtor


    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha <1 || linha> 8){
            throw new ExcecaoXadrez("Erro ao instanciar a posição de xadrez. os valores válidos são de a1 a h8.");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {

        return coluna;
    }

    public int getLinha() {
        return linha;
    }
    // metodo
    protected Posicao toPosicao(){
        return new Posicao(8 - linha, coluna - 'a');

    }

    protected static PosicaoXadrez fromPosicao(Posicao posicao){
        return new PosicaoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }
    @Override
    public String toString(){
        // imprimindo a posição do xadrez na ordem (coluna -- linha)
        return "" + coluna + linha;


    }
}
