package br.ufc.pds.model.campo.propriedade;
import br.ufc.pds.model.jogador.JogadorHumano;
import br.ufc.pds.view.RealizarConstrucao;

import javax.swing.*;

public class Proprietario {

    protected Terreno terreno;

    public Proprietario(Terreno terreno){
        this.terreno = terreno;
    }

    protected void acoesProprietario () {
        RealizarConstrucao constuir = construir();

        if (constuir.isConstuir()) {
            if (!terreno.hasHotel) {
                System.out.println("Implementar");
                terreno.comprarCasas((JogadorHumano) terreno.dono);
            } else {
                JOptionPane.showMessageDialog(null, terreno.getNomeDono(((JogadorHumano)terreno.dono))+" Não pode mais construir nesse Terreno");
            }
        } else {
            System.out.println();
            JOptionPane.showMessageDialog(null,terreno.getNomeDono(((JogadorHumano) terreno.dono))+" encerrou o Turno.");
        }
    }

    protected RealizarConstrucao construir() {
        RealizarConstrucao construir = new RealizarConstrucao(terreno.getNomeDono(((JogadorHumano)terreno.dono))+" - Essa Propriedade é sua",  terreno.getNome(), "Saldo R$" + terreno.dono.getContaBancaria().getSaldo());
        construir.setVisible(true);

        return construir;
    }

}
