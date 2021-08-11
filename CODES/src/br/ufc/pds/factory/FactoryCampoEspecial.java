package br.ufc.pds.factory;

import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.campo.campo_especial.*;

public class FactoryCampoEspecial {
    private static FactoryCampoEspecial factoryCampoEspecial = null;

    private FactoryCampoEspecial() {}

    public static synchronized FactoryCampoEspecial getInstance() {
        if(factoryCampoEspecial == null) factoryCampoEspecial = new FactoryCampoEspecial();
        return factoryCampoEspecial;
    }

    public Campo criar(String tipoCampo, int indice, int eixoX, int eixoY) {
        if (tipoCampo.equals("PontoDePartida")) {
            return new PontoDePartida(indice, eixoX, eixoY);
        } else if (tipoCampo.equals("ImpostoDeRenda")) {
            return new ImpostoDeRenda(indice, eixoX, eixoY);
        } else if (tipoCampo.equals("LucrosDividendos")) {
            return new LucrosDividendos(indice, eixoX, eixoY);
        } else if (tipoCampo.equals("ParadaLivre")) {
            return new ParadaLivre(indice, eixoX, eixoY);
        } else if (tipoCampo.equals("VaParaPrisao")) {
            return new VaParaPrisao(indice, eixoX, eixoY);
        } else if (tipoCampo.equals("Prisao")) {
            return new Prisao(indice, eixoX, eixoY);
        } else if (tipoCampo.equals("SorteOuReves")) {
            return new SorteOuReves(indice, eixoX, eixoY);
        }
        return null;
    }
}
