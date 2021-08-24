package br.ufc.pds;

import br.ufc.pds.model.jogador.JogadorHumano;

import java.util.Scanner;

public class Teste {

    private static Scanner input = new Scanner(System.in);

    public static String nomeJogador(int index) {
        System.out.println("Qual o nome do Jogador " + index + "?");
        return input.nextLine();
    }

    public static int numJogadores() {
        System.out.println("Qual a quantidade de Jogadores? Min 2 e Max 6");
        return  Integer.parseInt(input.nextLine());
    }

    public static void esperaQualquer() {
        System.out.println("Aperte Enter");
        String t = input.nextLine();
    }

    public static void informaAvancoJogador(JogadorHumano jogadorHumano) {
        System.out.println();
        System.out.println(jogadorHumano.getNome() + " avançou até o campo " + jogadorHumano.getPeca().obterLocalizacao().getIndice());
        System.out.println(jogadorHumano.getPeca().obterLocalizacao().getNome());
        System.out.println();
    }

    private static int strToInt(String txt){
        return Integer.parseInt(txt);
    }

    private static void sysMsg(String msg){
        System.out.println(msg);
    }

    public static int opcaoDoJogadorPropriedadeSemDono() {
        sysMsg("Escolha: ");
        sysMsg("1- Comprar Propriedade;");
        sysMsg("0- Encerrar Turno");
        return strToInt(input.nextLine());
    }

    public static int opcaoDoJogadorPropriedadeComDono() {
        sysMsg("Escolha: ");
        sysMsg("1- Fazer uma Oferta pela Propriedade;");
        sysMsg("0- Encerrar Turno");
        return  strToInt(input.nextLine());
    }

    public static boolean pedidoDeCompra(float valor) {
        sysMsg("Deseja Vender a Propriedade por R$" + valor + "?\nEscolha:");
        sysMsg("1- Sim.");
        sysMsg("0- Não.");
        if (input.nextInt() == 1)
            return true;
        return false;
    }
}
