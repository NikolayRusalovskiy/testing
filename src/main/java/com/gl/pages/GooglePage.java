package com.gl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends BasePage {
    public GooglePage(WebDriver driver) {
        super(driver);
    }
 @FindBy(xpath = "//input[@name=\"q\"]")
public
WebElement searchG;

}
