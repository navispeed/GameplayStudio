package eu.navispeed.oc.combinaison.manager;

import eu.navispeed.oc.combinaison.manager.GameMode;

import java.util.Scanner;

public class Menu {

  public static GameMode run() {
    int choice = 0;

    while (choice == 0 || choice > 3) {
      System.out.println("Please choose a gamemode :"
          + "\n1. Challenger"
          + "\n2. Defender"
          + "\n3. Duel"
      );

      choice = new Scanner(System.in).nextInt();
    }
    final GameMode[] values = GameMode.values();
    for (int i = 0; i < values.length; ++i) {
      if (i == choice - 1) {
        return values[i];
      }
    }
    //Should not happen
    return null;
  }

}
