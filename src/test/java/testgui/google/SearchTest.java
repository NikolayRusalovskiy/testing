package testgui.google;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import testgui.BaseTestCase;

public class SearchTest extends BaseTestCase {
    @Test
    @Description("Fisrt test")
    public void getSearchTest() throws InterruptedException {
        driver.get("https://www.google.com.ua/?hl=ru");
        googlePage.searchG.sendKeys("Google");
        Thread.sleep(5000);
    }
}
