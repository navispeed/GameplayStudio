package eu.navispeed.oc.combinaison.player;

import java.util.Arrays;
import java.util.Random;

public class AI implements Player {

  private int[] toPlay;
  private final int length;

  public AI(int length) {
    this.length = length;
    this.toPlay = new int[length];
    for (int i = 0; i < length; ++i) {
      toPlay[i] = 5;
    }
  }

  @Override public int askRandomSequence() {
    final int i = Math.abs(new Random().nextInt((int) Math.pow(10, length) - (int) Math.pow(10, length - 1)) + (int) Math.pow(10, length - 1));
    System.out.println("You have to find " + i);
    return i;
  }

  @Override public int askSolution() {
    final int i = Arrays.stream(toPlay).reduce(0, (acc, current) -> (acc + current) * 10) / 10;
    System.out.println("I send " + i);
    return i;
  }

  @Override public void sendIndice(char[] indice) {
    System.out.println("received " + Arrays.toString(indice));
    for (int i = 0; i < indice.length; ++i) {
      if (indice[i] == '-') {
        toPlay[i]++;
      }
      if (indice[i] == '+') {
        toPlay[i]--;
      }
    }
  }
}
