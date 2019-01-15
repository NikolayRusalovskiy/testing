package com.gl.pages;


import com.gl.utils.AbstractContainer;
import com.gl.utils.Container;
import com.gl.utils.PageFactoryEx;
import com.gl.utils.TestProperties;
import org.json.JSONException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage extends AbstractContainer {


    private final Wait<WebDriver> wait = new WebDriverWait(driver, 20).pollingEvery(1, TimeUnit.SECONDS);

    public BasePage(WebDriver driver){
        super(driver);

    }

    @FindBy(id = "spinner")
    public static
    WebElement spinner;
    @FindBy(id = "nextButton")
    public static
    WebElement nextButton;
    @FindBy(id = "okButton")
    public
    WebElement okButton;



        public void dropDownChoice(WebElement element, String parameter){
        element.sendKeys(parameter);
        waitForRequest();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.sendKeys(Keys.DOWN);
        waitForRequest();
        element.sendKeys(Keys.ENTER);
    }

        public  void nextStep(){
            nextButton.click();
            waitForRequest();
        }

    //----------------CHECKS ON PAGE------------------------------->
    //------------------------------------------------------------->
    //------------------------------------------------------------->

    public boolean isElementPresent(WebElement element){
        try{
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }

    public boolean isElementVisible(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (ElementNotVisibleException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement locator) {
        try {
            locator.isDisplayed();
            return true;
        } catch (ElementNotVisibleException ignored) {
            return false;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }

    public boolean isElementTextPresent(WebElement element, String text) {
        try {
            if (element.getText().equals(text)) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public boolean isTextPresent(String text) {
        return driver.findElement(By.tagName("body")).getText().contains(text);
    }

    public boolean isTableHeadersPresent(List<String> headerText) {
        List<WebElement> headers = driver.findElements(By.xpath("//th"));
        return isHeaderTextEquals(headerText, headers);
    }

    protected boolean isHeaderTextEquals(List<String> headerText, List<WebElement> headers) {
        if (headers.size() != headerText.size()) {
            return false;
        }

        for (int i = 0; i < headers.size(); i++) {
            WebElement webElement = headers.get(i);
            String text = headerText.get(i);
            if (!isElementTextPresent(webElement, text)) {
                return false;
            }
        }
        return true;
    }

    //----------------ACQUIRING ELEMENTS/PROPS OF PAGE------------------------------->
    //------------------------------------------------------------------------------->
    //------------------------------------------------------------------------------->

    public String getPageFullUrl() {
        return TestProperties.getTestProperty("host") ;
    }

    public String getChildMsgIDUrl() {
        return TestProperties.getTestProperty("host") + TestProperties.getTestProperty("childMsgIdUrl");
    }


    public Wait<WebDriver> getWait() {
        return wait;
    }

    //----------------UTIL METHODS--------------------------------->
    //------------------------------------------------------------->
    //------------------------------------------------------------->




    public static <T extends BasePage> T create(WebDriver driver, Class<T> pageClass) {
        return PageFactoryEx.init(driver, pageClass);
    }

    public void close() {
        driver.quit();
    }

    public void acceptConfirmation() {
        driver.switchTo().alert().accept();
    }



    protected void sleep() {
        try {
            Thread.sleep(Integer.parseInt(TestProperties.getTestProperty("wait.hack.msec")));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void clickAt(WebElement webElement, int x, int y) {
        Actions builder = new Actions(driver);
        Action action = builder.moveToElement(webElement, x, y).click().build();
        action.perform();
    }

    public void waitForRequest(){
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
//        getWait().until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.id("spinner"))));
    }
    public void selectElement(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    public void selectElement(WebElement element, String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public String getMessageId(){
        String currUrl = driver.getCurrentUrl();
        return currUrl.substring(currUrl.indexOf('=') + 1, currUrl.indexOf('&'));
    }

    public static String getRandomWord(int length, String alphabet) {
        StringBuilder sb = new StringBuilder(Math.max(length, 16));
        for (int i = 0; i < length; i++) {
            int len = alphabet.length();
            int random = new SecureRandom().nextInt(len);
            char c = alphabet.charAt(random);
            sb.append(c);
        }
        return sb.toString();
    }
}
