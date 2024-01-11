package com.love2code.banking.service;

import com.love2code.banking.dto.BankResponse;
import com.love2code.banking.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
