package com.aquariux.trading.repository;

import com.aquariux.trading.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long>, CustomSellRepository {
}
