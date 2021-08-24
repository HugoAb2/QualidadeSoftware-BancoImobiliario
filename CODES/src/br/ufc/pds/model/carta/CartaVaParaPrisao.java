package br.ufc.pds.model.carta;

import br.ufc.pds.controller.ControlBancoImobiliario;
import br.ufc.pds.model.jogador.JogadorHumano;

public class CartaVaParaPrisao extends Carta {

	public CartaVaParaPrisao(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.isCoringa = false;
	}

	public void acao(JogadorHumano jogador) {
		ControlBancoImobiliario.getInstance().prenderJogador(jogador);
		this.showCarta(this.titulo, this.descricao, Carta.getNomeDono(jogador) + " foi Preso!");
	}

}
