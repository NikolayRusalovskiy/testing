package com.gl.citrus;

import com.gl.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {
    public StartPage(WebDriver driver) {
        super(driver);
    }

@FindBy(xpath = "//a[@href=\"/planshety-noutbuki-i-kompyutery/\"]")
    public WebElement tabletNotePc;

    @FindBy(xpath = "//div[@class=\"menu--desktop\"]")
    public WebElement catalog;


}
