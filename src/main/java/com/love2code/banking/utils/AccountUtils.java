package com.love2code.banking.utils;

import java.time.Year;

public class AccountUtils {
    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account created!";
    public static final String ACCOUNT_CREATION_SUCCESS = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account has benn successfully created!";

    public static String generateAccountNumber() {
        Year currentYear = Year.now();
        int max = 999999;
        int min = 100000;
        int randNumber = (int) Math.floor(Math.random() * (max - min + 1));
        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString();
    }

}