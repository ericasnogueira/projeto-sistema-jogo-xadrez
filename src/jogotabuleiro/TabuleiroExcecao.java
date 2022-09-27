package jogotabuleiro;

import javax.sound.sampled.SourceDataLine;

public class TabuleiroExcecao extends RuntimeException{

    private static final long serialVersionUID = 1L;

    //construtor
    public TabuleiroExcecao(String msg){
        super(msg);
    }


}
