package com.aquariux.trading.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomSellRepositoryImpl implements CustomSellRepository {

    private final EntityManager entityManager;

    @Autowired
    public CustomSellRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Double getLatestBestSellPrice(String symbol) {
        try {
            return (Double) entityManager.createNativeQuery("SELECT TOP 1 price " +
                            "FROM Sell " +
                            "WHERE symbol = :symbol " +
                            "ORDER BY id DESC", Double.class)
                    .setParameter("symbol", symbol)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
