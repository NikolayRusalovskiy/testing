package com.gl.citrus;

import com.gl.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SystemBlockPage extends BasePage {

    public SystemBlockPage(WebDriver driver) {
        super(driver);
    }


@FindBy(xpath = "//div[@class=\"catalog__items\"]")
public WebElement catlogitems;



    public String urlSystemBlock = "https://www.citrus.ua/sistemnye-bloki/";


    public SystemBlockPage openPage() {
        SystemBlockPage sbPage = new SystemBlockPage(driver);
        driver.get(urlSystemBlock);
        return sbPage;
    }


}
