package br.ufc.pds.model.campo.campo_especial;

import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.JogadorHumano;

public class PontoDePartida extends CampoEspecial implements EfeitoEspecial {

	public PontoDePartida(int indice, int eixoX, int eixoY) {
		super("Ponto de Partida", indice, eixoX, eixoY);
	}

	public void aplicarEfeito(JogadorHumano jogador) {
		String titulo = this.nome;
		String acao = Campo.getNomeJogador(jogador) + " ganhou R$ 200.";

		this.showMensagem(titulo, acao);

		//System.out.println(jogador.getNome() +  " ganhou R$ 200");
		Banco.getInstance().pagar(200);
		jogador.receberCredor(200);
	}

}
