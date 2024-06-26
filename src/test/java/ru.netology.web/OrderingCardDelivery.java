package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class OrderingCardDelivery {


    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void succsefulCardDelivery() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String date = generateDate(5, "dd.MM.yyyy"); // генерация даты
        $("[data-test-id='date'] input").click();
        $("[data-test-id='date'] input").doubleClick(); // выделение содержимого
        $("[data-test-id='date'] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + date));
    }

    @Test
    public void succsefulCardDeliveryV2() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Мо");
        $$(".menu-item__control").findBy(Condition.text("Москва")).click();
        String date = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").click();
        if (!generateDate(4, "MM").equals(generateDate(7, "MM"))) {
            $("[data-test-arrow='next']").click();
        }
        $$("[data-day]").findBy(Condition.text(generateDate(7, "d"))).click();
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+95000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + date));
    }


}
