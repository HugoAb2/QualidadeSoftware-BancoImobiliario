package br.ufc.pds.model.campo.propriedade;

import br.ufc.pds.interfaces.EfeitoEspecial;
import br.ufc.pds.model.campo.Campo;
import br.ufc.pds.model.jogador.Banco;
import br.ufc.pds.model.jogador.Jogador;
import br.ufc.pds.model.jogador.JogadorHumano;
import br.ufc.pds.view.ComprarPropriedade;
import br.ufc.pds.view.FazerPropostaCompraPropriedade;
import br.ufc.pds.view.InserirProposta;
import br.ufc.pds.view.MostrarPedidoDeCompra;

import javax.swing.*;

public class Compania extends Propriedade implements EfeitoEspecial {

	private float taxa;

	public Compania(String nome, int indice, float preco, Jogador dono, float taxa, int eixoX, int eixoY) {
		super(nome, indice, preco, dono, eixoX, eixoY);
		this.taxa = taxa;
	}

	private boolean naoDono(JogadorHumano jogador){
        if(!this.dono.equals(Banco.getInstance()) && !this.dono.equals(jogador)){
            return true;
        }
        else return false;
    }

    private boolean fpp(JogadorHumano jogador){
        FazerPropostaCompraPropriedade fpp = new FazerPropostaCompraPropriedade("Essa Companhia pertence a :"+getNome(jogador), "Valor da Taxa: R$ "+(this.taxa*(jogador.getDados()[0].obterValorDaFace()+jogador.getDados()[1].obterValorDaFace())), "Valor da Companhia: R$ "+this.preco);
        fpp.setVisible(true);
        if(fpp.getComprar()){
            return true;
        }else return false;
    }

    private void setVisibleProposta(InserirProposta proposta){
	    proposta.setVisible(true);
    }

    private void loop(JogadorHumano jogador, InserirProposta proposta, float valorProposto){
        while (isCancelarProposta(proposta)) {
            setVisibleProposta(proposta);

            try {
                valorProposto = Float.parseFloat(proposta.getValorProposto());
                if (valorProposto > jogador.getContaBancaria().getSaldo()) {
                    JOptionPane.showMessageDialog(null,getNomeDono(((JogadorHumano) this.dono)) + " seu saldo é insuficiente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,getNomeDono(((JogadorHumano) this.dono)) + " insira um valor válido.");
            }
        }
    }

    private void mostraPedido(JogadorHumano jogador, float valorProposto){
        MostrarPedidoDeCompra pedido = new MostrarPedidoDeCompra(getNomeDono(((JogadorHumano) this.dono))+": "+getNome(jogador)+" fez uma Proposta", "Valor: R$ "+valorProposto, this.nome);
        pedido.setVisible(true);
        if (pedido.getAceito()) {
            JOptionPane.showMessageDialog(null, getNome(jogador)+" comprou "+this.nome+" de "+getNomeDono(((JogadorHumano) this.dono))+" por R$ "+valorProposto);
            jogador.getContaBancaria().pagar(this.preco);
            this.dono.receber(this.preco);
            this.dono.venderPropriedade(this);
            jogador.comprarPropriedade(this);
            this.dono = jogador;
        } else {
            JOptionPane.showMessageDialog(null,getNomeDono(((JogadorHumano) this.dono)) + " recusou sua proposta.");
            this.computarTaxa(jogador);
        }
    }

    private boolean naoDonoIgual(JogadorHumano jogador){
        if(!this.dono.equals(jogador) && this.dono.equals(Banco.getInstance())){
            return true;
        }
        return false;
    }

    private boolean comprarPropriedade(JogadorHumano jogador){
        String titulo = getNome(jogador) + " alcançou " + this.nome;
        String taxa = "Taxa: R$ "+this.taxa;
        String valor = "Valor: R$ "+this.preco;
        String saldo = "Seu Saldo: R$ " + jogador.getSaldo();
        String numPropriedades = "Num. Prop.: " + jogador.getPropriedades().size();
        String dado1 = "Dado1: " + jogador.getDados()[0].obterValorDaFace();
        String dado2 = "Dado2: " + jogador.getDados()[1].obterValorDaFace();

        ComprarPropriedade cp = new ComprarPropriedade(titulo, taxa, valor, saldo, numPropriedades, dado1, dado2);
        cp.setVisible(true);
        return cp.getComprar();
    }

    private void payOrNot(JogadorHumano jogador){
        if (jogador.getContaBancaria().pagar(this.preco)) {
            JOptionPane.showMessageDialog(null,getNome(jogador)+" comprou " + this.nome + " por R$"+this.preco);
            this.dono.receber(this.preco);
            jogador.comprarPropriedade(this);
            this.dono = jogador;
        } else {
            JOptionPane.showMessageDialog(null,getNome(jogador)+" não tem saldo suficiente para comprar essa companhia!");
        }
    }

    private boolean isCancelarProposta(InserirProposta proposta){
	    return proposta.isCancelar();
    }

    public void aplicarEfeito(JogadorHumano jogador) {
            if (naoDono(jogador)) {
                if (fpp(jogador)) {
                    InserirProposta proposta = new InserirProposta("Faça uma Proposta", "Saldo: R$ "+jogador.getContaBancaria().getSaldo(), "Valor da Companhia: R$ "+this.preco, this.preco);
                    float valorProposto = 0;
                    loop(jogador, proposta, valorProposto);

                    if (isCancelarProposta(proposta)){
                        mostraPedido(jogador, valorProposto);
                    } else {
                        desistiu(jogador);
                        this.computarTaxa(jogador);
                    }
                } else  {
                    this.computarTaxa(jogador);
                }
            }else if(naoDonoIgual(jogador)) {
                if (comprarPropriedade(jogador)) {
                    payOrNot(jogador);
                } else {
                    encerrou(jogador);
                }
            } else {
                visitou(jogador);
            }
    }

    private String getNomeDono(JogadorHumano jogador){ return  jogador.getNomeDono();}

    private String getNome(JogadorHumano jogador){
	    return Campo.getNomeJogador(jogador);
    }

    private void mensagemTela(String txt){
	    JOptionPane.showMessageDialog(null, txt);
    }

    private void desistiu(JogadorHumano jogador){ mensagemTela(getNome(jogador) + " desistiu da compra.");}

    private void encerrou(JogadorHumano jogador){ mensagemTela(getNome(jogador)+" encerrou o turno!");}

    private void visitou(JogadorHumano jogador){ mensagemTela(getNome(jogador)+" visitou sua Compania");}

    private void computarTaxa(JogadorHumano jogador){
        float taxaCobrada = this.taxa * (jogador.getDados()[0].obterValorDaFace() + jogador.getDados()[1].obterValorDaFace());
        if (jogador.pagarCredor(taxaCobrada)) {
            JOptionPane.showMessageDialog(null,getNome(jogador)+" pagou R$" + taxaCobrada);
            this.dono.receber(taxaCobrada);
        }
    }
}
