package com.aquariux.trading.controller;

import com.aquariux.trading.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/buy/{userId}/{id}")
    public void createBuyTransaction(@PathVariable String userId, @PathVariable Long id) {
        transactionService.addBuyTransaction(userId, id);
    }

    @PostMapping("/sell/{userId}/{id}")
    public void createSellTransaction(@PathVariable String userId, @PathVariable Long id) {
        transactionService.addSellTransaction(userId, id);
    }
}
