package com.aquariux.trading.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Sell")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price")
    private Double price;
}
