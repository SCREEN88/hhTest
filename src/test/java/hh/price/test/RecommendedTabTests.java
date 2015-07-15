package hh.price.test;

import hh.price.selenium.purchaseServices.PriceCart;
import hh.price.selenium.purchaseServices.RecommendedTab;
import hh.price.selenium.utils.WaitFor;
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
            new WaitFor(getDriver()).elementToDisplay(cart.getPriceSum());
            Map<String, String> cartData = cart.collectCartData().get(iter);
            //assert cartData.get("title").replace(" —", "").equals(offersData.get(iter).get("title"));
            if (cartData.containsKey("oldPrice")){
                assert cartData.get("oldPrice").equals(offersData.get(iter).get("oldPrice"));
            }
            assert cartData.get("price").equals(offersData.get(iter).get("price"));
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
                totalSum += Long.parseLong(offersData.get(iter).get("oldPrice"));
            }
            actualSum += Long.parseLong(offersData.get(iter).get("price").replace("руб.", ""));
            assert Long.parseLong(cart.collectCartData().get(iter).get("totalCost").replace("руб.", "")) == totalSum;
            assert Long.parseLong(cart.collectCartData().get(iter).get("actualCost").replace("руб.", "")) == actualSum;
            iter++;
        }
    }

    @Test
    public void removeElementFromCart(){
        RecommendedTab page = new RecommendedTab(getDriver()).get();
        PriceCart cart = new PriceCart(getDriver().findElement(By.className("HH-PriceCart")));
        for (WebElement button : page.formButtons) {
            button.click();
        }
        new WaitFor(getDriver()).elementToDisplay(cart.getPriceSum());
        int initSize = cart.collectCartData().size();
        cart.getCartItems().get(1).findElement(By.tagName("small")).click();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        assert cart.collectCartData().size() == (initSize - 1);
    }
}
