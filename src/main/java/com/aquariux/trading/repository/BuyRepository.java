package com.aquariux.trading.repository;

import com.aquariux.trading.entity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Long>, CustomBuyRepository {
}
