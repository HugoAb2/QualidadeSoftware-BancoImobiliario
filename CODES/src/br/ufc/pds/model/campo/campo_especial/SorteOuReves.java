package br.ufc.pds.model.campo.campo_especial;

import br.ufc.pds.controller.ControlSorteOuReves;
import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.carta.Carta;
import br.ufc.pds.model.jogador.JogadorHumano;

public class SorteOuReves extends CampoEspecial implements EfeitoEspecial {

	private ControlSorteOuReves controlSorteOuReves = ControlSorteOuReves.getInstance();

	public SorteOuReves(int indice, int eixoX, int eixoY) {
		super("Sorte ou Revés", indice, eixoX, eixoY);
	}

	public void aplicarEfeito(JogadorHumano jogador) {
		Carta sorteda = (Carta) this.controlSorteOuReves.next();
//		System.out.println("Carta " + sorteda.getTitulo() + "\nDescricao: " + sorteda.getDescricao());
		sorteda.acao(jogador);
	}

}
