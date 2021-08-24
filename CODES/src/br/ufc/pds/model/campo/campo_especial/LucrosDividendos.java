package br.ufc.pds.model.campo.campo_especial;

import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.JogadorHumano;

public class LucrosDividendos extends CampoEspecial implements EfeitoEspecial {

	public LucrosDividendos(int indice, int eixoX, int eixoY) {
		super("Lucros e Dividendos", indice, eixoX, eixoY);
	}

	public void aplicarEfeito(JogadorHumano jogador) {
		String titulo = this.nome;
		String acao = jogador.getNomeDono() + " recebeu R$ 200 de Lucros e Dividendos.";

		this.showMensagem(titulo, acao);

		//System.out.println(jogador.getNome() + " recebeu R$ 200");
		Banco.getInstance().getConta().pagar(200);
		jogador.receberCredor(200);
	}

}
