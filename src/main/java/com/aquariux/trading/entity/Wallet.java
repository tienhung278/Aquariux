package com.aquariux.trading.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "balance")
    private Double balance;
}
