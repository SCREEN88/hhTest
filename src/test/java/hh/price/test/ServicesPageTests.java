package hh.price.test;

import hh.price.selenium.recommended.RecommendedTab;
import org.testng.annotations.*;

public class ServicesPageTests extends DriverInit{

    @Test(description = "Проверка работы драйвера.")
    public void firstTest() {
        RecommendedTab page = new RecommendedTab(getDriver()).get();
    }
}
