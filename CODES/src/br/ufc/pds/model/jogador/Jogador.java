package br.ufc.pds.model.jogador;

import br.ufc.pds.model.ContaBancaria;
import br.ufc.pds.model.campo.propriedade.Propriedade;

import java.util.ArrayList;

public abstract class Jogador {

	protected ContaBancaria contaBancaria;
	protected ArrayList<Propriedade> propriedades;

	public Boolean pagar(float valor) {
		return this.contaBancaria.pagar(valor);
	}

	public void receber(float valor) {
		this.contaBancaria.receber(valor);
	}

	public void receberCredor(float valor) {
		this.contaBancaria.receber(valor);
	}

	public void comprarPropriedade(Propriedade propriedade) {
		this.propriedades.add(propriedade);
	}

	public void venderPropriedade(Propriedade propriedade) {
		this.propriedades.remove(propriedade);
	}

	public ContaBancaria getContaBancaria() {
		return this.contaBancaria;
	}

	public ContaBancaria getConta() {
		return this.contaBancaria;
	}

	public float getSaldo(){
		return this.contaBancaria.getSaldo();
	}

	public ArrayList<Propriedade> getPropriedades() {
		return propriedades;
	}
}
