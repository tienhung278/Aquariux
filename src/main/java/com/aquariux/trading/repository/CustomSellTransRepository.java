package com.aquariux.trading.repository;

import com.aquariux.trading.entity.SellTrans;

import java.util.List;

public interface CustomSellTransRepository {

    List<SellTrans> getTransByUser(String userId);
}
