package hh.price.selenium.recommended;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PriceCart {
    private final WebElement cart;

    public PriceCart(WebElement element) {
        cart = element;
    }

    public WebElement getCart() {
        return cart;
    }

   public List<WebElement> getCartItems(){
       return cart.findElements(By.className("price-cart__item"));
   }

    public WebElement getPriceSum(){
       return cart.findElement(By.className("price-cart__total-summary"));
   }

   public WebElement getGifts(){
       return cart.findElement(By.className("price-cart__gifts"));
   }

   public void buttonClick(){
       cart.findElement(By.cssSelector(".price-cart__button > a")).click();
   }


}
