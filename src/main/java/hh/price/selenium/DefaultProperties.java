package hh.price.selenium;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class DefaultProperties {
    public final static Config DATA;
    private final static String PATH = "config/" + System.getProperty("env") + "/";

    static {
        DATA = ConfigFactory.load("defaults");
    }
}
