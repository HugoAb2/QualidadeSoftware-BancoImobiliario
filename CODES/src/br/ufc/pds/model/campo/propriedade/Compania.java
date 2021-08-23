package br.ufc.pds.model.campo.propriedade;

import br.ufc.pds.interfaces.EfeitoEspecial;
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
        FazerPropostaCompraPropriedade fpp = new FazerPropostaCompraPropriedade("Essa Companhia pertence a :"+jogador.getNome(), "Valor da Taxa: R$ "+(this.taxa*(jogador.getDados()[0].obterValorDaFace()+jogador.getDados()[1].obterValorDaFace())), "Valor da Companhia: R$ "+this.preco);
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
                    JOptionPane.showMessageDialog(null,((JogadorHumano) this.dono).getNome() + " seu saldo é insuficiente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,((JogadorHumano) this.dono).getNome() + " insira um valor válido.");
            }
        }
    }

    private void mostraPedido(JogadorHumano jogador, float valorProposto){
        MostrarPedidoDeCompra pedido = new MostrarPedidoDeCompra(((JogadorHumano) this.dono).getNome()+": "+jogador.getNome()+" fez uma Proposta", "Valor: R$ "+valorProposto, this.nome);
        pedido.setVisible(true);
        if (pedido.getAceito()) {
            JOptionPane.showMessageDialog(null, jogador.getNome()+" comprou "+this.nome+" de "+((JogadorHumano) this.dono).getNome()+" por R$ "+valorProposto);
            jogador.pagar(this.preco);
            this.dono.receber(this.preco);
            this.dono.venderPropriedade(this);
            jogador.comprarPropriedade(this);
            this.dono = jogador;
        } else {
            JOptionPane.showMessageDialog(null,((JogadorHumano) this.dono).getNome() + " recusou sua proposta.");
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
        String titulo = jogador.getNome() + " alcançou " + this.nome;
        String taxa = "Taxa: R$ "+this.taxa;
        String valor = "Valor: R$ "+this.preco;
        String saldo = "Seu Saldo: R$ " + jogador.getContaBancaria().getSaldo();
        String numPropriedades = "Num. Prop.: " + jogador.getPropriedades().size();
        String dado1 = "Dado1: " + jogador.getDados()[0].obterValorDaFace();
        String dado2 = "Dado2: " + jogador.getDados()[1].obterValorDaFace();

        ComprarPropriedade cp = new ComprarPropriedade(titulo, taxa, valor, saldo, numPropriedades, dado1, dado2);
        cp.setVisible(true);
        return cp.getComprar();
    }

    private void payOrNot(JogadorHumano jogador){
        if (jogador.pagar(this.preco)) {
            JOptionPane.showMessageDialog(null,jogador.getNome()+" comprou " + this.nome + " por R$"+this.preco);
            this.dono.receber(this.preco);
            jogador.comprarPropriedade(this);
            this.dono = jogador;
        } else {
            JOptionPane.showMessageDialog(null,jogador.getNome()+" não tem saldo suficiente para comprar essa companhia!");
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
                        messages("desistiu", jogador);
                        this.computarTaxa(jogador);
                    }
                } else  {
                    this.computarTaxa(jogador);
                }
            }else if(naoDonoIgual(jogador)) {
                if (comprarPropriedade(jogador)) {
                    payOrNot(jogador);
                } else {
                    messages("encerrou", jogador);
                }
            } else {
                messages("visitou", jogador);
            }
    }

    private void messages(String op, JogadorHumano jogador){
	    switch (op){
            case "desistiu":
                JOptionPane.showMessageDialog(null,jogador.getNome() + " desistiu da compra.");
                break;
            case "encerrou":
                JOptionPane.showMessageDialog(null,jogador.getNome()+" encerrou o turno!");
                break;
            case "visitou":
                JOptionPane.showMessageDialog(null,jogador.getNome()+" visitou sua Companhia");
                break;
            default:
                System.out.println("Error");
        }
    }

    private void computarTaxa(JogadorHumano jogador){
        float taxaCobrada = this.taxa * (jogador.getDados()[0].obterValorDaFace() + jogador.getDados()[1].obterValorDaFace());
        if (jogador.pagarCredor(taxaCobrada)) {
            JOptionPane.showMessageDialog(null,jogador.getNome()+" pagou R$" + taxaCobrada);
            this.dono.receber(taxaCobrada);
        }
    }
}
