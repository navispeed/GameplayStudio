package eu.navispeed.oc.combinaison.game;

import eu.navispeed.oc.combinaison.manager.Configuration;
import eu.navispeed.oc.combinaison.player.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Combinaison implements Game {

  private final int maxTry;
  private Player attacker;
  private Player defender;
  private int[] combinaison;
  private int[] lastTurn;
  private int currentTurn = 0;

  public Combinaison(int maxTry) {
    this.maxTry = maxTry;
  }

  @Override public void setPlayers(Player attacker, Player defender) {
    this.attacker = attacker;
    this.defender = defender;
  }

  @Override public void runTurn() {
    if (lastTurn == null) {
      combinaison = convert(defender.askRandomSequence());
    }
    if (Configuration.getInstance().isDevMode()) {
      System.out.println("Remember, you have to find " + Arrays.toString(combinaison));
    }

    final int solutionFromAttacker = attacker.askSolution();
    lastTurn = convert(solutionFromAttacker);
    char[] indice = null;
    while (indice == null) {
      try {
        indice = compute(lastTurn);
      } catch (IllegalArgumentException excep) {
      }
    }
    attacker.sendIndice(indice);
    currentTurn++;
  }

  @Override public Status getCurrentStatus() {
    return lastTurn != null && Arrays.equals(combinaison, lastTurn) ?
        Status.ATTACKER_WIN :
        (currentTurn == maxTry ? Status.DEFENDER_WIN : Status.CONTINUE);
  }

  private int[] convert(final int toConvert) {
    int count = 0;
    int nb = toConvert;
    int result[];
    while (nb != 0) {
      nb /= 10;
      count++;
    }
    result = new int[count];
    nb = toConvert;
    count = 0;
    while (nb != 0) {
      result[count] = nb % 10;
      count++;
      nb /= 10;
    }
    final List<Integer> list = Arrays.stream(result).boxed().collect(Collectors.toList());
    Collections.reverse(list);
    return Arrays.stream(list.toArray(new Integer[0])).mapToInt(i -> i).toArray();
  }

  private char[] compute(int[] playerChoice) throws IllegalArgumentException {
    char result[];
    if (combinaison.length != playerChoice.length) {
      throw new IllegalArgumentException(
          "You should send response with " + combinaison.length + " digits");
    }
    result = new char[playerChoice.length];
    for (int i = 0; i < playerChoice.length; i++) {
      if (combinaison[i] == playerChoice[i]) {
        result[i] = '=';
      } else if (combinaison[i] < playerChoice[i]) {
        result[i] = '+';
      } else if (combinaison[i] > playerChoice[i]) {
        result[i] = '-';
      }
    }
    return result;
  }
}
