package com.aquariux.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadTrans {

    private Long id;

    private String createdBy;

    private LocalDateTime createdAt;

    private ReadCrypto crypto;
}
