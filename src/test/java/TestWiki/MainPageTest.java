package TestWiki;

import TestWiki.PageObject.MainPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(TextReportExtension.class)
public class MainPageTest {

    WebDriver driver;

    @BeforeEach
    public void start() {
        System.setProperty("https.protocols", "TLSv1.1");
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1600x900";
        Configuration.timeout = 10000;
        Configuration.headless = false;
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    public void finish() {
        driver.quit();
    }


    private final static String BASE_URL = "https://ru.wikipedia.org";
    private final static String TITLE = "Википедия — свободная энциклопедия";

    @Epic(TITLE)

    @Feature(value = "Чек-лист")

    @DisplayName("Главная страница")
    @Description("Тестовый сценарий")
    @Severity(SeverityLevel.TRIVIAL)

    @Test
    public void mainPage() {
        MainPage mainPage = new MainPage(BASE_URL, driver);
        mainPage.title(TITLE);
        mainPage.randomArticle();
        mainPage.nextArticle();
    }
}
