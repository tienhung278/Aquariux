package com.aquariux.trading.controller;

import com.aquariux.trading.dto.ReadCrypto;
import com.aquariux.trading.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

    private PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/buy/{symbol}")
    public ReadCrypto getLatestBestBuy(@PathVariable String symbol) {
        return priceService.getLatestBestBuyPrice(symbol.toUpperCase());
    }

    @GetMapping("/sell/{symbol}")
    public ReadCrypto getLatestBestSell(@PathVariable String symbol) {
        return priceService.getLatestBestSellPrice(symbol.toUpperCase());
    }
}
