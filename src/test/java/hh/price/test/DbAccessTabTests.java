package hh.price.test;

import hh.price.selenium.recommended.DbAccessTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class DbAccessTabTests extends DriverInit{

    @Test
    public void formActualPriceChange(){
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


}
