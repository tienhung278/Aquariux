package com.aquariux.trading.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomBuyRepositoryImpl implements CustomBuyRepository {

    private final EntityManager entityManager;

    @Autowired
    public CustomBuyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Double getLatestBestBuyPrice(String symbol) {
        try {
            return (Double) entityManager.createNativeQuery("SELECT TOP 1 price " +
                            "FROM Buy " +
                            "WHERE symbol = :symbol " +
                            "ORDER BY id DESC", Double.class)
                    .setParameter("symbol", symbol)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
