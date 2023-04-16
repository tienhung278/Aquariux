package com.aquariux.trading.repository;

import com.aquariux.trading.entity.SellTrans;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CustomSellTransRepositoryImpl implements CustomSellTransRepository {

    private final EntityManager entityManager;

    public CustomSellTransRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<SellTrans> getTransByUser(String userId) {
        return entityManager.createNativeQuery("SELECT * " +
                        "FROM Sell_Trans " +
                        "WHERE created_by = :created_by", SellTrans.class)
                .setParameter("created_by", userId)
                .getResultList();
    }
}
