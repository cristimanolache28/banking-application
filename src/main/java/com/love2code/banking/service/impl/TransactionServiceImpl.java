package com.love2code.banking.service.impl;

import com.love2code.banking.dto.TransactionDto;
import com.love2code.banking.entity.Transaction;
import com.love2code.banking.repository.TransactionRepository;
import com.love2code.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(TransactionDto transactionDto)
    {
        Transaction transaction = Transaction.builder()
                .accountNumber(transactionDto.getAccountNumber())
                .transactionType(transactionDto.getTransactionType())
                .amount(transactionDto.getAmount())
                .status("SUCCESS")
                .build();
        transactionRepository.save(transaction);
        System.out.println("Transaction saved successfully.");
    }
}
