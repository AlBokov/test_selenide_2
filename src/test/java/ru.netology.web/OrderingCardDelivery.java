package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.K;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class OrderingCardDelivery {
    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @BeforeClass
    public static void setup() {
        Configuration.headless = true;
    }


    @Test
    public void succsefulCardDelivery() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = generateDate(5, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date']").setValue(date);
        $("[data-test-id=name]").setValue("Иванов Иван");
        $("[data-test-id=phone]").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + date));
    }

}

//    // проверка формы с валлидными данными
//    @Test
//    public void workingWithValidData() {
//        open("http://localhost:9999");
//        SelenideElement form = $(".form_theme_alfa-on-white");
//        form.$("[name=name]").setValue("Боков Александр");
//        form.$("[name=phone]").setValue("+95000000000");
//        form.$(".checkbox_theme_alfa-on-white").click();
//        form.$("button").click();
//        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
//
//    }
//}
//
//    // проверка отправки формы с пустым полем Фамилия и имя
//    @org.junit.jupiter.api.Test
//    void sendingFormWithoutFULLNameField() {
//        open("http://localhost:9999"); // открываем страницу
//        SelenideElement form = $("[action]"); // делаем акцент на форму
//        form.$(".input__control[type=tel]").setValue("+95000000000"); // заполняем поле телефон
//        form.$(".checkbox__text").click(); // кликаем по чекбоксу
//        form.$(".button_theme_alfa-on-white").click(); // кликаем по кнопке отправить
//        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения")); // проверка наличия текста об ошибке в поле ФИО
//    }
//
//    @org.junit.jupiter.api.Test
//    void nameFieldIsFilledInWithEnglishLetters() {
//        open("http://localhost:9999");
//        SelenideElement form = $("[action]");
//        form.$("[name=name]").setValue("Bokov Alexander");
//        form.$(".input__control[type=tel]").setValue("+95000000000");
//        form.$(".checkbox__text").click();
//        form.$(".button_theme_alfa-on-white").click();
//        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
//    }
//
//    @org.junit.jupiter.api.Test
//    void nameFieldForbiddenCharacters() {
//        open("http://localhost:9999");
//        SelenideElement form = $("[action]");
//        form.$("[name=name]").setValue("Боков Александр?");
//        form.$(".input__control[type=tel]").setValue("+95000000000");
//        form.$(".checkbox__text").click();
//        form.$(".button_theme_alfa-on-white").click();
//        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
//    }
//
//
//}



