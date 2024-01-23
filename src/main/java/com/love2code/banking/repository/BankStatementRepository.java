package com.love2code.banking.repository;

import com.love2code.banking.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankStatementRepository {

    List<Transaction> allTransaction();

}
