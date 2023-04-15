package com.aquariux.trading.repository;

import com.aquariux.trading.entity.Buy;
import jakarta.persistence.EntityManager;
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
    public Buy getLatestBestBuyPrice(String symbol) {
        return (Buy) entityManager.createNativeQuery("SELECT TOP 1 * " +
                        "FROM Buy " +
                        "WHERE symbol = :symbol " +
                        "ORDER BY id DESC", Buy.class)
                .setParameter("symbol", symbol)
                .getSingleResult();
    }
}
