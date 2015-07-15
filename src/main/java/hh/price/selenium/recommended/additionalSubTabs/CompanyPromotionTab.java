package hh.price.selenium.recommended.additionalSubTabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CompanyPromotionTab implements IAdditionalTabs {
    private final WebDriver driver;

    @FindBy(css = ".l-insider-l-column .b-accordion")
    public List<WebElement> accordions;

    public CompanyPromotionTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
