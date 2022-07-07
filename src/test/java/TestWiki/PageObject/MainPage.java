package TestWiki.PageObject;

import TestWiki.Logger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тестовое задание по Википедии
 */

public class MainPage {



    // Ссылка на случайную статью
    private final String randomArticleLinkLocator = "//a[@accesskey = 'x']";
    // Текст статьи
    private final String textArticleLocator = "//div[@id = 'mw-content-text']/div[1]/p[1]";


    WebDriver driver;
    Logger logger = new Logger();

    public MainPage(String baseUrl, WebDriver driver) {
        this.driver = driver;
        driver.get(baseUrl);
        logger.setLog(driver);
    }

    @Step(value = "Тайтл")
    public void title(String title) {
        assertEquals(title, driver.getTitle());
    }

    @Step(value = "Переход в случайную статью")
    public void randomArticle() {
        driver.findElement(By.xpath(randomArticleLinkLocator)).click();
        logger.setLog(driver);
    }

    @Step(value = "Переход первой ссылке в тексте статьи, до получения статьи Философия")
    public void nextArticle() {

        boolean isPhilosophyArticle = false;
        List<WebElement> elements;
        String lastLink = "";
        int num = 0;

        // Запускаем цикл прохождения до нужной статьи
        while (!isPhilosophyArticle) {

            elements = driver.findElement(By.xpath(textArticleLocator))
                    .findElements(By.tagName("a")); // Получаем список тегов из элемента статьи

            for (WebElement el : elements)
            {
                // Проверяем ссылки на содержание якоря, уже пройденной ссылки, общих файлов википедии
                if (!el.getAttribute("href").contains("#") &&
                        !lastLink.contains(el.getAttribute("href")) &&
                        !el.getAttribute("href").contains(".ogg")) {

                    el.click(); // Переходим на следующую статью
                    logger.setLog(driver); // Сохраняем лог
                    lastLink = lastLink+driver.getCurrentUrl(); // Сохраняем ссылку, для обхода кругового замыкания
                    // Проверяем, соответствует ли ссылка на статью о философии текущей
                    isPhilosophyArticle = driver.getCurrentUrl()
                            .contains("https://ru.wikipedia.org/wiki/%D0%A4%D0%B8%D0%BB%D0%BE%D1%81%D0%BE%D1%84%D0%B8%D1%8F");
                    break;

                }
            }
        }

    }

    // foo...
}
