package com.aquariux.trading.service;

import com.aquariux.trading.dto.ReadCrypto;
import com.aquariux.trading.dto.ReadTrans;
import com.aquariux.trading.dto.WriteTrans;
import com.aquariux.trading.entity.*;
import com.aquariux.trading.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final BuyRepository buyRepository;
    private final SellRepository sellRepository;
    private final WalletRepository walletRepository;
    private final BuyTransRepository buyTransRepository;
    private final SellTransRepository sellTransRepository;

    @Autowired
    public TransactionServiceImpl(BuyRepository buyRepository,
                                  BuyTransRepository buyTransRepository,
                                  SellRepository sellRepository,
                                  SellTransRepository sellTransRepository,
                                  WalletRepository walletRepository) {
        this.buyTransRepository = buyTransRepository;
        this.sellTransRepository = sellTransRepository;
        this.buyRepository = buyRepository;
        this.sellRepository = sellRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public void addBuyTransaction(WriteTrans writeTrans) {
        Optional<Buy> optionalCrypto = buyRepository.findById(writeTrans.getCryptoId());
        Optional<Wallet> optionalWallet = walletRepository.findById(writeTrans.getUserId());

        if (optionalCrypto.isPresent() && optionalWallet.isPresent()) {
            Buy crypto = optionalCrypto.get();
            Wallet wallet = optionalWallet.get();

            Double balance = wallet.getBalance();
            Double price = crypto.getPrice();

            if (balance >= price) {
                BuyTrans buyTrans = new BuyTrans();
                buyTrans.setCrypto(crypto);
                buyTrans.setCreatedBy(writeTrans.getUserId());
                buyTrans.setCreatedAt(LocalDateTime.now());

                buyTransRepository.save(buyTrans);

                wallet.setBalance(balance - price);

                walletRepository.save(wallet);
            } else {
                throw new IllegalStateException("balance is not enough");
            }
        } else {
            throw new IllegalArgumentException("cryptoId or userId was invalid");
        }
    }

    @Override
    @Transactional
    public void addSellTransaction(WriteTrans writeTrans) {
        Optional<Sell> optionalCrypto = sellRepository.findById(writeTrans.getCryptoId());
        Optional<Wallet> optionalWallet = walletRepository.findById(writeTrans.getUserId());

        if (optionalCrypto.isPresent() && optionalWallet.isPresent()) {
            Sell crypto = optionalCrypto.get();
            Wallet wallet = optionalWallet.get();

            Double balance = wallet.getBalance();
            Double price = crypto.getPrice();

            SellTrans sellTrans = new SellTrans();
            sellTrans.setCrypto(crypto);
            sellTrans.setCreatedBy(writeTrans.getUserId());
            sellTrans.setCreatedAt(LocalDateTime.now());

            sellTransRepository.save(sellTrans);

            wallet.setBalance(balance + price);

            walletRepository.save(wallet);
        } else {
            throw new IllegalArgumentException("cryptoId or userId was invalid");
        }
    }

    @Override
    public List<ReadTrans> getBuyTrans(String userId) {
        List<BuyTrans> buyTrans = buyTransRepository.getTransByUser(userId);
        return buyTrans.stream()
                .map(t -> new ReadTrans(t.getId(),
                        t.getCreatedBy(),
                        t.getCreatedAt(),
                        new ReadCrypto(t.getCrypto().getId(),
                                t.getCrypto().getSymbol(),
                                t.getCrypto().getPrice())))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadTrans> getSellTrans(String userId) {
        List<SellTrans> sellTrans = sellTransRepository.getTransByUser(userId);
        return sellTrans.stream()
                .map(t -> new ReadTrans(t.getId(),
                        t.getCreatedBy(),
                        t.getCreatedAt(),
                        new ReadCrypto(t.getCrypto().getId(),
                                t.getCrypto().getSymbol(),
                                t.getCrypto().getPrice())))
                .collect(Collectors.toList());
    }
}
