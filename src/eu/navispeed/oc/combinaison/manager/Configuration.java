package eu.navispeed.oc.combinaison.manager;

import java.util.Properties;

public class Configuration {

  private static Configuration instance;

  private boolean devMode;
  private int maxTry;
  private int length;

  private Configuration() {

  }

  public static Configuration getInstance() {
    return instance;
  }

  public static void buildFromProperties(Properties properties) {
    instance = new Configuration();
    instance.devMode = Boolean.valueOf(properties.getProperty("application.dev", "false"));
    instance.maxTry = Integer.valueOf(properties.getProperty("combinaison.max_try", "10"));
    instance.length = Integer.valueOf(properties.getProperty("combinaison.length", "4"));
  }

  public boolean isDevMode() {
    return devMode;
  }

  public int getMaxTry() {
    return maxTry;
  }

  public int getLength() {
    return length;
  }

  public void setDevMode(boolean devMode) {
    this.devMode = devMode;
  }

  public void setMaxTry(int maxTry) {
    this.maxTry = maxTry;
  }

  public void setLength(int length) {
    this.length = length;
  }
}
