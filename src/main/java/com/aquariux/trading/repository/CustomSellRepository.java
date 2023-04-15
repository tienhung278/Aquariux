package com.aquariux.trading.repository;

import com.aquariux.trading.entity.Sell;

public interface CustomSellRepository {

    Sell getLatestBestSellPrice(String symbol);
}
