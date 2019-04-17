package eu.navispeed.oc.combinaison.player;

import java.util.Arrays;
import java.util.Scanner;

public class Human implements Player {

  private Scanner sc = new Scanner(System.in);

  @Override public int askRandomSequence() {
    System.out.println("Random");
    return sc.nextInt();
  }

  @Override public int askSolution() {
    System.out.println("Solution");
    return sc.nextInt();
  }

  @Override public void sendIndice(char[] indice) {
    System.out.println("indice: " + Arrays.toString(indice));
  }
}
