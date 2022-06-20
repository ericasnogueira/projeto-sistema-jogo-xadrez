package jogotabuleiro;

public class Posicao {

    //atributos
    private int linha;
    private int coluna;

    //construtor
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    // atualizar os valores de uma posição
    public void setValues(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }



    //Imprimindo a posição na tela
    @Override
    public  String toString(){
        return  linha + ", " + coluna;
    }
}
