package com.aquariux.trading.service;

import com.aquariux.trading.entity.Buy;
import com.aquariux.trading.entity.Sell;
import com.aquariux.trading.model.Crypto;
import com.aquariux.trading.repository.BuyRepository;
import com.aquariux.trading.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final BuyRepository buyRepository;
    private final SellRepository sellRepository;

    @Autowired
    public PriceServiceImpl(BuyRepository buyRepository, SellRepository sellRepository) {
        this.buyRepository = buyRepository;
        this.sellRepository = sellRepository;
    }

    @Override
    public Crypto getBestBuyPrice(String symbol, List<Crypto> cryptos) {
        Crypto bestBuy = null;

        Optional<Crypto> optionalCrypto = cryptos
                .stream()
                .filter(c -> c.getSymbol().contains(symbol))
                .sorted(new Crypto.OrderByBuyPriceAsc())
                .findFirst();

        if (optionalCrypto.isPresent()) {
            bestBuy = optionalCrypto.get();
        }

        return bestBuy;
    }

    @Override
    public Crypto getBestSellPrice(String symbol, List<Crypto> cryptos) {
        Crypto sellBuy = null;

        Optional<Crypto> optionalCrypto = cryptos
                .stream()
                .filter(c -> c.getSymbol().contains(symbol))
                .sorted(new Crypto.OrderBySellPriceDesc())
                .findFirst();

        if (optionalCrypto.isPresent()) {
            sellBuy = optionalCrypto.get();
        }

        return sellBuy;
    }

    @Override
    public void addBestBuyPrice(Crypto crypto) {
        buyRepository.save(convertToBuy(crypto));
    }

    @Override
    public void addBestSellPrice(Crypto crypto) {
        sellRepository.save(convertToSell(crypto));
    }

    @Override
    public Double getLatestBestBuyPrice(String symbol) {
        return buyRepository.getLatestBestBuyPrice(symbol);
    }

    @Override
    public Double getLatestBestSellPrice(String symbol) {
        return sellRepository.getLatestBestSellPrice(symbol);
    }

    private Buy convertToBuy(Crypto crypto) {
        Buy buy = new Buy();
        buy.setSymbol(crypto.getSymbol());
        buy.setPrice(crypto.getBuy());

        return buy;
    }

    private Sell convertToSell(Crypto crypto) {
        Sell sell = new Sell();
        sell.setSymbol(crypto.getSymbol());
        sell.setPrice(crypto.getBuy());

        return sell;
    }
}
