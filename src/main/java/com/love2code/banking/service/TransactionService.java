package com.love2code.banking.service;

import com.love2code.banking.dto.TransactionDto;
import com.love2code.banking.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto transaction);
}
