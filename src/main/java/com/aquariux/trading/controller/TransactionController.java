package com.aquariux.trading.controller;

import com.aquariux.trading.dto.WriteTrans;
import com.aquariux.trading.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/buy")
    public void createBuyTransaction(@RequestBody WriteTrans writeTrans) {
        transactionService.addBuyTransaction(writeTrans);
    }

    @PostMapping("/sell")
    public void createSellTransaction(@RequestBody WriteTrans writeTrans) {
        transactionService.addSellTransaction(writeTrans);
    }
}
