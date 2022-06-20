package camadaDeXadrez;

import camadaDeXadrez.Pecas.*;
import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import jogotabuleiro.TabuleiroExcecao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//coração do jogo de xadrez
//onde está as regras do jogo
public class PartidaDeXadrez {

    private int turno;
    private Cor atualJogador;

    private boolean check;

    private boolean chackMate;
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> capturedPecas = new ArrayList<>();

    private Tabuleiro tabuleiro;

    //construtor padrão
    public  PartidaDeXadrez(){
        tabuleiro = new Tabuleiro(8,8);
        turno = 1;
        atualJogador = Cor.WHITE;

        // chamando o metodo de iniciar a partida
        initialSetup();
    }

    public int getTurno() {
        return turno;
    }

    public Cor getAtualJogador() {

        return atualJogador;
    }
    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate(){
        return chackMate;
    }

    //metodo
    // retorna uma matriz de peças que são correspondetes a partida
    public PecaDeXadrez[][]getPecas (){
        PecaDeXadrez [][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        //percorrendo a matriz
        for(int i = 0; i < tabuleiro.getLinhas(); i ++){
            for (int j = 0; j < tabuleiro.getColunas(); j ++){
                mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i,j);
            }
        }
        return mat;
    }
    //movimentos possiveis
    public boolean [][] possivelMovimentos(PosicaoXadrez inicialPosicao){
        Posicao posicao = inicialPosicao.toPosicao();
        validaInicioPosicao(posicao);
        return tabuleiro.peca(posicao).possivelMovimentos();
    }


    //metodo de movimento de peças
     public  PecaDeXadrez movimentoXadrez(PosicaoXadrez  posicaoInicio, PosicaoXadrez posicaoDestino){
        // convertendo os parametros  para posição da matriz
         Posicao inicio = posicaoInicio.toPosicao();
         Posicao destino = posicaoDestino.toPosicao();
        // validando a posição inicial e de destino
        validaInicioPosicao(inicio);
        validaPosicaoDestino(inicio,destino);
         Peca capturePeca = movimento(inicio, destino);

         if(testCheck(atualJogador)){
             desfazerMovimento(inicio,destino,capturePeca);
             throw new ExcecaoXadrez("VOÇÊ NÃO PODE SE COLOCAR EM CHECK");
         }
         check = (testCheck(oponente(atualJogador))) ? true : false;

        if (testCheckMate(oponente(atualJogador))){
            chackMate = true ;
        } else {
            //chamando o metodo do proximo turno
            proximoTurno();
        }
        return (PecaDeXadrez) capturePeca;
     }

     private  Peca movimento(Posicao inicial, Posicao destino){
        PecaDeXadrez p =(PecaDeXadrez)tabuleiro.removePeca(inicial);// tirando a peça do seu lugar inicial
         p.aumentarContagemMovimento();
         //peça capturada
         Peca capturePeca = tabuleiro.removePeca(destino);
         tabuleiro.colocarPeca(p,destino);
         if (capturePeca != null){
             pecasNoTabuleiro.remove(capturePeca);
             capturedPecas.add(capturePeca);
         }

        return capturePeca;
     }

     private  void desfazerMovimento(Posicao inicial, Posicao destino, Peca capturePeca){
        PecaDeXadrez p = (PecaDeXadrez) tabuleiro.removePeca(destino);
        p.diminuirContagemMovimento();
        tabuleiro.colocarPeca(p, inicial);

        if (capturePeca != null){
            tabuleiro.colocarPeca(capturePeca, destino);
            capturedPecas.remove(capturePeca);
            pecasNoTabuleiro.add(capturePeca);
        }

     }


     private void validaInicioPosicao(Posicao posicao){
        if (!tabuleiro.temPeca(posicao)){
            throw new ExcecaoXadrez("NÃO TEM PEÇA NA POSIÇÃO INICIAL");
        }///verificando o jogador atual
        if (atualJogador != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()){
            throw new ExcecaoXadrez("A PEÇA NÃO É SUA ");
        }
        if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()){
            throw new TabuleiroExcecao("NÃO TEM MOVIMENTO POSSIVEL PARA A PEÇA");
        }
     }
     private void  validaPosicaoDestino(Posicao incial, Posicao destino){
        if (!tabuleiro.peca(incial).possivelMovimento(destino)){
            throw new TabuleiroExcecao("A PEÇA NÃO PODE SE MOVER PARA A POSIÇÃO DE DESTINO");
        }
     }

     // metodo trocar de turno
    private  void proximoTurno(){
        turno ++;
        atualJogador = (atualJogador == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
    }
    private Cor oponente(Cor cor){
        return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
    }
    private PecaDeXadrez rei(Cor cor){
        List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p :list){
            if(p instanceof Rei){
                return (PecaDeXadrez) p;
            }
        }
        throw new IllegalStateException("NÃO TEM REI DESSA COR : "+ cor);
    }

    private boolean testCheck(Cor cor){
        Posicao reiPosicao = rei(cor).getPosicaoXadrez().toPosicao();
        List<Peca> oponentePecas = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for(Peca p : oponentePecas){
            boolean[][] mat = p.possivelMovimentos();
            if(mat[reiPosicao.getLinha()][reiPosicao.getColuna()]){
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Cor cor){
        if(!testCheck(cor)){
            return false;
        }// pegando todas as peças da cor
        List<Peca> list =  pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
        for(Peca p : list){
            boolean[][] mat = p.possivelMovimentos();
            for (int i = 0; i < tabuleiro.getLinhas(); i++){
                for(int j = 0; j< tabuleiro.getColunas(); j++){
                    if(mat[i][j]){
                        Posicao inicial = ((PecaDeXadrez)p).getPosicaoXadrez().toPosicao();
                        Posicao destino = new Posicao(i,j);
                        Peca capturePeca = movimento(inicial, destino);
                        boolean testCheck = testCheck(cor);
                        desfazerMovimento(inicial,destino,capturePeca);
                        if (!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    //metodo
    // para colocar as peças na posição do xadrez
    private void novaPeca(char coluna, int linha , PecaDeXadrez peca){
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
        pecasNoTabuleiro.add(peca);// peças do tabuleiro

    }

    // metodo responsavel para iniciar a partida de xadrez
    // colocando as peças no tabuleiro
    private void  initialSetup(){
        novaPeca('a',1, new Torre(tabuleiro, Cor.WHITE));
        novaPeca('b',1, new Cavalo(tabuleiro, Cor.WHITE));
        novaPeca('c',1, new Bispo(tabuleiro, Cor.WHITE));
        novaPeca('d',1, new Queen(tabuleiro, Cor.WHITE));
        novaPeca('e',1, new Rei(tabuleiro, Cor.WHITE));
        novaPeca('f',1, new Bispo(tabuleiro, Cor.WHITE));
        novaPeca('g',1, new Cavalo(tabuleiro, Cor.WHITE));
        novaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
        novaPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));



        novaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
        novaPeca('b',8, new Cavalo(tabuleiro, Cor.BLACK));
        novaPeca('c',8, new Bispo(tabuleiro, Cor.BLACK));
        novaPeca('d',8, new Queen(tabuleiro, Cor.BLACK));
        novaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));
        novaPeca('f',8, new Bispo(tabuleiro, Cor.BLACK));
        novaPeca('g',8, new Cavalo(tabuleiro, Cor.BLACK));
        novaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
        novaPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));


    }
}
