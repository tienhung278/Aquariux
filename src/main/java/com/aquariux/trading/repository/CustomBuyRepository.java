package com.aquariux.trading.repository;

import com.aquariux.trading.entity.Buy;

public interface CustomBuyRepository {

    Buy getLatestBestBuyPrice(String symbol);
}
