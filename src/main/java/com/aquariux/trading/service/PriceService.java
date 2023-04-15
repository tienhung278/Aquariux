package com.aquariux.trading.service;

import com.aquariux.trading.model.Crypto;

import java.util.List;

public interface PriceService {

    Crypto getBestBuyPrice(String symbol, List<Crypto> cryptos);

    Crypto getBestSellPrice(String symbol, List<Crypto> cryptos);

    void addBestBuyPrice(Crypto crypto);

    void addBestSellPrice(Crypto crypto);

    Double getLatestBestBuyPrice(String symbol);

    Double getLatestBestSellPrice(String symbol);
}
