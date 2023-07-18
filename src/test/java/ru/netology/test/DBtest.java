package ru.netology.test;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBUtils;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class DBtest {
    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        DBUtils.cleanTable();
    }

    @Test
    void shouldBeApprovedWithApprovedCard() {
        var mainPage = new MainPage();
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
        assertEquals(DBUtils.getPaymentStatus(), "APPROVED");
    }

    @Test
    void shouldBeDeclinedWithDeclinedCard() {
        var mainPage = new MainPage();
        var cardInfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVC());
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
        assertEquals(DBUtils.getPaymentStatus(), "DECLINED");
    }
}