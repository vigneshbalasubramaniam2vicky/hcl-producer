package com.example.paymentingestor.controller;

import com.example.paymentingestor.entity.Account;
import com.example.paymentingestor.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        return accountService.getAccount(accountId);
    }
}
