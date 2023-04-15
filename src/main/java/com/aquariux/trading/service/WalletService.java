package com.aquariux.trading.service;

import com.aquariux.trading.dto.ReadWallet;
import com.aquariux.trading.dto.WriteWallet;

public interface WalletService {

    void createWallet(WriteWallet writeWallet);

    ReadWallet getBalance(String userId);
}
