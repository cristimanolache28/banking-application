package com.love2code.banking.service.impl;

import com.love2code.banking.dto.AccountInfo;
import com.love2code.banking.dto.BankResponse;
import com.love2code.banking.dto.UserRequest;
import com.love2code.banking.entity.User;
import com.love2code.banking.repository.UserRepository;
import com.love2code.banking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();

        return checkEmailAddress(userRequest, newUser);

    }

    public BankResponse checkEmailAddress(UserRequest userRequest, User createdUser) {
        if (!userRepository.existsByEmail(userRequest.getEmail())) {
            userRepository.save(createdUser);
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                    .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(createdUser.getAccountBalance())
                            .accountNumber(createdUser.getAccountNumber())
                            .accountName(createdUser.getFirstName() + " "
                                    + createdUser.getLastName() + " "
                                    + createdUser.getOtherName())
                            .build())
                    .build();
        } else {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountError("The " + userRequest.getEmail() + " email address is already in use.")
                            .build())
                    .build();
        }
    }

}
