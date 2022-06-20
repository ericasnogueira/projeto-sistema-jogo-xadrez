package aplicacao;

import camadaDeXadrez.Cor;
import camadaDeXadrez.PartidaDeXadrez;
import camadaDeXadrez.PecaDeXadrez;
import camadaDeXadrez.PosicaoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {
    //terminal colorido
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    //limpando a tela
    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    // lendo a posição do usuario
    public static PosicaoXadrez readPosicaoXadrez(Scanner sc){
        try {// evitando que ocorra algum error na hora de le os dados
            //lendo
            String s = sc.nextLine();
            char coluna = s.charAt(0);
            int linha = Integer.parseInt(s.substring(1));// convertendo para int para ter a linha
            return new PosicaoXadrez(coluna, linha);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("ERROR NA HORA DA ENTRADA DE DADOS. VALORES VÁLIDOS SÃO a1 A h8");
        }
    }

    //metodo da PARTIDA
    public static void printPartida(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez> capture){
        printTabuleiro(partidaDeXadrez.getPecas());
        System.out.println();
        printCapturePecas(capture);
        System.out.println();
        System.out.println("TURNO : " + partidaDeXadrez.getTurno());
        if(!partidaDeXadrez.getCheckMate()){
            System.out.println("ESPERANDO JOGADOR : " + partidaDeXadrez.getAtualJogador());
            if (partidaDeXadrez.getCheck()){
                System.out.println("CHECK!");
            }
        } else {
            System.out.println("CHECKMATE !");
            System.out.println("VENCEDOR: " + partidaDeXadrez.getAtualJogador());
        }
    }


    //metodo
    public static void printTabuleiro(PecaDeXadrez[][]pecas){
        //lógica para imprimir no formato do tabuleiro
        for(int i=0; i<pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j<pecas.length; j++){
                printPeca(pecas[i][j], false);
            }
            // quebra de linha
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printTabuleiro(PecaDeXadrez[][]pecas , boolean[][] possiveisMovimentos){
        //lógica para imprimir no formato do tabuleiro
        for(int i=0; i<pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j<pecas.length; j++){
                printPeca(pecas[i][j], possiveisMovimentos[i][j]);
            }
            // quebra de linha
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }




    // imprimindo uma peça
    private  static void printPeca(PecaDeXadrez peca , boolean background){
        if (background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (peca == null){
            System.out.print("-" + ANSI_RESET);
        } else {
            if (peca.getCor() == Cor.WHITE){
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
   // mostrar as  peças capturadas
    private static void printCapturePecas(List<PecaDeXadrez> capture){
        List<PecaDeXadrez> white = capture.stream().filter(x -> x.getCor() == Cor.WHITE).collect(Collectors.toList());
        List<PecaDeXadrez> black = capture.stream().filter(x -> x.getCor() == Cor.BLACK).collect(Collectors.toList());
        System.out.println("CAPTURADA PEÇAS : ");
        System.out.print("WHITE : ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("BLACK : ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);

    }
}
