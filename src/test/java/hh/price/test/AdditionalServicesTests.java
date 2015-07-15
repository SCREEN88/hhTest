package hh.price.test;

import hh.price.selenium.purchaseServices.PriceCart;
import hh.price.selenium.purchaseServices.additionalSubTabs.AdditionalServicesSubTabs;
import hh.price.selenium.purchaseServices.additionalSubTabs.AdditionalServicesTab;
import hh.price.selenium.purchaseServices.additionalSubTabs.CompanyPromotionTab;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.*;

public class AdditionalServicesTests extends DriverInit {

    @Test
    public void addToCart() {
        AdditionalServicesTab adTab = new AdditionalServicesTab(getDriver()).get();
        PriceCart cart = new PriceCart(getDriver().findElement(By.className("HH-PriceCart")));
        ArrayList<Map<String, String>> list = new ArrayList<>();
        for (AdditionalServicesTab.ResumeTypes type : AdditionalServicesTab.ResumeTypes.values()) {
            HashMap<String, String> mp = new HashMap<>();
            mp.put("pcs", Integer.toString(new Random().nextInt(100)));
            adTab.getInput(type).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, mp.get("pcs"));
            adTab.getButton(type).click();
            mp.put("price", adTab.getCost(type).getText().replace(" ", ""));
            list.add(mp);
        }
        List<Map<String, String>> cartData = cart.collectCartData();
        int iter = 0;
        long sum = 0;
        for (Map<String, String> input : list) {
            assert cartData.get(iter).get("title").contains(input.get("pcs"));
            assert cartData.get(iter).get("price").contains(input.get("price"));
            sum += Long.parseLong(input.get("price").replace("руб.", ""));
            iter++;
        }
        assert sum == Long.parseLong(cartData.get(0).get("actualCost"));
    }

    @Test
    public void accordionLinks() {
        CompanyPromotionTab adTab = (CompanyPromotionTab) new AdditionalServicesTab(getDriver()).get()
            .selectSubTab(AdditionalServicesSubTabs.COMPANY_PROMOTION);
        for (WebElement accordion : adTab.accordions) {
            accordion.findElement(By.cssSelector("dt > a")).click();
            assert accordion.findElement(By.tagName("dt")).getAttribute("class").contains("b-accordion__item_current");
            assert accordion.findElement(By.tagName("dd")).isDisplayed();
        }
    }
}
