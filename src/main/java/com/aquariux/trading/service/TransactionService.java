package com.aquariux.trading.service;

public interface TransactionService {

    void addBuyTransaction(String userId, Long cryptoId);

    void addSellTransaction(String userId, Long cryptoId);
}
