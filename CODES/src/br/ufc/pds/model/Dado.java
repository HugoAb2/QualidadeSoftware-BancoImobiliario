package br.ufc.pds.model;

import java.util.Random;

public class Dado {

	private int valorDaFace;
	private int maxFaces;

	public Dado() {
		this.valorDaFace = 1;
		this.maxFaces = 6;
	}

	public void lancar() {
		Random random = new Random();
		this.valorDaFace = 1 + random.nextInt(this.maxFaces);
	}

	public int obterValorDaFace() {
		return this.valorDaFace;
	}

}
