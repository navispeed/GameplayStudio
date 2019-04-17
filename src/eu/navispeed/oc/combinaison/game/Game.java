package eu.navispeed.oc.combinaison.game;

import eu.navispeed.oc.combinaison.player.Player;

public interface Game {

  enum Status {
    CONTINUE,
    ATTACKER_WIN,
    DEFENDER_WIN,
    NO_WINNER,
  }

  void setPlayers(Player attacker, Player defender);

  void runTurn();

  Status getCurrentStatus();

}
