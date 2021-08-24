package br.ufc.pds.model.carta;

import br.ufc.pds.model.jogador.JogadorHumano;
import br.ufc.pds.view.MensagemSorteReves;

public abstract class Carta {

	protected String titulo;
	protected String descricao;
	protected boolean isCoringa;

	public abstract void acao(JogadorHumano jogador);

	public String getTitulo() {
		return this.titulo;
	}

	public void showCarta(String titulo, String mensagem, String acao) {
		MensagemSorteReves ms = new MensagemSorteReves(titulo, mensagem, acao);
		ms.setVisible(true);
	}

	public static String getNomeDono(JogadorHumano jogador){
		return jogador.getNomeDono();
	}
}
