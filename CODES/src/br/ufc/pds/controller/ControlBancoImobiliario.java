package br.ufc.pds.controller;

import br.ufc.pds.Teste;
import br.ufc.pds.interfaces.ObserverJogador;
import br.ufc.pds.model.Tabuleiro;
import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.campo.propriedade.Propriedade;
import br.ufc.pds.model.campo.propriedade.Terreno;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.JogadorHumano;
import br.ufc.pds.model.Dado;
import br.ufc.pds.model.Peca;
import br.ufc.pds.view.EntraComJogador;
import br.ufc.pds.view.JogadorDaVez;
import br.ufc.pds.view.SelecionarQtdJogadores;
import br.ufc.pds.view.TelaPrincipal.TelaPrincipal;

import javax.swing.*;
import java.util.*;

public class ControlBancoImobiliario implements ObserverJogador {
	private static ControlBancoImobiliario controlBancoImobiliario = null;

	public static synchronized ControlBancoImobiliario getInstance(){
		if(controlBancoImobiliario == null) controlBancoImobiliario = new ControlBancoImobiliario();
		return controlBancoImobiliario;
	}

	private Map<Integer, JogadorHumano> jogadoresAtivos;
	private Map<Integer,JogadorHumano> jogadoresPresos;
	private Tabuleiro tabuleiro;
	protected Dado[] dados = {new Dado(), new Dado()};
	private TelaPrincipal telaPrincipal;

	private int numJogadores;
	private Banco banco;

	private boolean hasSetedNumJogadores = false;

	private ControlBancoImobiliario() {
		this.jogadoresAtivos = new HashMap<>();
		this.jogadoresPresos = new HashMap<>();
		this.banco = Banco.getInstance();
		this.tabuleiro = new Tabuleiro();
	}

	public void jogar() {

        SelecionarQtdJogadores SQTSpinner = new SelecionarQtdJogadores(2,6);
        SQTSpinner.setVisible(true);

        this.numJogadores = SQTSpinner.getQtdJogadores();

		for(int i=1; i<=this.numJogadores; i++) {
			EntraComJogador entraComJogador = new EntraComJogador();
			entraComJogador.run();
		}

        this.telaPrincipal = TelaPrincipal.getInstance(); //Instancia Tela Principal

		while(this.jogadoresAtivos.size()>1) {
			this.iniciarRodada();
		}
	}

	public void renderizarTelaPrincipal() {
        this.telaPrincipal.drawBackgroud();

        this.tabuleiro.getCampos().forEach((key, value) -> {
        	if (value instanceof Terreno && ((Terreno) value).getDono() != Banco.getInstance())
        		this.telaPrincipal.renderCasasHoteis((Terreno) value);

        	if (value instanceof Propriedade && ((Propriedade) value).getDono() != Banco.getInstance())
				this.telaPrincipal.renderProprietario((Propriedade)value);
		});

		this.jogadoresAtivos.forEach((key, value) -> {
			this.telaPrincipal.renderPeca(value.getPeca());
		});

        this.telaPrincipal.displayJanelaPrincipal();
    }

	public void adicionarJogador(String jogador, String cor){
		System.out.println(jogador+" "+cor);
		int indice = jogadoresAtivos.size();
		JogadorHumano jogadorHumano = new JogadorHumano(indice, jogador, new Peca(cor, this.tabuleiro.obterCampoInicial()), this.dados);
		this.jogadoresAtivos.put(indice, jogadorHumano);
		this.tabuleiro.obterCampoInicial().addJogador(jogadorHumano);
		jogadorHumano.addObserver(this);
	}

	protected String getNome(JogadorHumano jogador){
		return jogador.getNome();
	}

	private void iniciarRodada() {
		try {
            this.jogadoresAtivos.forEach((key, value) -> {
                this.renderizarTelaPrincipal();
                JogadorDaVez jdv;

                if (!(this.getJogadoresPresos().containsValue(value))) {
                    jdv = new JogadorDaVez(getNome(value) + " Jogando", "Propriedades: " + value.getPropriedades().size(), "Saldo: R$ " + value.getSaldo(), "Status: Jogador Livre");
                    jdv.setVisible(true);
                    value.lancarDados();
                    this.jogadorRealizaTurno(value);
                } else {
                    jdv = new JogadorDaVez(getNome(value) + " Jogando", "Propriedades: " + value.getPropriedades().size(), "Saldo: R$ " + value.getSaldo(), "Status: Jogador Preso");
                    jdv.setVisible(true);
                    value.lancarDados();
                    this.jogadorPresoRealizaTurno(value);
                }
            });
        } catch (ConcurrentModificationException e) {

        }
	}

	protected Tabuleiro getTabuleiro(){
		return tabuleiro;
	}

	private void jogadorPresoRealizaTurno(JogadorHumano jogador){
		ControlPrisao controlPrisao = new ControlPrisao(jogador, this);
		controlPrisao.jogadorPresoRealizaTurno(jogador, this);
	}

