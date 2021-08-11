package br.ufc.pds.controller;

import br.ufc.pds.model.carta.Carta;
import br.ufc.pds.factory.FactoryCartas;
import br.ufc.pds.interfaces.Iterator;

import java.util.ArrayList;
import java.util.Random;

public class ControlSorteOuReves implements Iterator {

	private ArrayList<Carta> cartas;

	private static ControlSorteOuReves controlSorteOuReves = null;

	public static synchronized ControlSorteOuReves getInstance() {
		if(controlSorteOuReves == null) controlSorteOuReves =  new ControlSorteOuReves();
		return controlSorteOuReves;
	}

	private ControlSorteOuReves(){
		this.cartas = new ArrayList<>();
		this.criarCartas();
	}
	private void criarCartas() {
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "A prefetura mandou abrir uma nova avenida, para o que desapropriou vários prédios. Em consequencia seu terreno valorizou", "Sorte", 25));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Papai os livros do ano passado não servem mais, preciso de livros novos", "Reves", 40));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Seus filhos já vão para a escola. Pague a primeira mensalidade.", "Reves", 50));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Houve um assalto à sua loja, mas você estava assegurado.", "Sorte", 150));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Seu clube está ampliando as piscinas, os sócios devem contribuir", "Reves", 25));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Você acaba de receber a comunicação do Imposto de Renda.", "Reves", 50));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você foi promovido a diretor da sua empresa.", "Sorte", 100));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Você é papai outra vez! Despesas de maternidade.", "Reves", 100));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Parabéns! Você convidou seus amigos para festejar o aniversário.", "Reves", 100));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Um amigo tinha lhe pedido um empréstimo e se esqueceu de devolver. Ele acaba de lembrar.", "Sorte", 80));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você saiu de férias e se hospedou na casa de um amigo. Você economizou o hotel.", "Sorte", 45));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Você estacionou seu carro em lugar proibido e entrou na contra mão", "Reves", 30));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "O médico lhe recomendou repouso num bom hotel de montanha.", "Reves", 45));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "A geada prejudicou a safra de cafés", "Reves", 50));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você trocou seu carro usado com um amigo e ainda saiu lucrando", "Sorte", 50));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "O seu cachorro policial tirou o 1º prêmio na exposição Kennel Club.", "Sorte", 100));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Seus parentes do interior vieram passar umas 'férias' na sua casa.", "Reves", 45));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você jogou na Loteria Esportiva com um grupo de amigos. Ganharam!", "Sorte", 20));
		this.cartas.add(FactoryCartas.getInstance().criar("Revés", "Vá para a prisão sem receber nada. (talvez eu lhe faça uma visita...)", "VaParaPrisao", 0));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Um amigo pediu-lhe um empréstimo. Você não pode recusar.", "Reves", 15));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Inesperadamente você recebeu uma herança que já estava esquecida.", "Sorte", 100));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Você achou interessante assistir à estréia da temporada ballet. Compre os ingressos.", "Reves", 30));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você apostou com os parceiros deste jogo e ganhou", "Aposta", 50));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Saída livre da prisão. (Conserve este cartão para quando lhe for preciso ou negocie-o em qualquer ocasião, por preço a combinar).", "SaiaDaPrisao", 0));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você está com sorte. Suas ações na Bolsa de Valores estão em alta", "Sorte", 200));
		this.cartas.add(FactoryCartas.getInstance().criar("Reves", "Renove a tempo a licença do seu automóvel.", "Reves", 30));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você acaba de receber uma parcela do seu 13º salário.", "Sorte", 50));
		this.cartas.add(FactoryCartas.getInstance().criar("Sorte", "Você tirou o primeiro lugar no Torneio de Tẽnis do seu clube. Parabéns.", "Sorte", 100));
	}

	@Override
	public Object first() {
		return this.cartas.get(0);
	}

	@Override
	public Object next() {
		if (cartas.isEmpty()) {
			this.criarCartas();
		}
		Random random = new Random();
		int i = random.nextInt(this.cartas.size());
		return this.cartas.remove(i);
	}

	@Override
	public boolean isDone() {
		return false;
	}
}
