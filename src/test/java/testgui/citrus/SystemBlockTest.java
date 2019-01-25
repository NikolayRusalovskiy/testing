package testgui.citrus;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import testgui.BaseTestCase;

public class SystemBlockTest extends BaseTestCase {
    @Test
    public void openSystemBlockaPagetest(){
        systemBlockPage.openPage();
        assert systemBlockPage.catlogitems.isDisplayed();
        takeScreenShotAsFile();
        System.out.println("ok");
    }
}
