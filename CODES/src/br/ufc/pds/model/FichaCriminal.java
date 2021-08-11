package br.ufc.pds.model;

public class FichaCriminal {
    private int numDelitos;
    private int rodadasPreso;

    public FichaCriminal() {
        this.numDelitos = 0;
        this.rodadasPreso = 0;
    }

    public int getNumDelitos() {
        return numDelitos;
    }

    public void setNumDelitos(int numDelitos) {
        this.numDelitos = numDelitos;
    }

    public int getRodadasPreso() {
        return this.rodadasPreso;
    }

    public void setRodadasPreso(int rodadasPreso) {
        this.rodadasPreso = rodadasPreso;
    }
}
