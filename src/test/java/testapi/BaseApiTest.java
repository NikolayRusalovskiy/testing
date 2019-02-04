package testapi;

import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BaseApiTest {
    @Step("Проверка совпадения фактической строки: {actual} и ожидаемой строки: {expected}")
    public static void checkStringStep(String actual, String expected) {
        Assert.assertTrue(actual.equals(expected), "Сравнение строк");
        assertThat("Сравнение строк", actual, equalTo(expected));
    }
}
