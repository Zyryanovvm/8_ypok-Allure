package qa.guru.allure.components;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

public class Labels {

    @Disabled
    @Test
    @Owner("Zyryanovvm")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тестирование страницы")
    @Feature("Задачи в репозитории")
    @Story("Пользователь имеет возможность просматривать созданные задачи")
    @Link(value = "Тестинг", url = "htps://github.com" )
    public void testAnnotated() {
    }


    @Owner("Zyryanovvm")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Задачи в репозитории")
    @Story("Пользователь имеет возможность просматривать созданные задачи")
    @Link(value = "Тестинг", url = "htps://github.com" )
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface ShowIssue {

    }

    @Disabled
    @Test
    public void testCode() {
        Allure.label("owner", "Zyryanovvm");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.feature("Задачи в репозитории");
        Allure.story("Пользователь имеет возможность просматривать созданные задачи");
        Allure.link("Тестинг", "htps://github.com");
    }

}
