package com.aquariux.trading.service;

import com.aquariux.trading.dto.WriteTrans;

public interface TransactionService {

    void addBuyTransaction(WriteTrans writeTrans);

    void addSellTransaction(WriteTrans writeTrans);
}
