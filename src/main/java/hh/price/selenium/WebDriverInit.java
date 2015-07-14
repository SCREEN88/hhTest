package hh.price.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class WebDriverInit {

    public WebDriver createDriver() {
        return ThreadGuard.protect(new RemoteWebDriver(createURL(), createCapabilities()));
    }

    private static DesiredCapabilities createCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(DefaultProperties.DATA.getString("browser"));
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        return capabilities;
    }

    private static URL createURL() {
        try {
            return new URL(
                "http",
                DefaultProperties.DATA.getString("selenium.hubHost"),
                DefaultProperties.DATA.getInt("selenium.hubPort"), "/wd/hub"
            );
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL.");
        }
        return null;
    }
}
