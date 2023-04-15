package com.aquariux.trading.controller;

import com.aquariux.trading.dto.ReadWallet;
import com.aquariux.trading.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance/{userId}")
    public ReadWallet getLatestBestBuy(@PathVariable String userId) {
        return walletService.getBalance(userId);
    }
}
