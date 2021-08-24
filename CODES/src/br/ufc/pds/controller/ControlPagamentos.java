package br.ufc.pds.controller;

import br.ufc.pds.model.campo.propriedade.Propriedade;
import br.ufc.pds.model.campo.propriedade.Terreno;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.JogadorHumano;

import javax.swing.*;

public class ControlPagamentos {

    protected static void pagamento(float propriedadesVendidas, JogadorHumano jogador){
        Banco.getInstance().getConta().pagar(propriedadesVendidas);
        jogador.receberCredor(propriedadesVendidas);
    }

    protected static float verifyPropriedade(Propriedade propriedade, float propriedadesVendidas){
        if (propriedade instanceof Terreno) {
            if (((Terreno) propriedade).isHasHotel()) {
                propriedadesVendidas += ((4 * ((Terreno) propriedade).getPrecoCasa() + ((Terreno) propriedade).getPrecoHotel()) / 2);
                propriedadesVendidas += propriedade.getPreco();
            } else {
                propriedadesVendidas += ((((Terreno) propriedade).getNumCasas() * ((Terreno) propriedade).getPrecoCasa()) / 2);
                propriedadesVendidas += propriedade.getPreco();
                System.out.println("Sem Hotel: " + propriedadesVendidas);
            }
        } else {
            propriedadesVendidas += propriedade.getPreco();
        }
        return propriedadesVendidas;
    }

    protected static void pagarDividas(JogadorHumano jogador, float propriedadesVendidas, float valor) {
        for (Propriedade propriedade : jogador.getPropriedades()) {
            propriedadesVendidas = verifyPropriedade(propriedade, propriedadesVendidas);
            messageVendeuPagou(jogador, propriedade, propriedadesVendidas);

            if (propriedadesVendidas >= valor) {
                pagamento(propriedadesVendidas, jogador);
                messagePagou(jogador);
            } else {
                messageNPagou();
            }
        }
    }

    private static void messageVendeuPagou(JogadorHumano jogador, Propriedade propriedade, float propriedadesVendidas){
        JOptionPane.showMessageDialog(null, getNome(jogador) + " vendeu " + propriedade.getNome() + " por " + propriedadesVendidas + " para pagar suas dívidas");
    }

    private static void messagePagou(JogadorHumano jogador){
        JOptionPane.showMessageDialog(null, getNome(jogador) + " pagou sua dívida e continua no jogo!");
    }

    private static void messageNPagou(){
        JOptionPane.showMessageDialog(null,  "Dinheiro Insificiente Para Pagar O Credor");
    }

    private static String getNome(JogadorHumano jogador){
        return jogador.getNome();
    }
}

