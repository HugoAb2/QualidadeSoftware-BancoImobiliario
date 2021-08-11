package br.ufc.pds.interfaces;

public interface SubjectObserver {
    void addObserver(ObserverJogador o);
    void removeObserver(ObserverJogador o);
    void notifyObserver(float valor);
}
