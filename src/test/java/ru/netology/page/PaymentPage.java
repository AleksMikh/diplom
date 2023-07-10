package ru.netology.page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement cardNumber = fields.get(0);
    private final SelenideElement month = fields.get(1);
    private final SelenideElement year = fields.get(2);
    private final SelenideElement owner = fields.get(3);
    private final SelenideElement cvc = fields.get(4);
    private final ElementsCollection button = $$(".button");
    private final SelenideElement continueButton = button.get(2);
    private final SelenideElement cardNumberError = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]");
    private final SelenideElement monthError = $(byText("Месяц")).parent().$(".input__sub");
    private final SelenideElement yearError = $(byText("Год")).parent().$(".input__sub");
    private final SelenideElement ownerError = $(byText("Владелец")).parent().$(".input__sub");
    private final SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(".input__sub");


    public void fillCard(DataHelper.CardInfo cardInfo){
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        cvc.setValue(cardInfo.getCvcCVV());
        continueButton.click();
    }

    public void cardNumberErrorVisible(){
        cardNumberError.shouldBe(visible);
    }

    public void monthErrorVisible(){
        monthError.shouldBe(visible);
    }

    public void yearErrorVisible(){
        yearError.shouldBe(visible);
    }

    public void ownerErrorVisible(){
        ownerError.shouldBe(visible);
    }

    public void cvcErrorVisible(){
        cvcError.shouldBe(visible);
    }
    public void successfullPayment() {
        $(".notification_status_ok").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void declinedPayment() {
        $("notification_status_error").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }



}