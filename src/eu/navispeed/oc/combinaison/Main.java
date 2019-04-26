package eu.navispeed.oc.combinaison;

import eu.navispeed.oc.combinaison.manager.Configuration;
import eu.navispeed.oc.combinaison.manager.GameFactory;
import eu.navispeed.oc.combinaison.manager.Menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

  public static void main(String[] args) throws IOException {

    Properties properties = new Properties();
    try (InputStream is = new FileInputStream("./application.properties")) {
      properties.load(is);
    }
    Configuration.buildFromProperties(properties);

    GameFactory.createGame(Menu.run()).run();


    //    while (true) {
    //      Game g = new Combinaison();
    //
    //      g.setPlayers(new AI(Configuration.getInstance().getLength()), new AI(Configuration.getInstance().getLength()));
    //      while (g.getCurrentStatus() != Game.Status.ATTACKER_WIN) {
    //        g.runTurn();
    //      }
    //
    //    }

  }
}
