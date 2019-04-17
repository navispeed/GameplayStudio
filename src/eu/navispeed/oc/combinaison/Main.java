package eu.navispeed.oc.combinaison;

import eu.navispeed.oc.combinaison.game.Combinaison;
import eu.navispeed.oc.combinaison.game.Game;
import eu.navispeed.oc.combinaison.player.Human;

public class Main {

  public static void main(String[] args) {

    Game g = new Combinaison();

    g.setPlayers(new Human(), new Human());
    while (g.getCurrentStatus() != Game.Status.ATTACKER_WIN) {
      g.runTurn();
    }

  }
}
