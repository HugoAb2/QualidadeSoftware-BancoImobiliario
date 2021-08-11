package br.ufc.pds.model.campo.campo_especial;

import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.jogador.JogadorHumano;

public class Prisao extends CampoEspecial implements EfeitoEspecial {

	public Prisao(int indice, int eixoX, int eixoY) {
		super("Prisão", indice, eixoX, eixoY);
	}

	public void aplicarEfeito(JogadorHumano jogador) {
		this.showMensagem(jogador.getNome()+" está visitando a prisão", "Sem Ações");
	}

}
