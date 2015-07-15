package hh.price.test;

import hh.price.selenium.recommended.PriceCart;
import hh.price.selenium.recommended.RecommendedTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public class RecommendedTabTests extends DriverInit{

    @Test
    public void buttonDisabledTest(){
        RecommendedTab page = new RecommendedTab(getDriver()).get();
        for (WebElement button : page.formButtons) {
            button.click();
            assert button.getAttribute("disabled").equals("true");
        }
    }

    @Test
    public void addToCartTest(){
        RecommendedTab page = new RecommendedTab(getDriver()).get();
        PriceCart cart = new PriceCart(getDriver().findElement(By.className("HH-PriceCart")));
        List<Map<String,String>> offersData = page.collectOffersData();
        int iter = 0;
        for (WebElement button : page.formButtons) {
            button.click();
            Map<String, String> cartData = cart.collectCartData().get(iter);
            assert cartData.get("title").contains(offersData.get(iter).get("title"));
            if (cartData.containsKey("oldPrice")){
                assert cartData.get("oldPrice").contains(offersData.get(iter).get("oldPrice"));
            }
            assert cartData.get("price").contains(offersData.get(iter).get("price"));
            assert cartData.get("bonus").equals(offersData.get(iter).get("bonus").replace("+", ""));
            iter++;
        }
    }

    @Test
    public void correctCartSumTest(){
        RecommendedTab page = new RecommendedTab(getDriver()).get();
        PriceCart cart = new PriceCart(getDriver().findElement(By.className("HH-PriceCart")));
        List<Map<String,String>> offersData = page.collectOffersData();
        long totalSum = 0;
        long actualSum = 0;
        int iter= 0;
        for (WebElement button : page.formButtons) {
            button.click();
            if (offersData.get(iter).containsKey("oldPrice")){
                totalSum += Long.parseLong(offersData.get(iter).get("oldPrice").replace(" руб.", ""));
            }
            actualSum += Long.parseLong(offersData.get(iter).get("price").replace(" руб.", ""));
            assert cart.collectCartData().get(iter).get("totalCost").replace("руб.", "").equals(Long.toString(totalSum));
            assert cart.collectCartData().get(iter).get("actualCost").replace("руб.", "").equals(Long.toString(actualSum));
            iter++;
        }
    }
}
