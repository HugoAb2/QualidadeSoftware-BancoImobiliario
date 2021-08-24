package br.ufc.pds.model.campo;

import br.ufc.pds.model.jogador.JogadorHumano;

import java.util.ArrayList;

public abstract class Campo {

	protected String nome;
	protected ArrayList<JogadorHumano> listaJogadores;
	protected int indice;
	protected int eixoX;
	protected int eixoY;

	protected Campo(String nome, int indice, int eixoX, int eixoY) {
		this.nome = nome;
		this.indice = indice;
		this.listaJogadores = new ArrayList<>();
		this.eixoX = eixoX;
		this.eixoY = eixoY;
	}

	public int getIndice() {
		return indice;
	}

	public String getNome() {
		return nome;
	}

	public static String getNomeJogador(JogadorHumano jogador){
		return jogador.getNome();
	}

	public void addJogador(JogadorHumano jogadorHumano) {
		this.listaJogadores.add(jogadorHumano);
		jogadorHumano.getPeca().mudarLocalizacao(this);
	}

	public void removerJogador(JogadorHumano jogadorHumano) {
		this.listaJogadores.remove(jogadorHumano);
	}

	public int getEixoX() {
		return this.eixoX;
	}

	public int getEixoY() {
		return this.eixoY;
	}
}
