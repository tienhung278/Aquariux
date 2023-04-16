package com.aquariux.trading.service;

import com.aquariux.trading.dto.ReadTrans;
import com.aquariux.trading.dto.WriteTrans;

import java.util.List;

public interface TransactionService {

    void addBuyTransaction(WriteTrans writeTrans);

    void addSellTransaction(WriteTrans writeTrans);

    List<ReadTrans> getBuyTrans(String userId);

    List<ReadTrans> getSellTrans(String userId);
}
