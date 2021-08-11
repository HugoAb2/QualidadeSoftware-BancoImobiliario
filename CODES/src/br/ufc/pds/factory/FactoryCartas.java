package br.ufc.pds.factory;

import br.ufc.pds.model.carta.*;

public class FactoryCartas {
	private static FactoryCartas factoryCartas = new FactoryCartas();

	private FactoryCartas() {}

	public static synchronized FactoryCartas getInstance() {
		if(factoryCartas == null) factoryCartas =  new FactoryCartas();
		return factoryCartas;
	}

	public Carta criar(String titulo, String descricao, String tipoCarta, float valor) {
		if (tipoCarta.equals("Sorte")) {
			return new Sorte(titulo, descricao, valor);
		} else if (tipoCarta.equals("Reves")) {
			return new Reves(titulo, descricao, valor);
		} else if (tipoCarta.equals("Aposta")) {
			return new CartaAposta(titulo, descricao, valor);
		} else if (tipoCarta.equals("SaiaDaPrisao")) {
			return new CartaSaiaDaPrisao(titulo, descricao);
		} else if (tipoCarta.equals("VaParaPrisao")) {
			return new CartaVaParaPrisao(titulo, descricao);
		}
		return null;
	}

}
