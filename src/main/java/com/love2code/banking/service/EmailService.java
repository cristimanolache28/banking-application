package com.love2code.banking.service;

import com.love2code.banking.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
