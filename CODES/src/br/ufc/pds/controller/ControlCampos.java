package br.ufc.pds.controller;

import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.campo.propriedade.Compania;
import br.ufc.pds.model.campo.propriedade.Terreno;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.Jogador;
import br.ufc.pds.factory.FactoryCampoEspecial;

import java.util.HashMap;
import java.util.Map;

public class ControlCampos {

    Map<Integer, Campo> campos = new HashMap<>();
    Jogador dono = Banco.getInstance();

    public ControlCampos() {

    }

    private void inserirTerreno(int instance, String nome, int indice, Jogador dono, float preco, String cor, float aluguel, float precoCasa, float precoHotel, float aluguel1Casas, float aluguel2Casas, float aluguel3Casas, float aluguel4Casas, float aluguelHotel, int eixoX, int eixoY  ){
        campos.put(instance, new Terreno(
                nome,
                indice,
                dono,
                preco,
                cor,
                aluguel,
                precoCasa,
                precoHotel,
                aluguel1Casas, aluguel2Casas, aluguel3Casas, aluguel4Casas,
                aluguelHotel,
                eixoX, eixoY));
    }

    private void inserirCampoEspecial(int instance, String tipoCampo, int indice, int eixoX, int eixoY){
        campos.put(instance, FactoryCampoEspecial.getInstance().criar(tipoCampo, indice, eixoX, eixoY));
    }

    private void inserirCompania(int instance, String nome, int indice, float preco, Jogador dono, float taxa, int eixoX, int eixoY){
        campos.put(6, new Compania("COMPANIA FERROVIÁRIA", 6, 200, dono, 50, 339, 631  ));
    }

    public Map criarCampos() {

        inserirCampoEspecial(1,"PontoDePartida", 1, 639, 631);
        inserirTerreno(2, "LEBLON", 2, dono, 100, "ROSA", 6, 50, 50, 30, 90, 270, 400, 500, 581, 631);
        inserirCampoEspecial(3,"SorteOuReves", 3, 519, 631);
        inserirTerreno(4,"AV. PRESIDENTE VARGAS", 4, dono, 60, "ROSA", 2, 50, 50, 10, 30, 90, 160, 250, 458, 631);
        inserirTerreno(5,"AV. NOSSA SENHORA DE COPACABANA", 5, dono, 60, "ROSA", 4, 50, 50, 20, 60, 180, 320, 450, 398, 631);
        inserirCompania(6,"COMPANIA FERROVIÁRIA", 6, 200, dono, 50, 339, 631);
        inserirTerreno(7,"AV. BRIGADEIRO FARIA LIMA", 7, dono, 240, "AZUL", 20, 150, 150, 100, 300, 750, 925, 1100, 284, 631);
        inserirCompania(8,"COMPANIA DE VIAÇÃO", 8, 200, dono, 50, 337, 631);
        inserirTerreno(9,"AV. REBOUCAS", 9, dono, 220, "AZUL", 18, 150, 150, 90, 250, 700, 875, 1050, 173, 631);
        inserirTerreno(10,"AV. 9 DE JULHO", 10, dono, 220, "AZUL", 18, 150, 150, 90, 250, 700, 875, 1050, 109, 631);
        inserirCampoEspecial(11,"Prisao", 11, 23, 638);
        inserirTerreno(12,"AV. EUROPA", 12, dono, 200, "VIOLETA", 16, 100, 100, 80, 220, 600, 800, 1000, 23, 568);
        inserirCampoEspecial(13,"SorteOuReves", 13, 23, 512);
        inserirTerreno(14,"RUA AUGUSTA", 14, dono, 180, "VIOLETA", 14, 100, 100, 70, 200, 550, 750, 950, 23, 454);
        inserirTerreno(15,"AV. PACAEMBÚ", 15, dono, 180, "VIOLETA", 14, 100, 100, 70, 200, 550, 750, 950, 23, 394);
        inserirCompania(16,"COMPANIA DE TAXI", 16, 150, dono, 40, 23, 337);
        inserirCampoEspecial(17,"SorteOuReves", 17, 23, 277);
        inserirTerreno(18,"INTERLAGOS", 18, dono, 350, "LARANJA", 35, 200, 200, 175, 500, 1100, 1300, 1500, 23, 220);
        inserirCampoEspecial(19,"LucrosDividendos", 19, 23, 161);
        inserirTerreno(20,"MORUMBI", 20, dono, 400, "LARANJA", 50, 200, 200, 200, 600, 1400, 1700, 2000, 23, 107);
        inserirCampoEspecial(21,"ParadaLivre", 21, 23, 15);
        inserirTerreno(22,"FLAMENGO", 22, dono, 120, "VERMELHO", 8, 50, 50, 40, 100, 300, 450, 600, 109, 15);
        inserirCampoEspecial(23,"SorteOuReves", 22, 164, 15);
        inserirTerreno(24,"BOTAFOGO", 24, dono, 100, "VERMELHO", 6, 50, 50, 30, 90, 270, 400, 500, 224, 15);
        inserirCampoEspecial(25,"ImpostoDeRenda", 25, 280, 15);
        inserirCompania(26,"COMPANIA DE NAVEGAÇÃO", 26, 150, dono, 40, 340, 15);
        inserirTerreno(27,"AV. BRASIL", 27, dono, 160, "AMARELO", 12, 100, 100, 60, 180, 500, 700, 900, 396, 15);
        inserirCampoEspecial(28,"SorteOuReves", 28, 454, 15);
        inserirTerreno(29,"AV. PAULISTA", 29, dono, 140, "AMARELO", 10, 100, 100, 50, 150, 450, 625, 750, 511, 15);
        inserirTerreno(30,"JARDIM EUROPA", 30, dono, 140, "AMARELO", 10, 100, 100, 50, 150, 450, 625, 750, 567, 15);
        inserirCampoEspecial(31,"VaParaPrisao", 31, 640, 45);
        inserirTerreno(32,"COPACABANA", 32, dono, 260, "VERDE", 22, 150, 150, 110, 330, 800, 975, 1150, 631, 99);
        inserirCompania(33,"COMPANIA DE AVIAÇÃO", 33, 200, dono, 50, 631, 160);
        inserirTerreno(34,"AV. VIEIRA SOUTO", 34, dono, 320, "VERDE", 28, 200, 200, 150, 450, 1000, 1200, 1400, 631, 228);
        inserirTerreno(35,"AV. ATLÂNTICA", 35, dono, 300, "VERDE", 26, 200, 200, 130, 390, 900, 1100, 1275, 631, 279);
        inserirCompania(36,"COMPANIA DE TAXI AÉRIO", 36, 200, dono, 50, 631, 339);
        inserirTerreno(37,"IPANEMA", 37, dono, 300, "VERDE", 26, 200, 200, 130, 390, 900, 1100, 1275, 631, 392);
        inserirCampoEspecial(38,"SorteOuReves", 38, 631, 455);
        inserirTerreno(39,"BROOKLIN", 39, dono, 260, "ROXO", 22, 150, 150, 110, 330, 800, 975, 1150, 631, 509);
        inserirTerreno(40,"JARDIM PAULISTA", 40, dono, 280, "ROXO", 24, 150, 150, 120, 360, 860, 1025, 1200, 631, 571);

        return campos;
    }
}
