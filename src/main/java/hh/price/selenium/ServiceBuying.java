package hh.price.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public class ServiceBuying extends LoadableComponent<ServiceBuying> {
    private WebDriver driver;

    public ServiceBuying(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(DefaultProperties.DATA.getString("selenium.testURL"));
    }

    @Override
    protected void isLoaded() throws Error {
        if (!driver.getCurrentUrl().equals(DefaultProperties.DATA.getString("selenium.testURL"))){
            throw new Error();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
