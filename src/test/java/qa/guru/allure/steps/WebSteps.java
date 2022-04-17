package qa.guru.allure.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openPage() {
        Selenide.open("https://github.com/");
        attachScreenshot();
    }
    @Step("Поиск репозитория {repo}")
    public void searchForRepo(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").pressEnter();
        attachScreenshot();
    }
    @Step("Переходим в репозиторий {repo}")
    public void clickOnRepo(String repo) {
        $(linkText(repo)).click();
        attachScreenshot();
    }
    @Step("Ищем вкладку Issues и кликаем по ней")
    public void issueTab() {
        $(partialLinkText("Issues")).click();
        attachScreenshot();
    }
    @Step("Проверяем наличие текста Welcome to issues!")
    public void searchTextWelcome() {
        $(withText("Welcome to issues!")).shouldHave(Condition.visible);
        attachScreenshot();
    }

    @Attachment(value = "Вложенный скриншот", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
