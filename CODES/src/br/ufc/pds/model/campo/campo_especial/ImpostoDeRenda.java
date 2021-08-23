package br.ufc.pds.model.campo.campo_especial;

import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.JogadorHumano;

public class ImpostoDeRenda extends CampoEspecial implements EfeitoEspecial {

    public ImpostoDeRenda(int indice, int eixoX, int eixoY) {
        super("Imposto de Renda", indice, eixoX, eixoY);
    }

    public void aplicarEfeito(JogadorHumano jogador) {
        String titulo = this.nome;
        String acao = jogador.getNome() + " pagou R$ 200 de Imposto de Renda.";

        this.showMensagem(titulo, acao);

        if (jogador.pagarCredor(200)) {
            Banco.getInstance().receberCredor(200);
        }
    }
}
