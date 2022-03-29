package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryOrderTest {

    @NotNull
    private String date() {
        Calendar cl = new GregorianCalendar();
        cl.add(Calendar.DATE, 6);
        return new SimpleDateFormat("dd.MM.yyyy").format(cl.getTime());
    }

    @Test
    void shouldSubmitRequest(){
        Configuration.holdBrowserOpen = false;
        Selenide.open("http://0.0.0.0:9999/");
        $("[data-test-id='city'] input").val("Красноярск");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, date());
        $("[data-test-id=name] input").val("Лощенов Денис");
        $("[data-test-id='phone'] input").val("+79002001001");
        $("[data-test-id='agreement']").click();
        $("[class=\"button__text\"]").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + date()));
    }
}

