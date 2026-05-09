package com.example.paymentingestor.service;

import com.example.paymentingestor.entity.Account;
import com.example.paymentingestor.exception.ResourceNotFoundException;
import com.example.paymentingestor.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account getAccount(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + accountId));
    }
}
