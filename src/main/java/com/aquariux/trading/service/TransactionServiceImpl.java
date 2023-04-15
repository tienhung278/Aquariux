package com.aquariux.trading.service;

import com.aquariux.trading.entity.Buy;
import com.aquariux.trading.entity.BuyTrans;
import com.aquariux.trading.entity.Sell;
import com.aquariux.trading.entity.SellTrans;
import com.aquariux.trading.repository.BuyRepository;
import com.aquariux.trading.repository.BuyTransRepository;
import com.aquariux.trading.repository.SellRepository;
import com.aquariux.trading.repository.SellTransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final BuyRepository buyRepository;
    private final SellRepository sellRepository;
    private final BuyTransRepository buyTransRepository;
    private final SellTransRepository sellTransRepository;

    @Autowired
    public TransactionServiceImpl(BuyRepository buyRepository,
                                  BuyTransRepository buyTransRepository,
                                  SellRepository sellRepository,
                                  SellTransRepository sellTransRepository) {
        this.buyTransRepository = buyTransRepository;
        this.sellTransRepository = sellTransRepository;
        this.buyRepository = buyRepository;
        this.sellRepository = sellRepository;
    }

    @Override
    public void addBuyTransaction(String userId, Long cryptoId) {
        Buy crypto = null;

        Optional<Buy> optionalCrypto = buyRepository.findById(cryptoId);

        if (optionalCrypto.isPresent()) {
            crypto = optionalCrypto.get();

            BuyTrans buyTrans = new BuyTrans();
            buyTrans.setCrypto(crypto);
            buyTrans.setCreatedBy(userId);
            buyTrans.setCreatedAt(LocalDateTime.now());

            buyTransRepository.save(buyTrans);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addSellTransaction(String userId, Long cryptoId) {
        Sell crypto = null;

        Optional<Sell> optionalCrypto = sellRepository.findById(cryptoId);

        if (optionalCrypto.isPresent()) {
            crypto = optionalCrypto.get();

            SellTrans sellTrans = new SellTrans();
            sellTrans.setCrypto(crypto);
            sellTrans.setCreatedBy(userId);
            sellTrans.setCreatedAt(LocalDateTime.now());

            sellTransRepository.save(sellTrans);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
