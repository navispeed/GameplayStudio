package eu.navispeed.oc.combinaison;

import eu.navispeed.oc.combinaison.manager.Configuration;
import eu.navispeed.oc.combinaison.manager.GameFactory;
import eu.navispeed.oc.combinaison.manager.GameMode;
import eu.navispeed.oc.combinaison.manager.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) throws IOException {
    logger.info("Run");
    Properties properties = new Properties();
    try (InputStream is = new FileInputStream("./application.properties")) {
      properties.load(is);
    }
    Configuration.buildFromProperties(properties);

    final GameMode firstGamemode = Menu.run();
    GameFactory.createGame(firstGamemode, Configuration.getInstance()).run();
    while (true) {
      final GameMode replay = Menu.replay(firstGamemode);
      GameFactory.createGame(replay, Configuration.getInstance()).run();
    }
  }
}
