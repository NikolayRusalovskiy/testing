package testgui;


import com.gl.citrus.SystemBlockPage;
import com.gl.pages.BasePage;
import com.gl.pages.GooglePage;
import com.gl.utils.TestProperties;
import com.gl.utils.WebDriverScreenshotListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.ashot.*;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ViewportPastingDecorator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.testng.internal.Utils.log;


@Listeners({WebDriverScreenshotListener.class})
public abstract class BaseTestCase {

    public static WebDriver driver;

    public String taxId = "";
    public String msgId = "";
    public String currentUrl = "";
    protected GooglePage googlePage = BasePage.create(getDriver(), GooglePage.class);

    protected SystemBlockPage systemBlockPage = BasePage.create(getDriver(), SystemBlockPage.class);

    @BeforeSuite(alwaysRun = true)
    public void setUpBeforeSuite() throws Exception {
    }

    @BeforeClass(alwaysRun = true)
    public void setUpBeforeClass() throws Exception {
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownAfterSuite() {
        shutDownDriver(driver);
    }

    private void shutDownDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        if (driver == null) {
            String browser = TestProperties.getTestProperty("browser");
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
                maximizeWindow();
            }
            if ("chrome".equals(browser)) {
                //ClassLoader.getSystemResource("chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", ClassLoader.getSystemResource("chromedriver.exe").getPath());
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1200x600");
                driver = new ChromeDriver();
                maximizeWindow();
            }
            if ("htmlunit".equals(browser)) {
                driver = new HtmlUnitDriver(true);
                return driver;
            }
//            if("ie".equals(browser)){
//                System.setProperty("webdriver.ie.driver", ClassLoader.getSystemResource("IEDriverServer.exe").getPath());
//                InternetExplorerOptions options = new InternetExplorerOptions();
//                driver = new InternetExplorerDriver();
//
//            }
            driver.manage()
                    .timeouts()
                    .implicitlyWait(TestProperties.getWaitTime(),
                            TimeUnit.SECONDS);
            driver.manage()
                    .timeouts()
                    .pageLoadTimeout(
                            Integer.parseInt(TestProperties
                                    .getTestProperty("wait.page.load")),
                            TimeUnit.SECONDS);
        }
        return driver;
    }

    private void maximizeWindow() {
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();
        org.openqa.selenium.Dimension dim = new org.openqa.selenium.Dimension((int) screenSize.getWidth(),
                (int) screenSize.getHeight());
        driver.manage().window().setSize(dim);
    }

    public void openRelativeUrl(String relativeUrl) {
        getDriver().get(relativeUrl);
    }

    @Step("{0}")
    public void logToReport(String message) {
        log(message); //or System.out.println(message);
    }

    @Attachment(value = "Test for opening page")
    public byte[] createAttachment() {
        String content = "attachmentContent";
        return content.getBytes();
    }

    @Attachment(value = "Contains of messageBody")
    public byte[] logMessageBody(String msgBody) throws UnsupportedEncodingException {
        //return new String(msgBody.getBytes("windows-1251"), "UTF-8").getBytes();
        return msgBody.getBytes();
    }

    @Attachment(value = "Contains of uploaded Document")
    public static byte[] logUploadDocument(String path) throws IOException {
        //return new String(msgBody.getBytes("windows-1251"), "UTF-8").getBytes();
        return new String(Files.readAllBytes(new File(path).toPath()), "windows-1251").getBytes("UTF-8");
    }

    @Attachment(value = "Screenshot")
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void takeScreenShotAsFile() {
        File tmp = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String name = "src\\test\\resources\\" + "shot_" + UUID.randomUUID() + ".jpeg";
        File dst = new File(name);

        try {
            copyFileUsingStream(tmp, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeAShot() {
        Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

        String name = "src\\test\\resources\\AShot\\" + "shot_" + UUID.randomUUID() + ".jpeg";

        try {
            ImageIO.write(myScreenshot.getImage(), "JPEG", new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }


    public void takeshotASFile() {
        ChromeDriver chromeDriver = new ChromeDriver();


    }

    public String generateTaxId() {
        String resInn = "";
        Integer[] array = new Integer[]{10, 5, 7, 9, 4, 6, 10, 5, 7};
        Integer[] inn = new Integer[]{1, 2, 1, 1, 1, 1, 1, 7, 4};
        Integer[] result = new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
        for (int index = 0; index < 9; index += 1) {
            inn[index] = 1 + Math.abs(Long.valueOf(
                    UUID.randomUUID().getLeastSignificantBits()).intValue()) % (index == 0 ? 2 : 9);
        }
        int index = 0;
        for (Integer item : inn) {
            result[index] = item * array[index];
            resInn += item;
            index += 1;
        }
        int sum = 0;
        for (Integer res : result) {
            sum += res;
        }
        sum %= 11;
        sum %= 10;
        result[9] = sum;
        resInn += sum;
        return resInn;
    }

}
