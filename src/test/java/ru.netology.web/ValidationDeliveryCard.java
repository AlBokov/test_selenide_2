package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.*;

public class ValidationDeliveryCard {

    @BeforeClass
    public static void setup() {
        Configuration.headless = true;
    }


    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void emptyCityField() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("");
        String date = generateDate(5, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void cityNotSelected() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Минск");
        String date = generateDate(5, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void pastDateDay() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = generateDate(-1, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void pastDateMonth() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = generateDate(-30, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void emptyName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = generateDate(5, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }


    @Test
    public void emptyPhone() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = generateDate(5, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }


    @Test
    public void thereIsNoReceiptBox() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = generateDate(5, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("button.button").click();
        element("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));    }



}
