package hh.price.test;

import hh.price.selenium.recommended.PriceCart;
import hh.price.selenium.recommended.RecommendedTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public class RecommendedTabTests extends DriverInit{

    @BeforeTest(description = "Загрузка страницы", enabled = false)
    public void firstTest() {
        RecommendedTab page = new RecommendedTab(getDriver()).get();
    }

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
            if (cartData.get("oldPrice").length() > 0){
                assert cartData.get("oldPrice").contains(offersData.get(iter).get("oldPrice"));
            }
            assert cartData.get("price").contains(offersData.get(iter).get("price"));
            assert cartData.get("bonus").equals(offersData.get(iter).get("bonus").replace("+", ""));
            iter++;
        }

    }
}
