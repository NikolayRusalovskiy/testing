package testgui.citrus;

import org.testng.annotations.Test;
import testgui.BaseTestCase;


public class SystemBlockTest extends BaseTestCase {
    @Test
    public void openSystemBlockaPagetest(){
        systemBlockPage.openPage();

        assert systemBlockPage.catlogitems.isDisplayed();
        takeScreenShotAsFile();
        takeAShot();
        System.out.println("ok");
    }
}
