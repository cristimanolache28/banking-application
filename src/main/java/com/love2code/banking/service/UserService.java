package com.love2code.banking.service;

import com.love2code.banking.dto.BankResponse;
import com.love2code.banking.dto.CreditDebitRequest;
import com.love2code.banking.dto.EnquiryRequest;
import com.love2code.banking.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);

    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest enquiryRequest);
    BankResponse creditAccount(CreditDebitRequest request);
}
