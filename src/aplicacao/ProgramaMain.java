package aplicacao;

import camadaDeXadrez.PartidaDeXadrez;
import camadaDeXadrez.PecaDeXadrez;
import camadaDeXadrez.PosicaoXadrez;
import jogotabuleiro.TabuleiroExcecao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProgramaMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        //lista de peças
        List<PecaDeXadrez> capture = new ArrayList<>();

        while (!partidaDeXadrez.getCheckMate()) {
            try {

                UI.clearScreen();// chamando o metodo de limpar a tela

                UI.printPartida(partidaDeXadrez, capture);
                System.out.println();
                System.out.print("INICIO : ");
                PosicaoXadrez inicio = UI.readPosicaoXadrez(sc);

                boolean[][] posiveisMovimentos = partidaDeXadrez.possivelMovimentos(inicio);
                UI.clearScreen();
                UI.printTabuleiro(partidaDeXadrez.getPecas(), posiveisMovimentos);


                System.out.println();
                System.out.print("DESTINO : ");
                PosicaoXadrez destino = UI.readPosicaoXadrez(sc);

                PecaDeXadrez capturePeca = partidaDeXadrez.movimentoXadrez(inicio, destino);
                if (capturePeca != null){
                    capture.add(capturePeca);
                }
           }
            catch (TabuleiroExcecao e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException e ){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printPartida(partidaDeXadrez,capture);
    }
}
