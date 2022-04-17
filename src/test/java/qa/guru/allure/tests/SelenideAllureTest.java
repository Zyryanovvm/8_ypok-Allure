package qa.guru.allure.tests;

import com.beust.jcommander.Parameters;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa.guru.allure.components.Labels;
import qa.guru.allure.steps.WebSteps;


import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@Owner("Zyryanovvm")
@Severity(SeverityLevel.BLOCKER)
@Feature("Задачи в репозитории")
@Story("Пользователь имеет возможность просматривать созданные задачи")
@Link(value = "Тестинг", url = "htps://github.com" )
public class SelenideAllureTest {

    private static final String Repo = "Zyryanovvm/demoqa_4_ypok";


    @Test
    @DisplayName("Обычный тест")
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Allure.parameter("Репозиторий", "Zyryanovvm/demoqa_4_ypok");

        Selenide.open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(Repo);
        $(".header-search-input").pressEnter();
        $(linkText(Repo)).click();
        $(partialLinkText("Issues")).click();
        $(withText("Welcome to issues!")).shouldHave(Condition.visible);
    }

    @Test
    @DisplayName("Тест с лямбда step'ми")
    public void testGithubIssueStepLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Allure.parameter("Репозиторий", "Zyryanovvm/demoqa_4_ypok");

        step("Открываем главную страницу", () -> {
            Selenide.open("https://github.com/");
        });
        step("Поиск репозитория " + Repo, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(Repo);
            $(".header-search-input").pressEnter();
        });
        step("Переходим в репозиторий " + Repo, () -> {
            $(linkText(Repo)).click();
        });
        step("Ищем вкладку Issues и кликаем по ней", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверяем наличие текста Welcome to issues!", () -> {
            $(withText("Welcome to issues!")).shouldHave(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8));
            Allure.getLifecycle().addAttachment(
                "Вложенный скриншот",
                "image/png",
                "png",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8));
    });
}

    @Test
    @DisplayName("Тест с аннотоциями step'ов")
    public void testGithubIssueAnnotationSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Allure.parameter("Репозиторий", "Zyryanovvm/demoqa_4_ypok");
        WebSteps steps = new WebSteps();

        steps.openPage();
        steps.searchForRepo(Repo);
        steps.clickOnRepo(Repo);
        steps.issueTab();
        steps.searchTextWelcome();
    }
}
