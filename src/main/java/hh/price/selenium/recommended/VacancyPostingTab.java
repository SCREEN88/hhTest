package hh.price.selenium.recommended;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

public class VacancyPostingTab extends LoadableComponent<VacancyPostingTab> {
    private WebDriver driver;

    public VacancyPostingTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        new RecommendedTab(driver).get();
        driver.findElement(By.cssSelector("a[href='#publications']")).click();
    }

    @Override
    protected void isLoaded() throws Error {
        if (wrongStartUrl() && tabIsNotVisible()) {
            throw new Error();
        }
    }

    private boolean wrongStartUrl() {
        return !driver.getCurrentUrl().contains("#publications");
    }

    private boolean tabIsNotVisible() {
        return !driver.findElement(By.cssSelector(".flat-tabs__body > li:nth-child(3)"))
            .getAttribute("class")
            .contains("g-expand");
    }

    public WebElement getInput(VacancyType type){
        return getVacancyType(type).findElement(By.tagName("input"));
    }

    public WebElement getButton(VacancyType type){
        return getVacancyType(type).findElement(By.tagName("button"));
    }

    public WebElement getCost(VacancyType type){
        return getVacancyType(type).findElement(By.className("price-countable-service__cost"));
    }

    public List<WebElement> getDiscountPrices(VacancyType type) {
        return getVacancyType(type).findElements(By.className("HH-Price-CountableService-DiscountRate"));
    }

    public List<WebElement> getFeatures(VacancyType type) {
        return getVacancyType(type).findElements(By.cssSelector(".price-countable-service__features > li"));
    }

    private WebElement getVacancyType(VacancyType type) {
        return driver.findElement(By.cssSelector(".g-expand .price-countable-service:nth-child(" + type.getValue() + ")"));
    }

}
