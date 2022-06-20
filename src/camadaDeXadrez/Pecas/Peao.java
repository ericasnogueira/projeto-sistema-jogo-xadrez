package camadaDeXadrez.Pecas;

import camadaDeXadrez.Cor;
import camadaDeXadrez.PecaDeXadrez;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public class Peao extends PecaDeXadrez {

    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.WHITE) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temPeca(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
		}
		else {
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temPeca(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && existePecaOpenente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}	
		}
		return mat;
	}


    @Override
    public String toString(){
        return "P";
    }
}