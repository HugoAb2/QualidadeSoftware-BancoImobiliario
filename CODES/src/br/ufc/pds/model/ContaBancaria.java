package br.ufc.pds.model;

public class ContaBancaria {

	private float saldo;

	public ContaBancaria(float saldoInicial) {
		this.saldo = saldoInicial;
	}

	public boolean pagar(float valor) {
		if (saldo > 0 && saldo > valor) {
			this.saldo-=valor;
			return true;
		}
		return false;
	}

	public void receber(float valor) {
		this.saldo+=valor;
	}

	public float getSaldo(){
		return this.saldo;
	}

}
