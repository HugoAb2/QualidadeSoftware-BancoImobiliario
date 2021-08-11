package br.ufc.pds.model.campo.propriedade;

import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.jogador.Jogador;

public abstract class Propriedade extends Campo {

	protected float preco;
	protected Jogador dono;

	protected Propriedade(String nome, int indice, float preco, Jogador jogador, int eixoX, int eixoY) {
		super(nome, indice, eixoX, eixoY);
		this.preco = preco;
		this.dono = jogador;
	}

	public float getPreco() {
		return this.preco;
	}

	public Jogador getDono() {
		return this.dono;
	}
}
