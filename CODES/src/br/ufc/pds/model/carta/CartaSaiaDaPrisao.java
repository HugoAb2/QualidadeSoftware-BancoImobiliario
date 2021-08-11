package br.ufc.pds.model.carta;

import br.ufc.pds.controller.ControlBancoImobiliario;
import br.ufc.pds.model.jogador.JogadorHumano;

public class CartaSaiaDaPrisao extends Carta {

	public CartaSaiaDaPrisao(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.isCoringa = true;
	}

	public void acao(JogadorHumano jogador) {
		if(ControlBancoImobiliario.getInstance().getJogadoresPresos().containsValue(jogador)) {
			ControlBancoImobiliario.getInstance().soltarJogador(jogador);
			this.showCarta(this.titulo, this.descricao, jogador.getNome() + " foi solto da Prisão.");
		} else {
			jogador.setCartaPrisao(this);
			this.showCarta(this.titulo, this.descricao, jogador.getNome() + " ganhou: 'Saída Livre da Prisão'");
		}
	}

}
