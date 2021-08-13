package br.ufc.pds.model.jogador;

import br.ufc.pds.interfaces.ObserverJogador;
import br.ufc.pds.interfaces.SubjectObserver;
import br.ufc.pds.model.campo.propriedade.Propriedade;
import br.ufc.pds.model.carta.Carta;
import br.ufc.pds.model.ContaBancaria;
import br.ufc.pds.model.FichaCriminal;
import br.ufc.pds.model.Peca;
import br.ufc.pds.model.Dado;

import java.util.ArrayList;
import java.util.List;

public class JogadorHumano extends Jogador implements SubjectObserver {

	private int id;
	private String nome;
	private Peca peca;
	private Carta cartaPrisao;
	private Dado[] dados;
	private FichaCriminal fichaCriminal;
	private List<ObserverJogador> observerCollection;

	public JogadorHumano(int id, String nome, Peca peca, Dado[] dados) {
		this.id = id;
		this.nome = nome;
		this.peca = peca;
		this.dados = dados;
		this.cartaPrisao = null;
		this.contaBancaria = new ContaBancaria(1500);
		this.propriedades = new ArrayList<>();
		this.fichaCriminal = new FichaCriminal();
		this.observerCollection = new ArrayList<>();
	}

	public void lancarDados() {
		this.dados[0].lancar();
		this.dados[1].lancar();
	}

	public boolean pagarCredor(float valor) {
		System.out.println("Saldo antes R$:" + this.contaBancaria.getSaldo());
	    if (this.getContaBancaria().getSaldo() < valor) {
            this.notifyObserver(valor);
        }
        System.out.println("Saldo depois R$:" + this.contaBancaria.getSaldo());
        return this.pagar(valor);
    }

	public String getNome() {
		return this.nome;
	}

	public Peca getPeca() {
		return this.peca;
	}

	public ContaBancaria getContaBancaria() {
		return this.contaBancaria;
	}

	public Dado[] getDados() {
		return this.dados;
	}

	public ArrayList<Propriedade> getPropriedades() {
		return propriedades;
	}

	public Carta getCartaPrisao() {
		return this.cartaPrisao;
	}

	public void setCartaPrisao(Carta carta) {
		this.cartaPrisao = carta;
	}

	public int getId() {
		return this.id;
	}

	public FichaCriminal getFichaCriminal() {
		return fichaCriminal;
	}

	public void atualizaFichaCriminal(JogadorHumano jogador, boolean dadosIguais, boolean jogadorAtivo){
		if(dadosIguais && jogadorAtivo){
			jogador.getFichaCriminal().setNumDelitos(jogador.getFichaCriminal().getNumDelitos()+1);
		}else{
			jogador.getFichaCriminal().setNumDelitos(0);
		}
	}

    @Override
    public void addObserver(ObserverJogador o) {
        this.observerCollection.add(o);
        System.out.println(this.observerCollection.size());
    }

    @Override
    public void removeObserver(ObserverJogador o) {
        this.observerCollection.remove(o);
    }

    @Override
    public void notifyObserver(float valor) {
        for (ObserverJogador object: this.observerCollection) {
            object.update(this, valor);
        }
    }
}
