package com.love2code.banking.service.impl;

import com.love2code.banking.dto.AccountInfo;
import com.love2code.banking.dto.BankResponse;
import com.love2code.banking.dto.EmailDetails;
import com.love2code.banking.dto.UserRequest;
import com.love2code.banking.entity.User;
import com.love2code.banking.repository.UserRepository;
import com.love2code.banking.service.EmailService;
import com.love2code.banking.service.UserService;
import com.love2code.banking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

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

    public BankResponse checkEmailAddress(UserRequest userRequest, User newUser) {
        if (!userRepository.existsByEmail(userRequest.getEmail())) {
            sendEmail(newUser);
            userRepository.save(newUser);
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                    .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(newUser.getAccountBalance())
                            .accountNumber(newUser.getAccountNumber())
                            .accountName(newUser.getFirstName() + " "
                                    + newUser.getLastName() + " "
                                    + newUser.getOtherName())
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

    public void sendEmail(User newUser) {
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(newUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("Congratulations! Your Account Has Been Successfully Created." +
                        "\nYour Account Details" +
                        "\nAccount Name: " + newUser.getFirstName() + " " + newUser.getOtherName() + " " + newUser.getLastName() +
                        "\nAccount Number: " + newUser.getAccountNumber())
                .build();

        emailService.sendEmailAlert(emailDetails);
    }


}
