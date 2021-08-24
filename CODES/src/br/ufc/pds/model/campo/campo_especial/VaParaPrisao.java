package br.ufc.pds.model.campo.campo_especial;

import br.ufc.pds.controller.ControlBancoImobiliario;
import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.jogador.JogadorHumano;

public class VaParaPrisao extends CampoEspecial implements EfeitoEspecial {

	public VaParaPrisao(int indice, int eixoX, int eixoY) {
		super("Vá Para Prisão", indice, eixoX, eixoY);
	}

	public void aplicarEfeito(JogadorHumano jogador) {
		ControlBancoImobiliario.getInstance().prenderJogador(jogador);
		String titulo = this.nome;
		String acao = Campo.getNomeJogador(jogador)+" foi Preso!";

		this.showMensagem(titulo, acao);
	}

}
