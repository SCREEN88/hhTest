package hh.price.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitFor {
    private final WebDriver driver;
    private final int       maxRedirectTime;
    private final int       maxIsDisplayedTime;

    public WaitFor(WebDriver driver) {
        this.driver = driver;
        this.maxRedirectTime = DefaultProperties.DATA.getInt("hh.waitFor.pageTitleInHeader.maxTime");
        this.maxIsDisplayedTime = DefaultProperties.DATA.getInt("hh.waitFor.elementToDisplay.maxTime");
    }

    public void elementToDisplay(WebElement element) {
        waitFor(maxIsDisplayedTime).until(ExpectedConditions.visibilityOf(element));
    }

    public void elementToHide(WebElement element) {
        waitFor(maxIsDisplayedTime).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    public void elementIsClickable(WebElement element) {
        waitFor(maxIsDisplayedTime).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void pageTitleInHeader(String expectedTitle) {
        waitFor(maxRedirectTime).until(ExpectedConditions.titleContains(expectedTitle));
    }

    public void elementToAppear(By expectedElementLocation) {
        waitFor(maxIsDisplayedTime).until(ExpectedConditions.presenceOfElementLocated(expectedElementLocation));
    }

    public void elementTextChange(WebElement element, String text) {
        waitFor(maxIsDisplayedTime).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void condition(ExpectedCondition<Boolean> expectedCondition) {
        waitFor(maxIsDisplayedTime).until(expectedCondition);
    }

    private WebDriverWait waitFor(Integer time) {
        return new WebDriverWait(driver, time);
    }
}
