package br.ufc.pds.controller;

import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.JogadorHumano;

import javax.swing.*;

public class ControlPrisao {

    JogadorHumano jogador;
    ControlBancoImobiliario controler;

    ControlPrisao(JogadorHumano jogador, ControlBancoImobiliario controler){
        this.jogador = jogador;
        this.controler = controler;
    }

    private void cartaPrisao(JogadorHumano jogador){
        jogador.setCartaPrisao(null);
    }

    protected void sairDaPrisaoCoringa(JogadorHumano jogador){
        if(controler.getJogadoresPresos() != null){
            controler.soltarJogador(jogador);
            jogador.getFichaCriminal().setRodadasPreso(0);
            controler.alterarPosicaoDoJogador(jogador);
            JOptionPane.showMessageDialog(null, jogador.getNome() + " saiu da Prisão (Carta Coringa)");
        }

    }

    protected void sairDaPrisaoCartasIguais(JogadorHumano jogador){
        if(controler.getJogadoresPresos() != null){
            controler.soltarJogador(jogador);
            jogador.getFichaCriminal().setRodadasPreso(0);
            controler.alterarPosicaoDoJogador(jogador);
            JOptionPane.showMessageDialog(null, jogador.getNome() + " saiu da Prisão (Dados Iguais)");
        }
    }
    protected void sairDaPrisaoPaga(JogadorHumano jogador){
        if (jogador.pagarCredor(50)) {
            Banco.getInstance().receberCredor(50);
            controler.soltarJogador(jogador);
            jogador.getFichaCriminal().setRodadasPreso(0);
            controler.alterarPosicaoDoJogador(jogador);
            JOptionPane.showMessageDialog(null, jogador.getNome() + " saiu da Prisão (Pagou R$ 50,00 ao Banco)");
        }
    }

    protected void continuarPreso(JogadorHumano jogador){
        int delitos = jogador.getFichaCriminal().getRodadasPreso() + 1;
        System.out.println(delitos + " - " + jogador.getNome());
        jogador.getFichaCriminal().setRodadasPreso(delitos);
        JOptionPane.showMessageDialog(null, jogador.getNome() + " continuará Preso");
    }

    protected void jogadorPresoRealizaTurno(JogadorHumano jogador, ControlBancoImobiliario controler) {
        if (jogador.getCartaPrisao() != null) {
            sairDaPrisaoCoringa(jogador);
            cartaPrisao(jogador);
        } else if (controler.dados[0].obterValorDaFace() == controler.dados[1].obterValorDaFace()) {
            sairDaPrisaoCartasIguais(jogador);
        } else if (jogador.getFichaCriminal().getRodadasPreso() >= 3) {
            sairDaPrisaoPaga(jogador);
        } else {
            continuarPreso(jogador);
        }
    }
}
