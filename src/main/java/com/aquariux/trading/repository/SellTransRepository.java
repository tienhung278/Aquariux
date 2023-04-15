package com.aquariux.trading.repository;

import com.aquariux.trading.entity.SellTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellTransRepository extends JpaRepository<SellTrans, Long> {
}
