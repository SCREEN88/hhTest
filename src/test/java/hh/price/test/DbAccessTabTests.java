package hh.price.test;

import hh.price.selenium.purchaseServices.DbAccessTab;
import hh.price.selenium.purchaseServices.PriceCart;
import hh.price.selenium.utils.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbAccessTabTests extends DriverInit {

    @Test
    public void formActualPriceChange() {
        DbAccessTab dbTab = new DbAccessTab(getDriver()).get();
        WebElement exRadio = dbTab.expressSelectionRadio;
        String exPrice = exRadio.findElement(By.tagName("span")).getText();
        exRadio.findElement(By.tagName("label")).click();
        assert exPrice.equals(dbTab.costSummery.getText());
        WebElement spRadio = dbTab.specialistSearchRadio;
        String spPrice = spRadio.findElement(By.tagName("span")).getText();
        spRadio.findElement(By.tagName("label")).click();
        assert spPrice.equals(dbTab.costSummery.getText());
    }

    @Test
    public void changeRegion() {
        DbAccessTab dbTab = new DbAccessTab(getDriver()).get();
        dbTab.changeRegion.click();
        WebElement popup = getPopup();
        List<WebElement> checkBoxes = popup.findElements(By.className("price-resume-access__part-item"));
        ArrayList<String> regions = new ArrayList<>();
        regions.add(checkBoxes.get(1).getText());
        checkBoxes.get(1).click();
        regions.add(checkBoxes.get(93).getText());
        checkBoxes.get(93).click();
        regions.add(checkBoxes.get(98).getText());
        checkBoxes.get(98).click();
        popup.findElement(By.className("bloko-button")).click();
        String selected = dbTab.regionValues.getText();
        for (String region : regions) {
            assert selected.contains(region);
        }
    }

    @Test
    public void changeProfession() {
        DbAccessTab dbTab = new DbAccessTab(getDriver()).get();
        dbTab.changeRegion.click();
        WebElement regionPopup = getPopup();
        List<WebElement> regionCheckBoxes = regionPopup.findElements(By.className("price-resume-access__part-item"));
        regionCheckBoxes.get(0).click();
        regionPopup.findElement(By.className("bloko-button")).click();
        dbTab.changeIndustry.click();
        WebElement industryPopup = getPopup();
        List<WebElement> industryCheckBoxes = industryPopup.findElements(By.className("price-resume-access__part-item"));
        ArrayList<String> industries = new ArrayList<>();
        industries.add(industryCheckBoxes.get(1).getText());
        industryCheckBoxes.get(1).click();
        industries.add(industryCheckBoxes.get(5).getText());
        industryCheckBoxes.get(5).click();
        industries.add(industryCheckBoxes.get(9).getText());
        industryCheckBoxes.get(9).click();
        industryPopup.findElement(By.className("bloko-button")).click();
        String selected = dbTab.industryValues.getText();
        for (String industry : industries) {
            assert selected.contains(industry);
        }
    }

    private WebElement getPopup() {
        new WaitFor(getDriver()).elementToAppear(By.className("b-popup"));
        return getDriver().findElement(By.className("b-popup"));
    }

    @Test
    public void addSelectedToCart(){
        DbAccessTab dbTab = new DbAccessTab(getDriver()).get();
        WebElement selected = dbTab.allRadioButton.get(4);
        String value = selected.findElement(By.tagName("span")).getText().replace("руб.", "").replace(" ", "");
        selected.click();
        getDriver().findElement(By.className("HH-Price-ResumeAccess-AddToCartButton")).click();
        PriceCart cart = new PriceCart(getDriver().findElement(By.className("HH-PriceCart")));
        new WaitFor(getDriver()).elementToDisplay(cart.getPriceSum());
        List<Map<String,String>> cartData = cart.collectCartData();
        assert cartData.get(0).get("actualCost").equals(value);
    }
}
