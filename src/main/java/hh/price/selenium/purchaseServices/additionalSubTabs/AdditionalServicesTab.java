package hh.price.selenium.purchaseServices.additionalSubTabs;

import hh.price.selenium.purchaseServices.RecommendedTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

public class AdditionalServicesTab extends LoadableComponent<AdditionalServicesTab> implements IAdditionalTabs {
    private WebDriver driver;

    public AdditionalServicesTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        new RecommendedTab(driver).get();
        driver.findElement(By.cssSelector("a[href='#additional']")).click();
        selectSubTab(AdditionalServicesSubTabs.RESUME_SPOTLIGHT);
    }

    @Override
    protected void isLoaded() throws Error {
        if (wrongStartUrl() && tabIsNotVisible()) {
            throw new Error();
        }
    }

    public IAdditionalTabs selectSubTab(AdditionalServicesSubTabs tab){
        WebElement subTab = driver.findElement(By.cssSelector(".g-switchrow span:nth-child(" + tab.getTabNumber() + ")"));
        if (firstSubTabIsNotSelected(subTab)){
            subTab.click();
        }
        switch (tab){
            case ADVERTISING_ON_THE_SITE:
                return new AdvertisingOnTheSiteTab(driver);
            case PERSONAL_IN_THE_BALTIC_STATES:
                return new PersonalInTheBalticTab(driver);
            case COMPANY_PROMOTION:
                return new CompanyPromotionTab(driver);
            default:
                return this;
        }
    }

    private boolean firstSubTabIsNotSelected(WebElement subTab) {
        return !subTab.getAttribute("class").contains("m-switchrow__switch_selected");
    }

    private boolean wrongStartUrl() {
        return !driver.getCurrentUrl().contains("#additional");
    }

    private boolean tabIsNotVisible() {
        return !driver.findElement(By.cssSelector(".flat-tabs__body > li:nth-child(4)"))
            .getAttribute("class")
            .contains("g-expand");
    }

    public WebElement getInput(ResumeTypes type){
        return getVacancyType(type).findElement(By.tagName("input"));
    }

    public WebElement getButton(ResumeTypes type){
        return getVacancyType(type).findElement(By.tagName("button"));
    }

    public WebElement getCost(ResumeTypes type){
        return getVacancyType(type).findElement(By.className("price-countable-service__cost"));
    }

    public List<WebElement> getFeatures(ResumeTypes type) {
        return getVacancyType(type).findElements(By.cssSelector(".price-countable-service__features > li"));
    }

    private WebElement getVacancyType(ResumeTypes type) {
        return driver.findElement(By.cssSelector(".g-expand .price-countable-service:nth-child(" + type.getValue() + ")"));
    }

    public enum ResumeTypes{
        SPOTLIGHT(1), REGIONAL_SPOTLIGHT(2);

        private int value;

        ResumeTypes(int num) {
            value = num;
        }

        public int getValue() {
            return value;
        }
    }
}
