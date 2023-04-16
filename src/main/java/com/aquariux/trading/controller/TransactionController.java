package com.aquariux.trading.controller;

import com.aquariux.trading.dto.ReadTrans;
import com.aquariux.trading.dto.WriteTrans;
import com.aquariux.trading.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/buy/{userId}")
    public List<ReadTrans> getBuyTransaction(@PathVariable String userId) {
        return transactionService.getBuyTrans(userId);
    }

    @GetMapping("/sell/{userId}")
    public List<ReadTrans> getSellTransaction(@PathVariable String userId) {
        return transactionService.getSellTrans(userId);
    }
}
