package ru.netology.data;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    @Value
    @RequiredArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String cvcCVV;
    }

    //номер
    public static String getApprovedCardNumber() {
        return ("1111 2222 3333 4444");
    }

    public static String getDeclinedCardNumber() {
        return ("5555 6666 7777 8888");
    }
    public static String getApprovedCardNumberNoSpace(){
        return "1111222233334444";
    }

    public static String getShortCardNumber() {
        return ("5555 6666 7777 888");
    }
    public static String getTheCardNumberEnteredIsLong(){
        return "1111 2222 3333 4444 1";
    }

    public static String getCardNumberWithSigns() {
        return ("5555 6666 7777 ***8");
    }

    public static String getCardNumberWithLetters() {
        return ("5555 6666 7777 absd");
    }

    //месяц
    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }
    public static String getMonthMinus1(){
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String getMonth12(){
        return ("12");
    }
    public static String getMonthOver12() {
        return ("13");
    }

    public static String getMonth01(){
        return "01";
    }

    public static String getMonthWithLetters() {
        return ("qq");
    }

    public static String getMonthWithSigns() {
        return ("1*");
    }

    public static String getMonthWithOneDigit() {
        return ("1");
    }

    public static String getMonthWithNulls() {
        return ("00");
    }
    public static String getMonthWithNull() {
        return ("0");
    }

    public static String getMonthWithThreeDigits() {
        return ("123");
    }

    //год
    public static String getValidYear() {
        return String.format("%ty", Year.now());
    }

    public static String getPastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty",Year.now().minusYears(2));
    }
    public static String getValidNextYear(){
        return String.format("%ty",Year.now().plusYears(2));
    }

    public static String getYearWithLetters() {
        return ("aw");
    }

    public static String getYearWithSigns() {
        return ("&&");
    }

    public static String getYearWithOneDigit() {
        return ("9");
    }

    //владелец
    public static String getOwnerName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    public static String getOwnerNameInRussia() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getOwnerNameWithDigits() {
        return "qwerty 1111";
    }

    public static String getOwnerNameWithSigns() {
        return "*** qwerty";
    }

    public static String getOwnerNameShort() {
        return "I";
    }

    public static String getOwnerNameWithDoubleName() {
        return "Mikhail Saltykov-Shchedrin";
    }

    //cvc
    public static String getCVC() {
        return "123";
    }

    public static String getCVCwithLetters() {
        return "abc";
    }

    public static String getCVCwithSigns() {
        return "23*";
    }

    public static String getCVCshort() {
        return "22";
    }
    public static String getCVClong() {
        return "1234";
    }
}