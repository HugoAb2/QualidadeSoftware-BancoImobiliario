package br.ufc.pds.view.TelaPrincipal;

import JGamePlay.GameImage;
import JGamePlay.Window;
import br.ufc.pds.model.campo.propriedade.Propriedade;
import br.ufc.pds.model.campo.propriedade.Terreno;
import br.ufc.pds.model.jogador.JogadorHumano;
import br.ufc.pds.model.Peca;

public class TelaPrincipal {
    private Window janelaPrincipal;
    private GameImage background;
    private static TelaPrincipal telaPrincipal = new TelaPrincipal();

    private TelaPrincipal() {
        this.janelaPrincipal = new Window(700, 700);
        this.background = new GameImage("resources//preview.png");
    }

    public static synchronized TelaPrincipal getInstance() {
        return telaPrincipal;
    }

    public void drawBackgroud() {
        this.background.draw();
    }

    public void displayJanelaPrincipal() {
        this.janelaPrincipal.display();
    }

    public void renderPeca(Peca pecaLoc) {
        GameImage peca = new GameImage("resources//pecas//"+ pecaLoc.getCor() +".png");
        peca.setPosition(pecaLoc.obterLocalizacao().getEixoX(), pecaLoc.obterLocalizacao().getEixoY());
        peca.draw();
    }

    public void renderProprietario(Propriedade propriedade) {
        GameImage marcador = new GameImage("resources//marcador//"+ ((JogadorHumano)propriedade.getDono()).getPeca().getCor() +".png");
        marcador.setPosition(propriedade.getEixoX()-10, propriedade.getEixoY()-10);
        marcador.draw();
    }

    public void renderCasasHoteis(Terreno terreno) {
        GameImage marcador;//
        if (!terreno.isHasHotel()) {
            marcador = new GameImage("resources//casas//"+ ((JogadorHumano)terreno.getDono()).getPeca().getCor() + "Casa" + terreno.getNumCasas() +".png");
        } else {
            marcador = new GameImage("resources//hotel//"+ ((JogadorHumano)terreno.getDono()).getPeca().getCor() +"Hotel.png");
        }
        marcador.setPosition(terreno.getEixoX()-10, terreno.getEixoY()+15);
        marcador.draw();
    }

    public void closeJanela() {
        this.janelaPrincipal.exit();
    }
}
