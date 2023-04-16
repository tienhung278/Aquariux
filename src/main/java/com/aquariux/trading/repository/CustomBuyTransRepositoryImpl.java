package com.aquariux.trading.repository;

import com.aquariux.trading.entity.BuyTrans;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomBuyTransRepositoryImpl implements CustomBuyTransRepository {

    private final EntityManager entityManager;

    public CustomBuyTransRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<BuyTrans> getTransByUser(String userId) {
        return entityManager.createNativeQuery("SELECT * " +
                        "FROM Buy_Trans " +
                        "WHERE created_by = :created_by", BuyTrans.class)
                .setParameter("created_by", userId)
                .getResultList();
    }
}
