package hh.price.test;

import hh.price.selenium.utils.DefaultProperties;
import hh.price.selenium.utils.SeleniumServerInit;
import hh.price.selenium.utils.WebDriverInit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class DriverInit {

    protected ThreadLocal<WebDriver> pages = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    void init() {
        try {
            SeleniumServerInit.INITIALIZE.start();
        } catch (Exception e) {
            System.out.println("Couldn't start server! /n" + e);
        }
    }

    public WebDriver getDriver(){
        return pages.get();
    }

    @BeforeMethod
    public void start() {
        WebDriver driver = new WebDriverInit().createDriver();
        driver.get(DefaultProperties.DATA.getString("selenium.testURL"));
        driver.manage().deleteAllCookies();
        pages.set(driver);
    }

    @AfterMethod
    public void stop(){
        pages.get().quit();
    }

    @AfterSuite
    public void tearDownSuite() {
        SeleniumServerInit.INITIALIZE.stop();
    }
}
