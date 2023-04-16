package com.aquariux.trading.repository;

import com.aquariux.trading.entity.BuyTrans;

import java.util.List;

public interface CustomBuyTransRepository {

    List<BuyTrans> getTransByUser(String userId);
}
