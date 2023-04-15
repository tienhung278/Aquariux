package com.aquariux.trading.repository;

public interface CustomBuyRepository {

    Double getLatestBestBuyPrice(String symbol);
}
