package hh.price.selenium.recommended;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceCart {
    private final WebElement cart;

    public PriceCart(WebElement element) {
        cart = element;
    }

    public WebElement getCart() {
        return cart;
    }

    public List<WebElement> getCartItems() {
        return cart.findElements(By.className("price-cart__item"));
    }

    public List<Map<String,String>> collectCartData(){
        ArrayList<Map<String,String>> list = new ArrayList<>();
        List<WebElement> gifts = Lists.reverse(getGifts());
        WebElement priceSum = getPriceSum();
        int iter = 0;
        for (WebElement offer : getCartItems()) {
            HashMap<String, String> data = new HashMap<>();
            data.put("title", offer.findElement(By.className("price-cart__item-title")).getText());
            List<WebElement> oldPrice = offer.findElements(By.className("price-cart__old-cost"));
            if (oldPrice.size() > 0){
                data.put("oldPrice", oldPrice.get(0).getText());
            }
            data.put("price", offer.findElement(By.className("price-cart__actual-cost")).getText().replace(" ", ""));
            data.put("totalCost", priceSum.findElement(By.className("price-cart__total-old-cost")).getText().replace(" ", ""));
            data.put("actualCost", priceSum.findElement(By.className("HH-PriceCart-TotalCost-Actual")).getText().replace(" ", ""));
            data.put("bonus", gifts.get(iter).getText());
            list.add(data);
        }
        return list;
    }

    public WebElement getPriceSum() {
        return cart.findElement(By.className("price-cart__total-summary"));
    }

    public List<WebElement> getGifts() {
        return cart.findElements(By.className("price-cart__gift"));
    }

    public void buttonClick() {
        cart.findElement(By.cssSelector(".price-cart__button > a")).click();
    }


}
