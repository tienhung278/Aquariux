package com.aquariux.trading.controller;

import com.aquariux.trading.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellController {

    private final PriceService priceService;

    public SellController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/latest/{symbol}")
    public Double getLatestBestSell(@PathVariable String symbol) {
        return priceService.getLatestBestSellPrice(symbol.toUpperCase());
    }

}
