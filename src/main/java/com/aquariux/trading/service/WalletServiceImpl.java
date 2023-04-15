package com.aquariux.trading.service;

import com.aquariux.trading.dto.ReadWallet;
import com.aquariux.trading.dto.WriteWallet;
import com.aquariux.trading.entity.Wallet;
import com.aquariux.trading.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void createWallet(WriteWallet writeWallet) {
        Wallet wallet = new Wallet();
        wallet.setUserId(writeWallet.getUserId());
        wallet.setBalance(writeWallet.getBalance());

        walletRepository.save(wallet);
    }

    @Override
    public ReadWallet getBalance(String userId) {
        ReadWallet readWallet = null;

        Optional<Wallet> optionalWallet = walletRepository.findById(userId);

        if (optionalWallet.isPresent()) {
            Wallet wallet = optionalWallet.get();

            readWallet = new ReadWallet();
            readWallet.setUserId(wallet.getUserId());
            readWallet.setBalance(wallet.getBalance());
        } else {
            throw new IllegalArgumentException();
        }

        return readWallet;
    }
}
