package br.ufc.pds.model.campo.propriedade;

import br.ufc.pds.controller.ControlBancoImobiliario;
import br.ufc.pds.interfaces.EfeitoEspecial;
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
        RealizarConstrucao construir = new RealizarConstrucao(((JogadorHumano)this.dono).getNome()+" - Essa Propriedade � sua",  this.getNome(), "Saldo R$" + this.dono.getContaBancaria().getSaldo());
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
                JOptionPane.showMessageDialog(null, ((JogadorHumano)this.dono).getNome()+" N�o pode mais construir nesse Terreno");
            }
        } else {
            System.out.println();
            JOptionPane.showMessageDialog(null,(((JogadorHumano) this.dono).getNome()+" encerrou o Turno."));
        }
    }

    private void acoesVisitante(JogadorHumano jogador) {
        String proprietario = "Esse Terreno Pertence a: " + ((JogadorHumano) this.dono).getNome();
        String aluguel = "Aluguel: " + this.getAluguel();
        String valor = "Valor: " + this.preco;
        FazerPropostaCompraPropriedade pcp = new FazerPropostaCompraPropriedade(proprietario, aluguel, valor);
        pcp.setVisible(true);

        if (pcp.getComprar()) {

            InserirProposta proposta = new InserirProposta("Faça uma Proposta", "Saldo: R$ "+jogador.getContaBancaria().getSaldo(), "Valor Terreno: R$ "+this.preco, this.preco);
            float valorProposto = 0;

            while (proposta.isCancelar()) {
                proposta.setVisible(true);

                try {
                    valorProposto = Float.parseFloat(proposta.getValorProposto());
                    if (valorProposto > jogador.getContaBancaria().getSaldo()) {
                        JOptionPane.showMessageDialog(null,((JogadorHumano) this.dono).getNome() + " seu saldo é insuficiente.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,((JogadorHumano) this.dono).getNome() + " insira um valor válido.");
                }
            }

            if (proposta.isCancelar()){
                MostrarPedidoDeCompra pedido = new MostrarPedidoDeCompra(((JogadorHumano) this.dono).getNome()+": "+jogador.getNome()+" fez uma Proposta", "Valor: R$ "+valorProposto, this.nome);
                pedido.setVisible(true);

                if (pedido.getAceito()) {
                    JOptionPane.showMessageDialog(null, jogador.getNome()+" comprou "+this.nome+" de "+((JogadorHumano) this.dono).getNome()+" por R$ "+valorProposto);
                    this.numCasas = 0; //IMPLEMENTAR VENDER TODAS AS CASAS/HOTÉIS PARA O BANCO---------------------------------------------------------------------------
                    jogador.pagar(this.preco);
                    this.dono.receber(this.preco);
                    this.dono.venderPropriedade(this);
                    jogador.comprarPropriedade(this);
                    this.dono = jogador;
                } else {
                    JOptionPane.showMessageDialog(null,((JogadorHumano) this.dono).getNome() + " recusou sua proposta.");
                }
            } else {
                JOptionPane.showMessageDialog(null,jogador.getNome() + " desistiu da compra.");
            }
        } else {
            if (jogador.pagarCredor(this.getAluguel())) {
                JOptionPane.showMessageDialog(null,jogador.getNome()+" encerrou o Turno e pagou R$ " + this.getAluguel() + " de aluguel");
                this.dono.receber(this.getAluguel());
            }
        }
    }

    private ComprarPropriedade criarComprarPropriedade(JogadorHumano jogador) {
        String titulo = jogador.getNome() + " alcan�ou " + this.nome;
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
                JOptionPane.showMessageDialog(null,jogador.getNome()+" comprou " + this.nome + " por R$ "+this.preco);
                this.dono.receber(this.preco);
                this.dono.venderPropriedade(this);
                jogador.comprarPropriedade(this);
                this.dono = jogador;
            } else {
                JOptionPane.showMessageDialog(null,jogador.getNome()+"  n�o tem dinheiro suficiente para comprar essa propriedade.");
            }

        } else {
            JOptionPane.showMessageDialog(null,jogador.getNome()+" encerrou o Turno.");
        }
    }
    private void comprarCasas(JogadorHumano jogador) {
        if (ControlBancoImobiliario.getInstance().validarConstrucaoCasa(jogador, this)) {
            if (this.numCasas < 4) {
                if (jogador.pagar(this.precoCasa)) {
                    JOptionPane.showMessageDialog(null,jogador.getNome()+" construiu uma casa.");
                    Banco.getInstance().receber(this.precoCasa);
                    this.numCasas+=1;
                } else {
                    JOptionPane.showMessageDialog(null,jogador.getNome()+" você não possui saldo disponível.");
                }
            } else if (this.numCasas == 4) {
                if (jogador.pagar(this.precoHotel)) {
                    JOptionPane.showMessageDialog(null,jogador.getNome()+" construiu um hotel.");
                    Banco.getInstance().receber(this.precoCasa);
                    this.hasHotel = true;
                } else {
                    JOptionPane.showMessageDialog(null,jogador.getNome()+" você não possui saldo disponível.");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Você não pode mais construir nesse Terreno!");
            }
        } else {
            System.out.println("Não pode construir");
            JOptionPane.showMessageDialog(null,jogador.getNome()+" ainda não pode construir casas neste Terreno.");
        }
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
