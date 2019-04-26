package eu.navispeed.oc.combinaison.manager;

import static eu.navispeed.oc.combinaison.game.Game.Status.ATTACKER_WIN;

import eu.navispeed.oc.combinaison.game.Combinaison;
import eu.navispeed.oc.combinaison.game.Game;
import eu.navispeed.oc.combinaison.player.AI;
import eu.navispeed.oc.combinaison.player.Human;
import eu.navispeed.oc.combinaison.player.Player;

public class GameFactory {

  public static Runnable createGame(GameMode mode) {
    Player a = null;
    Player b = null;
    switch (mode) {
      case DUEL:
        return () -> {
          Game g1 = new Combinaison();
          Game g2 = new Combinaison();

          g1.setPlayers(new Human(), new AI(Configuration.getInstance().getLength()));
          g2.setPlayers(new AI(Configuration.getInstance().getLength()), new Human());
          while (true) {
            System.out.println("---------- Player 1 (human) turn ----------");
            g1.runTurn();
            if (g1.getCurrentStatus() == ATTACKER_WIN) {
              System.out.println("Human win");
              break;
            }
            System.out.println("---------- Player 2 (computer) turn ----------");
            g2.runTurn();
            if (g2.getCurrentStatus() == ATTACKER_WIN) {
              System.out.println("Computer win");
              break;
            }
          }
        };
      case CHALLENGER:
        a = new Human();
        b = new AI(Configuration.getInstance().getLength());
        break;
      case DEFENDER:
        a = new AI(Configuration.getInstance().getLength());
        b = new Human();
        break;
    }
    Player finalB = b;
    Player finalA = a;
    return () -> {
      Game g = new Combinaison();

      g.setPlayers(finalA, finalB);
      while (g.getCurrentStatus() != ATTACKER_WIN) {
        g.runTurn();
      }
    };
  }

}