	private void jogadorRealizaTurno(JogadorHumano jogador) {
		if (jogador.getFichaCriminal().getNumDelitos()>=3){
			JOptionPane.showMessageDialog(null, getNome(jogador) + " preso por trapacear.");
			this.prenderJogador(jogador);
			jogador.getFichaCriminal().setNumDelitos(0);
		} else {
			Campo proximoCampo = this.alterarPosicaoDoJogador(jogador);

			try {
				this.executaAcaoCampo((EfeitoEspecial) proximoCampo, jogador); //Aciona o efeito especial para o Jogador...
			} catch (ClassCastException e) {
                JOptionPane.showMessageDialog(null, getNome(jogador) + " passou por "+proximoCampo.getNome());
			}
		}
	}

	protected Campo alterarPosicaoDoJogador(JogadorHumano jogador){
		ControlCampos controlCampos = new ControlCampos(jogador, this);
		Campo campo = controlCampos.alterarPosicaoDoJogador(jogador);
		Teste.informaAvancoJogador(jogador);//remover-------------------------------------------
		return campo;
	}

    private boolean compararDadosIguais(JogadorHumano jogador){
		if(jogador.getDados()[0].obterValorDaFace() == jogador.getDados()[1].obterValorDaFace()){
			return true;
		}
		return false;
	}

	private void setJogadorDaVez(JogadorHumano jogador){
		JogadorDaVez jdv = new JogadorDaVez(getNome(jogador) + " Jogando (Dados Iguais)", "Propriedades: " + jogador.getPropriedades().size(), "Saldo: R$ " + jogador.getSaldo(), "Status: Jogador Livre");
		jdv.setVisible(true);
	}

	private void executaAcaoCampo(EfeitoEspecial campo, JogadorHumano jogador) {
		if (campo != null) {
			campo.aplicarEfeito(jogador);

			if (compararDadosIguais(jogador) && this.jogadoresAtivos.containsValue(jogador)){
				setJogadorDaVez(jogador);
				jogador.lancarDados();
				jogador.atualizaFichaCriminal(jogador, compararDadosIguais(jogador), this.jogadoresAtivos.containsValue(jogador));
				this.jogadorRealizaTurno(jogador);

			} else {
                jogador.atualizaFichaCriminal(jogador, compararDadosIguais(jogador), this.jogadoresAtivos.containsValue(jogador));
            }
		}
	}

	public void prenderJogador(JogadorHumano jogador) {
		this.tabuleiro.buscarCampo(11).addJogador(jogador); // 11 é o índice da prisão
		this.jogadoresPresos.put(jogador.getId(), jogador);
	}

	public void soltarJogador(JogadorHumano jogador) {
		this.tabuleiro.buscarCampo(11).removerJogador(jogador); // 11 é o índice da prisão
		this.jogadoresPresos.remove(jogador.getId());
	}

    public ArrayList<String> getCores(){
        String[] cores = {"Azul", "Rosa", "Roxo", "Verde","Amarelo","Laranja"};
        List<String> coresList = new ArrayList<String>();

        for(String cor: cores){
            if(!this.corUsada(cor)){
                coresList.add(cor);
            }
        }

        return (ArrayList) coresList;
    }

    private boolean corUsada(String cor){
        for (int i = 0; i < jogadoresAtivos.size(); i++) {
            if(jogadoresAtivos.get(i).getPeca().getCor().equals(cor)){
                return true;
            }
        }

        for (int i = 0; i < jogadoresPresos.size(); i++) {
            if(jogadoresPresos.get(i).getPeca().getCor().equals(cor)){
                return true;
            }
        }

        return false;
    }

	public Map getJogadoresPresos() {
		return this.jogadoresPresos;
	}

	public Map getJogadoresAtivos() {
		return this.jogadoresAtivos;
	}

	public int getNumJogadores() {
		return numJogadores;
	}

	public void setNumJogadores(int numJogadores) {
		System.out.println(numJogadores);
		this.numJogadores = numJogadores;
	}

	public boolean validarConstrucaoCasa(JogadorHumano jogador, Terreno terreno){
		ArrayList<Campo> camposIndisponiveis = new ArrayList<>();

		this.tabuleiro.getCampos().forEach((key, campo) -> {
			if (campo instanceof Terreno) {
				if (((Terreno) campo).getCor().equals(terreno.getCor())) {
					System.out.println("Terreno Atual: "+terreno.getNumCasas()+" - " + "Terreno Iterado: "+((Terreno) campo).getNumCasas());
					if(!jogador.equals(((Terreno)campo).getDono()) || terreno.getNumCasas() > ((Terreno) campo).getNumCasas()) {
						camposIndisponiveis.add(campo);
					}
				}
			}
		});

		return camposIndisponiveis.isEmpty();
	}

	@Override
	public void update(JogadorHumano jogador, float valor) {
	    System.out.println(getNome(jogador)+" está devendo R$ "+valor);
	    float propriedadesVendidas = 0;

		if (jogador.getPropriedades().isEmpty()) {
			JOptionPane.showMessageDialog(null, getNome(jogador) + " foi à falência (Sem dinheiro para pagar os credores).");
			this.jogadoresAtivos.remove(jogador.getId());
			jogador.getPeca().obterLocalizacao().removerJogador(jogador);
		} else {
            ControlPagamentos.pagarDividas(jogador, propriedadesVendidas, valor);
		}
		if (this.jogadoresAtivos.size() == 1) {
            JOptionPane.showMessageDialog(null, "<Alguém> ganhou essa partida");
            TelaPrincipal.getInstance().closeJanela();
        }
	}
}
