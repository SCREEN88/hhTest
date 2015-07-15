package hh.price.test;

import hh.price.selenium.recommended.DbAccessTab;
import hh.price.selenium.utils.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

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
        new WaitFor(getDriver()).elementToAppear(By.className("b-popup"));
        WebElement popup = getDriver().findElement(By.className("b-popup"));
        List<WebElement> checkBoxes = popup.findElements(By.className("price-resume-access__part-item"));
        ArrayList<String> regions = new ArrayList<>();
        regions.add(checkBoxes.get(1).getText());
        checkBoxes.get(1).click();
        regions.add(checkBoxes.get(93).getText());
        checkBoxes.get(93).click();
        regions.add(checkBoxes.get(98).getText());
        checkBoxes.get(98).click();
        popup.findElement(By.className("bloko-button")).click();
        String selected = dbTab.selectedValues.get(0).getText();
        for (String region : regions) {
            assert selected.contains(region);
        }
    }
}
