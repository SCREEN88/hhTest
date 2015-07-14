package hh.price.test;

import hh.price.selenium.ServiceBuying;
import org.testng.annotations.*;

public class ServicesPageTests extends DriverInit{

    @Test(description = "Проверка работы драйвера.")
    public void firstTest() {
        ServiceBuying page = new ServiceBuying(getDriver()).get();
    }
}
