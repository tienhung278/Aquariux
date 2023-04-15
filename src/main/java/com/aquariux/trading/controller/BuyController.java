package com.aquariux.trading.controller;

import com.aquariux.trading.dto.ReadCrypto;
import com.aquariux.trading.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy")
public class BuyController {

    private final PriceService priceService;

    public BuyController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/latest/{symbol}")
    public ReadCrypto getLatestBestBuy(@PathVariable String symbol) {
        return priceService.getLatestBestBuyPrice(symbol.toUpperCase());
    }
}
