package br.ufc.pds.model.campo.propriedade;

import br.ufc.pds.controller.ControlBancoImobiliario;
import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.Jogador;
import br.ufc.pds.model.jogador.JogadorHumano;
import br.ufc.pds.view.*;

import javax.swing.*;

public class Terreno extends Propriedade implements EfeitoEspecial {

	private String cor;
	private float aluguel;
	private int numCasas;
	private float precoCasa;
	private float precoHotel;
	private boolean hasHotel;
	private float aluguel1Casas;
	private float aluguel2Casas;
	private float aluguel3Casas;
	private float aluguel4Casas;
	private float aluguelHotel;

	public Terreno(String nome, int indice, Jogador dono, float preco, String cor, float aluguel, float precoCasa, float precoHotel, float aluguel1Casas, float aluguel2Casas, float aluguel3Casas, float aluguel4Casas, float aluguelHotel, int eixoX, int eixoY) {
        super(nome, indice, preco, dono, eixoX, eixoY);
		this.cor = cor;
		this.aluguel = aluguel;
		this.numCasas = 0;
		this.precoCasa = precoCasa;
		this.precoHotel = precoHotel;
		this.hasHotel = false;
		this.aluguel1Casas = aluguel1Casas;
		this.aluguel2Casas = aluguel2Casas;
		this.aluguel3Casas = aluguel3Casas;
		this.aluguel4Casas = aluguel4Casas;
		this.aluguelHotel = aluguelHotel;
	}

	public float getAluguel() {
	    if (this.hasHotel) {
	        return this.aluguelHotel;
        } else if (this.numCasas == 1) {
			return this.aluguel1Casas;
		} else if (this.numCasas == 2) {
			return this.aluguel2Casas;
		} else if (this.numCasas == 3) {
			return this.aluguel3Casas;
		} else if (this.numCasas == 4) {
			return this.aluguel4Casas;
		} else {
			return this.aluguel;
		}
	}

    @Override
    public void aplicarEfeito(JogadorHumano jogador) {
	    if (this.dono == jogador) {
	        this.acoesProprietario();
        } else if (this.dono == Banco.getInstance()) {
	        this.acoesComprador(jogador);
        } else {
	        this.acoesVisitante(jogador);
        }
    }

    public RealizarConstrucao construir() {
        RealizarConstrucao construir = new RealizarConstrucao(getNomeDono(((JogadorHumano)this.dono))+" - Essa Propriedade � sua",  this.getNome(), "Saldo R$" + this.dono.getContaBancaria().getSaldo());
        construir.setVisible(true);

        return construir;
    }

    public void acoesProprietario () {
        RealizarConstrucao constuir = construir();

        if (constuir.isConstuir()) {
            if (!this.hasHotel) {
                System.out.println("Implementar");
                this.comprarCasas((JogadorHumano) this.dono);
            } else {
                JOptionPane.showMessageDialog(null, getNomeDono(((JogadorHumano)this.dono))+" N�o pode mais construir nesse Terreno");
            }
        } else {
            System.out.println();
            JOptionPane.showMessageDialog(null,getNomeDono(((JogadorHumano) this.dono))+" encerrou o Turno.");
        }
    }

    private boolean verifyPCP(JogadorHumano jogador, FazerPropostaCompraPropriedade pcp){
	    return pcp.getComprar();
    }

    private void setVisible(FazerPropostaCompraPropriedade pcp){
	    pcp.setVisible(true);
    }

    private boolean isCancelar(InserirProposta proposta){
	    return proposta.isCancelar();
    }

