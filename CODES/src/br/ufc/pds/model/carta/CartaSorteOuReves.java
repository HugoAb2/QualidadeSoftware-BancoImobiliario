package br.ufc.pds.model.carta;

public abstract class CartaSorteOuReves extends Carta {

	protected float valor;

	protected CartaSorteOuReves (String titulo, String descricao, float valor) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.valor = valor;
		this.isCoringa = false;
	}
}
