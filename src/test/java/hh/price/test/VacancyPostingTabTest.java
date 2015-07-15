package hh.price.test;

import hh.price.selenium.recommended.PriceCart;
import hh.price.selenium.recommended.VacancyPostingTab;
import hh.price.selenium.recommended.VacancyType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;

import java.util.*;

public class VacancyPostingTabTest extends DriverInit{

    @Test
    public void addToCart(){
        VacancyPostingTab vpTab = new VacancyPostingTab(getDriver()).get();
        PriceCart cart = new PriceCart(getDriver().findElement(By.className("HH-PriceCart")));
        ArrayList<Map<String,String>> list = new ArrayList<>();
        for (VacancyType type : VacancyType.values()) {
            HashMap<String, String> mp = new HashMap<>();
            mp.put("type", type.getName());
            mp.put("pcs", Integer.toString(new Random().nextInt(100)));
            vpTab.getInput(type).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, mp.get("pcs"));
            vpTab.getButton(type).click();
            mp.put("price", vpTab.getCost(type).getText().replace(" ", ""));
            list.add(mp);
        }
        List<Map<String, String>> cartData = cart.collectCartData();
        int iter = 0;
        long sum = 0;
        for (Map<String, String> input : list) {
            assert cartData.get(iter).get("title").contains(input.get("type"));
            assert cartData.get(iter).get("title").contains(input.get("pcs"));
            assert cartData.get(iter).get("price").contains(input.get("price"));
            sum += Long.parseLong(input.get("price").replace("руб.", ""));
            iter++;
        }
        assert sum == Long.parseLong(cartData.get(0).get("actualCost"));
    }
}