    private float fazerProposta(JogadorHumano jogador, InserirProposta proposta, float valorProposto){
	    while (isCancelar(proposta)) {
            proposta.setVisible(true);

            try {
                valorProposto = Float.parseFloat(proposta.getValorProposto());
                if (valorProposto > jogador.getSaldo()) {
                    JOptionPane.showMessageDialog(null,getNomeDono(((JogadorHumano) this.dono)) + " seu saldo é insuficiente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,getNomeDono(((JogadorHumano) this.dono)) + " insira um valor válido.");
            }
        }
	    return valorProposto;
    }

    public void mostraPedidoCompra(JogadorHumano jogador, MostrarPedidoDeCompra pedido, float valorProposto){
        pedido.setVisible(true);

        if (pedido.getAceito()) {
            JOptionPane.showMessageDialog(null, nomeMensagem(jogador)+" comprou "+this.nome+" de "+getNomeDono(((JogadorHumano) this.dono))+" por R$ "+valorProposto);
            this.numCasas = 0; //IMPLEMENTAR VENDER TODAS AS CASAS/HOTÉIS PARA O BANCO---------------------------------------------------------------------------
            jogador.pagar(this.preco);
            this.dono.receber(this.preco);
            this.dono.venderPropriedade(this);
            jogador.comprarPropriedade(this);
            this.dono = jogador;
        } else {
            JOptionPane.showMessageDialog(null,getNomeDono(((JogadorHumano) this.dono)) + " recusou sua proposta.");
        }
    }

    private void acoesVisitante(JogadorHumano jogador) {
        String proprietario = "Esse Terreno Pertence a: " + getNomeDono(((JogadorHumano) this.dono));
        String aluguel = "Aluguel: " + this.getAluguel();
        String valor = "Valor: " + this.preco;
        FazerPropostaCompraPropriedade pcp = new FazerPropostaCompraPropriedade(proprietario, aluguel, valor);
        setVisible(pcp);

        if (verifyPCP(jogador, pcp)) {

            InserirProposta proposta = new InserirProposta("Faça uma Proposta", "Saldo: R$ "+jogador.getContaBancaria().getSaldo(), "Valor Terreno: R$ "+this.preco, this.preco);
            float valorProposto = 0;

            valorProposto = fazerProposta(jogador, proposta, valorProposto);

            if (isCancelar(proposta)){
                MostrarPedidoDeCompra pedido = new MostrarPedidoDeCompra(getNomeDono(((JogadorHumano) this.dono))+": "+nomeMensagem(jogador)+" fez uma Proposta", "Valor: R$ "+valorProposto, this.nome);
                mostraPedidoCompra(jogador, pedido, valorProposto);

            } else {
                JOptionPane.showMessageDialog(null,nomeMensagem(jogador) + " desistiu da compra.");
            }
        } else {
            if (jogador.pagarCredor(this.getAluguel())) {
                JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+" encerrou o Turno e pagou R$ " + this.getAluguel() + " de aluguel");
                this.dono.receber(this.getAluguel());
            }
        }
    }

    private ComprarPropriedade criarComprarPropriedade(JogadorHumano jogador) {
        String titulo = nomeMensagem(jogador) + " alcan�ou " + this.nome;
        String aluguel = "Aluguel: R$ "+this.aluguel;
        String valor = "Valor: R$ "+this.preco;
        String saldo = "Seu Saldo: R$ " + jogador.getContaBancaria().getSaldo();
        String numPropriedades = "Num. Prop.: " + jogador.getPropriedades().size();
        String dado1 = "Dado1: " + jogador.getDados()[0].obterValorDaFace();
        String dado2 = "Dado2: " + jogador.getDados()[1].obterValorDaFace();

        ComprarPropriedade cp = new ComprarPropriedade(titulo, aluguel, valor, saldo, numPropriedades, dado1, dado2);

        cp.setVisible(true);

        return cp;
    }

    private void acoesComprador(JogadorHumano jogador) {
        ComprarPropriedade cp = criarComprarPropriedade(jogador);

        boolean comprar = cp.getComprar();

        if (comprar) {
            if (jogador.pagar(this.preco)) {
                JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+" comprou " + this.nome + " por R$ "+this.preco);
                this.dono.receber(this.preco);
                this.dono.venderPropriedade(this);
                jogador.comprarPropriedade(this);
                this.dono = jogador;
            } else {
                JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+"  n�o tem dinheiro suficiente para comprar essa propriedade.");
            }

        } else {
            JOptionPane.showMessageDialog(null, nomeMensagem(jogador)+" encerrou o Turno.");
        }
    }

    private boolean validarConstrucaoCasa(JogadorHumano jogador){
        return ControlBancoImobiliario.getInstance().validarConstrucaoCasa(jogador, this);
    }

    private void comprarCasas(JogadorHumano jogador) {
        if (validarConstrucaoCasa(jogador)) {
            if (this.numCasas < 4) {
                if (jogador.pagar(this.precoCasa)) {
                    JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+" construiu uma casa.");
                    Banco.getInstance().receber(this.precoCasa);
                    this.numCasas+=1;
                } else {
                    JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+" você não possui saldo disponível.");
                }
            } else if (this.numCasas == 4) {
                if (jogador.pagar(this.precoHotel)) {
                    JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+" construiu um hotel.");
                    Banco.getInstance().receber(this.precoCasa);
                    this.hasHotel = true;
                } else {
                    JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+" você não possui saldo disponível.");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Você não pode mais construir nesse Terreno!");
            }
        } else {
            System.out.println("Não pode construir");
            JOptionPane.showMessageDialog(null,nomeMensagem(jogador)+" ainda não pode construir casas neste Terreno.");
        }
    }

    private String nomeMensagem(JogadorHumano jogador){
        return Campo.getNomeJogador(jogador);
    }

    private String getNomeDono(JogadorHumano jogador){
	    return jogador.getNomeDono();
    }

    public String getCor() {
	    return this.cor;
    }

    public int getNumCasas() {
	    return this.numCasas;
    }

    public boolean isHasHotel() {
	    return this.hasHotel;
    }

    public float getPrecoCasa() {
	    return this.precoCasa;
    }

    public float getPrecoHotel() {
	    return this.precoHotel;
    }
}
