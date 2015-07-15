package hh.price.selenium.recommended;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

public class DbAccessTab extends LoadableComponent<DbAccessTab> {
    private WebDriver        driver;

    @FindBy(css = "a[href='#dbaccess']")
    private WebElement dbAccess;

    //"//div[@class='price-resume-access__periods'][2]//label"
    @FindBy(css = ".price-resume-access__periods .price-resume-access__part-item")
    public List<WebElement> allRadioButton;

    @FindBy(css = ".price-resume-access__periods .HH-Price-ResumeAccess-Period_short")
    public WebElement expressSelectionRadio;

    @FindBy(css = ".price-resume-access__periods .HH-Price-ResumeAccess-Period_medium")
    public WebElement specialistSearchRadio;

    @FindBy(css = ".price-resume-access__periods .HH-Price-ResumeAccess-Period_long")
    public WebElement regularSelectionRadio;

    @FindBy(className = "price-spoffers-item__cost")
    public WebElement costSummery;

    @FindBy(css = ".HH-Price-ResumeAccess-Region .link-switch")
    public WebElement changeRegion;

    @FindBy(className = "HH-Price-ResumeAccess-ChangeProfArea")
    public WebElement changeIndustry;

    @FindBy(className = "price-resume-access__part-item-selected")
    public List<WebElement> selectedValues;

    @FindBy(css = ".price-resume-access__summary label")
    public WebElement summeryLabel;

    public DbAccessTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        new RecommendedTab(driver).get();
        dbAccess.click();
    }

    @Override
    protected void isLoaded() throws Error {
        if (wrongStartUrl() && tabIsNotVisible()) {
            throw new Error();
        }
    }

    private boolean wrongStartUrl() {
        return !driver.getCurrentUrl().contains("#dbaccess");
    }

    private boolean tabIsNotVisible() {
        return !driver.findElement(By.cssSelector(".flat-tabs__body > li:nth-child(2)"))
            .getAttribute("class")
            .contains("g-expand");
    }
}