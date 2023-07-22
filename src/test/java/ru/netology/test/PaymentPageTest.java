package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;


public class PaymentPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }


    @Test
    void shouldGetPaymentPage() {
        var mainPage = new MainPage();
        mainPage.payByCard();
    }

    @Test
    void shouldGetCreditPage() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
    }

    @Test
    void successfulCardPayment() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void shouldNotPayWithDeclinedCard() {
        var cardInfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.declinedPayment();
    }

    @Test
    void ShortCardNumber() {
        var cardInfo = new DataHelper.CardInfo(getShortCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cardNumberErrorVisible();
    }

    @Test
    void cardNumberWithoutSpaces() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumberNoSpace(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void emptyCardNumber() {
        var cardInfo = new DataHelper.CardInfo(null, getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cardNumberErrorVisible();
    }

    @Test
    void cardNumberWithLetter() {
        var cardInfo = new DataHelper.CardInfo(getCardNumberWithLetters(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cardNumberErrorVisible();
    }

    @Test
    void cardNumberWithSpecialCharacters() {
        var cardInfo = new DataHelper.CardInfo(getCardNumberWithSigns(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cardNumberErrorVisible();
    }

    @Test
    void longCardNumber() {
        var cardInfo = new DataHelper.CardInfo(getTheCardNumberEnteredIsLong(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void emptyMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), null, getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.monthErrorVisible();
    }

    @Test
    void singleDigitInMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getOneDigit(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void monthWithLetters() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getTwoLetters(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.monthErrorVisible();
    }

    @Test
    void threeDigitsInMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getThreeDigit(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void doubleZeroInMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthWithNulls(), getValidNextYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.monthErrorVisible();
    }

    @Test
    void zeroOneInMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonth01(), getValidNextYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void twelveInMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getTwoDigit(), getValidNextYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void monthNumberOver12() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthOver12(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.monthErrorVisible();
    }

    @Test
    void monthWithSpecialCharacters() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthWithSigns(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.monthErrorVisible();
    }

    @Test
    void zeroInMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthWithNull(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.monthErrorVisible();
    }

    @Test
    void emptyYear() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), null, getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.yearErrorVisible();
    }

    @Test
    void previousYearInYear() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getPastYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.yearErrorVisible();
    }

    @Test
    void currentYearAndPreviousMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getMonthMinus1(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.monthErrorVisible();
    }

    @Test
    void currentYearAndCurrentMonth() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void specialCharactersInYear() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getYearWithSigns(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.yearErrorVisible();
    }

    @Test
    void lettersInYear() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getTwoLetters(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.yearErrorVisible();
    }

    @Test
    void oneDigitInYear() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getOneDigit(), getOwnerName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.yearErrorVisible();
    }

    @Test
    void emptyOwnerName() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), null, getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.ownerErrorVisible();
    }

    @Test
    void shortOwnerName() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameShort(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.ownerErrorVisible();
    }

    @Test
    void ownerNameWithDoubleName() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameWithDoubleName(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void cyrillicNameOwner() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameInRussia(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.ownerErrorVisible();
    }

    @Test
    void ownerNameWithSpecialCharacters() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameWithSigns(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.ownerErrorVisible();
    }

    @Test
    void ownerNameWithDigits() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerNameWithDigits(), getThreeDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.ownerErrorVisible();
    }

    @Test
    void emptyCVC() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), null);
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cvcErrorVisible();
    }

    @Test
    void specialCharactersInCVC() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVCwithSigns());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cvcErrorVisible();
    }

    @Test
    void twoDigitInCVC() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getTwoDigit());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cvcErrorVisible();
    }

    @Test
    void fourDigitInCVC() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVClong());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.successfullPayment();
    }

    @Test
    void lettersInCVC() {
        var cardInfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getOwnerName(), getCVCwithLetters());
        var mainPage = new MainPage();
        var paymentPage = mainPage.payByCard();
        paymentPage.fillCard(cardInfo);
        paymentPage.cvcErrorVisible();
    }

}