package br.ufc.pds;

import br.ufc.pds.controller.ControlBancoImobiliario;

public class Main {
    public static void main (String args[]) {
        ControlBancoImobiliario controlBancoImobiliario = ControlBancoImobiliario.getInstance();
        controlBancoImobiliario.jogar();
    }
}
