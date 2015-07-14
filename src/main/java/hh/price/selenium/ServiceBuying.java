package hh.price.selenium;

import org.openqa.selenium.By;
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
        driver.findElement(By.className("link-secondary")).click();
        new WaitFor(driver).pageTitleInHeader("Выбор города");
        driver.findElement(By.cssSelector(".area-switcher-welcome .area-switcher-welcome__item:nth-child(1) li:nth-child(1) > a")).click();
    }

    @Override
    protected void isLoaded() throws Error {
        new WaitFor(driver).pageTitleInHeader("Покупка услуг");
        if (wrongStartUrl()){
            throw new Error();
        }
    }

    private boolean wrongStartUrl() {
        return !driver.getCurrentUrl().contains(DefaultProperties.DATA.getString("selenium.testURL"));
    }

    public WebDriver getDriver() {
        return driver;
    }
}
