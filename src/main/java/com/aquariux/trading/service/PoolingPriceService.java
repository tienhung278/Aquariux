package com.aquariux.trading.service;

import com.aquariux.trading.model.Crypto;

import java.util.List;

public interface PoolingPriceService {

    List<Crypto> getBinanceCryptos();

    List<Crypto> getHoubiCryptos();
}
