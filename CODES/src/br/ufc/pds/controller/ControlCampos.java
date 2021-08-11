package br.ufc.pds.controller;

import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.campo.propriedade.Companhia;
import br.ufc.pds.model.campo.propriedade.Terreno;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.Jogador;
import br.ufc.pds.factory.FactoryCampoEspecial;

import java.util.HashMap;
import java.util.Map;

public class ControlCampos {

    public ControlCampos() {

    }

    public Map criarCampos() {
        Map<Integer, Campo> campos = new HashMap<>();
        Jogador dono = Banco.getInstance();

        campos.put(1, FactoryCampoEspecial.getInstance().criar("PontoDePartida", 1, 639, 631));
        campos.put(2, new Terreno("LEBLON", 2, dono, 100, "ROSA", 6, 50, 50, 30, 90, 270, 400, 500, 581, 631));
        campos.put(3, FactoryCampoEspecial.getInstance().criar("SorteOuReves", 3, 519, 631));
        campos.put(4, new Terreno("AV. PRESIDENTE VARGAS", 4, dono, 60, "ROSA", 2, 50, 50, 10, 30, 90, 160, 250, 458, 631));
        campos.put(5, new Terreno("AV. NOSSA SENHORA DE COPACABANA", 5, dono, 60, "ROSA", 4, 50, 50, 20, 60, 180, 320, 450, 398, 631));
        campos.put(6, new Companhia("COMPANHIA FERROVIÁRIA", 6, 200, dono, 50, 339, 631  ));
        campos.put(7, new Terreno("AV. BRIGADEIRO FARIA LIMA", 7, dono, 240, "AZUL", 20, 150, 150, 100, 300, 750, 925, 1100, 284, 631));
        campos.put(8, new Companhia("COMPANHIA DE VIAÇÃO", 8, 200, dono, 50, 337, 631));
        campos.put(9, new Terreno("AV. REBOUCAS", 9, dono, 220, "AZUL", 18, 150, 150, 90, 250, 700, 875, 1050, 173, 631));
        campos.put(10, new Terreno("AV. 9 DE JULHO", 10, dono, 220, "AZUL", 18, 150, 150, 90, 250, 700, 875, 1050, 109, 631));
        campos.put(11, FactoryCampoEspecial.getInstance().criar("Prisao", 11, 23, 638));
        campos.put(12, new Terreno("AV. EUROPA", 12, dono, 200, "VIOLETA", 16, 100, 100, 80, 220, 600, 800, 1000, 23, 568));
        campos.put(13, FactoryCampoEspecial.getInstance().criar("SorteOuReves", 13, 23, 512));
        campos.put(14, new Terreno("RUA AUGUSTA", 14, dono, 180, "VIOLETA", 14, 100, 100, 70, 200, 550, 750, 950, 23, 454));
        campos.put(15, new Terreno("AV. PACAEMBÚ", 15, dono, 180, "VIOLETA", 14, 100, 100, 70, 200, 550, 750, 950, 23, 394));
        campos.put(16, new Companhia("COMPANHIA DE TAXI", 16, 150, dono, 40, 23, 337));
        campos.put(17, FactoryCampoEspecial.getInstance().criar("SorteOuReves", 17, 23, 277));
        campos.put(18, new Terreno("INTERLAGOS", 18, dono, 350, "LARANJA", 35, 200, 200, 175, 500, 1100, 1300, 1500, 23, 220));
        campos.put(19, FactoryCampoEspecial.getInstance().criar("LucrosDividendos", 19, 23, 161));
        campos.put(20, new Terreno("MORUMBI", 20, dono, 400, "LARANJA", 50, 200, 200, 200, 600, 1400, 1700, 2000, 23, 107));
        campos.put(21, FactoryCampoEspecial.getInstance().criar("ParadaLivre", 21, 23, 15));
        campos.put(22, new Terreno("FLAMENGO", 22, dono, 120, "VERMELHO", 8, 50, 50, 40, 100, 300, 450, 600, 109, 15));
        campos.put(23, FactoryCampoEspecial.getInstance().criar("SorteOuReves", 22, 164, 15));
        campos.put(24, new Terreno("BOTAFOGO", 24, dono, 100, "VERMELHO", 6, 50, 50, 30, 90, 270, 400, 500, 224, 15));
        campos.put(25, FactoryCampoEspecial.getInstance().criar("ImpostoDeRenda", 25, 280, 15));
        campos.put(26, new Companhia("COMPANHIA DE NAVEGAÇÃO", 26, 150, dono, 40, 340, 15));
        campos.put(27, new Terreno("AV. BRASIL", 27, dono, 160, "AMARELO", 12, 100, 100, 60, 180, 500, 700, 900, 396, 15));
        campos.put(28, FactoryCampoEspecial.getInstance().criar("SorteOuReves", 28, 454, 15));
        campos.put(29, new Terreno("AV. PAULISTA", 29, dono, 140, "AMARELO", 10, 100, 100, 50, 150, 450, 625, 750, 511, 15));
        campos.put(30, new Terreno("JARDIM EUROPA", 30, dono, 140, "AMARELO", 10, 100, 100, 50, 150, 450, 625, 750, 567, 15));
        campos.put(31, FactoryCampoEspecial.getInstance().criar("VaParaPrisao", 31, 640, 45));
        campos.put(32, new Terreno("COPACABANA", 32, dono, 260, "VERDE", 22, 150, 150, 110, 330, 800, 975, 1150, 631, 99));
        campos.put(33, new Companhia("COMPANHIA DE AVIAÇÃO", 33, 200, dono, 50, 631, 160));
        campos.put(34, new Terreno("AV. VIEIRA SOUTO", 34, dono, 320, "VERDE", 28, 200, 200, 150, 450, 1000, 1200, 1400, 631, 228));
        campos.put(35, new Terreno("AV. ATLÂNTICA", 35, dono, 300, "VERDE", 26, 200, 200, 130, 390, 900, 1100, 1275, 631, 279));
        campos.put(36, new Companhia("COMPANHIA DE TAXI AÉRIO", 36, 200, dono, 50, 631, 339));
        campos.put(37, new Terreno("IPANEMA", 37, dono, 300, "VERDE", 26, 200, 200, 130, 390, 900, 1100, 1275, 631, 392));
        campos.put(38, FactoryCampoEspecial.getInstance().criar("SorteOuReves", 38, 631, 455));
        campos.put(39, new Terreno("BROOKLIN", 39, dono, 260, "ROXO", 22, 150, 150, 110, 330, 800, 975, 1150, 631, 509));
        campos.put(40, new Terreno("JARDIM PAULISTA", 40, dono, 280, "ROXO", 24, 150, 150, 120, 360, 860, 1025, 1200, 631, 571));

        return campos;
    }
}
