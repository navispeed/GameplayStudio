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

  public static GameMode replay(GameMode current) {
    int choice = 0;

    while (choice == 0 || choice > 3) {
      System.out.println("You finished the game, please make a choice:"
          + "\n1. Replay " + current.toString() + " mode"
          + "\n2. Replay (go to gamemode choice)"
          + "\n3. exit"
      );

      choice = new Scanner(System.in).nextInt();
      switch (choice) {
        case 1:
          return current;
        case 2:
          return run();
        case 3:
          System.exit(0);
      }
    }
    //Should not happen
    return null;
  }

}
