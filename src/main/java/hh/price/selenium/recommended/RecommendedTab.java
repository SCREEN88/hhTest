package hh.price.selenium.recommended;

import hh.price.selenium.utils.DefaultProperties;
import hh.price.selenium.utils.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendedTab extends LoadableComponent<RecommendedTab> {
    private WebDriver driver;

    @FindBy(css = ".m-colspan2 .price-spoffers__button button")
    public List<WebElement> formButtons;

    @FindBy(css = ".m-colspan2")
    private List<WebElement> offers;

    public RecommendedTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get(DefaultProperties.DATA.getString("selenium.testURL"));
        driver.findElement(By.className("link-secondary")).click();
        new WaitFor(driver).pageTitleInHeader("Выбор города");
        driver.findElement(By.cssSelector(
            ".area-switcher-welcome .area-switcher-welcome__item:nth-child(1) li:nth-child(1) > a")).click();
    }

    @Override
    protected void isLoaded() throws Error {
        new WaitFor(driver).pageTitleInHeader("Покупка услуг");
        if (wrongStartUrl()) {
            throw new Error();
        }
    }

    private boolean wrongStartUrl() {
        return !driver.getCurrentUrl().contains(DefaultProperties.DATA.getString("selenium.testURL"));
    }

    public List<Map<String,String>> collectOffersData(){
        ArrayList<Map<String,String>> list = new ArrayList<>();
        for (WebElement offer : offers) {
            HashMap<String, String> data = new HashMap<>();
            data.put("title", offer.findElement(By.className("price-spoffers__special-offer-title")).getText());
            List<WebElement> oldPrice = offer.findElements(By.className("price-spoffers__old-price"));
            if (oldPrice.size() > 0){
                data.put("oldPrice", oldPrice.get(0).getText());
            }
            data.put("price", offer.findElement(By.className("price-spoffers__actual-price")).getText());
            data.put("bonus", offer.findElement(By.className("price-spoffers__special-offer-plus")).getText());
            list.add(data);
        }
        return list;
    }
}
