package testgui.google;

import org.testng.annotations.Test;
import testgui.BaseTestCase;

public class SearchTest extends BaseTestCase {
    @Test
    public void getSearch() throws InterruptedException {
        driver.get("https://www.google.com.ua/?hl=ru");
        googlePage.searchG.sendKeys("Google");
        Thread.sleep(5000);
    }
}
