package com.aquariux.trading.repository;

import com.aquariux.trading.entity.Sell;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomSellRepositoryImpl implements CustomSellRepository {

    private final EntityManager entityManager;

    @Autowired
    public CustomSellRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Sell getLatestBestSellPrice(String symbol) {
        try {
            return (Sell) entityManager.createNativeQuery("SELECT TOP 1 * " +
                            "FROM Sell " +
                            "WHERE symbol = :symbol " +
                            "ORDER BY id DESC", Sell.class)
                    .setParameter("symbol", symbol)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new NoResultException("No result found");
        }
    }
}
