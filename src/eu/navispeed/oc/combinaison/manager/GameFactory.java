package eu.navispeed.oc.combinaison.manager;

import static eu.navispeed.oc.combinaison.game.Game.Status.ATTACKER_WIN;
import static eu.navispeed.oc.combinaison.game.Game.Status.DEFENDER_WIN;

import eu.navispeed.oc.combinaison.game.Combinaison;
import eu.navispeed.oc.combinaison.game.Game;
import eu.navispeed.oc.combinaison.player.AI;
import eu.navispeed.oc.combinaison.player.Human;
import eu.navispeed.oc.combinaison.player.Player;


public class GameFactory {

  public static Runnable createGame(GameMode mode, Configuration configuration) {
    Player a = null;
    Player b = null;
    switch (mode) {
      case DUEL:
        return getDuelMode(configuration);
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
    return getGenericMode(configuration, finalB, finalA);
  }

  private static Runnable getGenericMode(Configuration configuration, Player finalB,
      Player finalA) {
    return () -> {
      Game g = new Combinaison(configuration.getMaxTry());

      g.setPlayers(finalA, finalB);
      while (g.getCurrentStatus() == Game.Status.CONTINUE) {
        g.runTurn();
      }
      if (g.getCurrentStatus() == ATTACKER_WIN) {
        System.out.println("Attacker win");
      }
      if (g.getCurrentStatus() == DEFENDER_WIN) {
        System.out.println("Defender win");
      }
    };
  }

  private static Runnable getDuelMode(Configuration configuration) {
    return () -> {
      Game g1 = new Combinaison(configuration.getMaxTry());
      Game g2 = new Combinaison(configuration.getMaxTry());

      g1.setPlayers(new Human(), new AI(Configuration.getInstance().getLength()));
      g2.setPlayers(new AI(Configuration.getInstance().getLength()), new Human());
      while (true) {
        System.out.println("---------- Player 1 (human) turn ----------");
        g1.runTurn();
        if (g1.getCurrentStatus() == ATTACKER_WIN || g2.getCurrentStatus() == DEFENDER_WIN) {
          System.out.println("Human win");
          break;
        }
        System.out.println("---------- Player 2 (computer) turn ----------");
        g2.runTurn();
        if (g2.getCurrentStatus() == ATTACKER_WIN || g1.getCurrentStatus() == DEFENDER_WIN) {
          System.out.println("Computer win");
          break;
        }
      }
    };
  }

}
