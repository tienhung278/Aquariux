package com.aquariux.trading.repository;

public interface CustomSellRepository {

    Double getLatestBestSellPrice(String symbol);
}
