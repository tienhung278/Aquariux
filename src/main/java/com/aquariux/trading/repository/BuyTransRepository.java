package com.aquariux.trading.repository;

import com.aquariux.trading.entity.BuyTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyTransRepository extends JpaRepository<BuyTrans, Long>, CustomBuyTransRepository {
}
