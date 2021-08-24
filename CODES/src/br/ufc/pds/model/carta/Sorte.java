package br.ufc.pds.model.carta;

import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.JogadorHumano;

public class Sorte extends CartaSorteOuReves {
	public Sorte(String titulo, String descricao, float valor) {
		super(titulo, descricao, valor);
	}

	public void acao(JogadorHumano jogador) {
		Banco.getInstance().getConta().pagar(this.valor);
		jogador.receberCredor(this.valor);
		this.showCarta(this.titulo, this.descricao, Carta.getNomeDono(jogador) + " recebeu R$ " + this.valor);
	}

}
