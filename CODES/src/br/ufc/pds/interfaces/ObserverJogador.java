package br.ufc.pds.interfaces;

import br.ufc.pds.model.jogador.JogadorHumano;

public interface ObserverJogador {
    void update(JogadorHumano jogador, float valor);
}
