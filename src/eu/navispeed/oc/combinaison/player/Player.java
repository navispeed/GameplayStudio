package eu.navispeed.oc.combinaison.player;

public interface Player {

  int askRandomSequence();

  int askSolution();

  void sendIndice(char[] indice);


}
