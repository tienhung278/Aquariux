package com.aquariux.trading.service;

import com.aquariux.trading.model.Binance;
import com.aquariux.trading.model.Crypto;
import com.aquariux.trading.model.Houbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoolingPriceServiceImpl implements PoolingPriceService {

    private final RestTemplate restTemplate;
    private final PriceService priceService;
    @Value("${pricesource.binance.url}")
    private String binanceUrl;
    @Value("${pricesource.houbi.url}")
    private String houbiUrl;
    private List<Crypto> binanceCryptos;
    private List<Crypto> houbiCryptos;

    @Autowired
    public PoolingPriceServiceImpl(RestTemplate restTemplate, PriceService priceService) {
        this.restTemplate = restTemplate;
        this.priceService = priceService;
    }

    public List<Crypto> getBinanceCryptos() {
        return binanceCryptos;
    }

    public List<Crypto> getHoubiCryptos() {
        return houbiCryptos;
    }

    @Scheduled(fixedRate = 10000)
    private void getBinancePrice() {
        binanceCryptos = getBinancePrice(List.of("BTCUSDT", "ETHUSDT"));
    }

    private List<Crypto> getBinancePrice(List<String> symbols) {
        Binance[] data = restTemplate.getForObject(binanceUrl, Binance[].class);

        return Arrays.stream(data)
                .filter(c -> symbols.contains(c.getSymbol().toUpperCase()))
                .map(c -> new Crypto(c.getSymbol().toUpperCase(), c.getBidPrice(), c.getAskPrice()))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 10000)
    private void pollHoubiPrice() {
        houbiCryptos = getHoubiPrice(List.of("BTCUSDT", "ETHUSDT"));
    }

    private List<Crypto> getHoubiPrice(List<String> symbols) {
        Houbi data = restTemplate.getForObject(houbiUrl, Houbi.class);

        return Arrays.stream(data.getData())
                .filter(c -> symbols.contains(c.getSymbol().toUpperCase()))
                .map(c -> new Crypto(c.getSymbol().toUpperCase(), c.getBid(), c.getAsk()))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 11000)
    private void addBestBuy() {
        List<Crypto> cryptos = new ArrayList<>();

        if (binanceCryptos != null) {
            cryptos.addAll(binanceCryptos);
        }

        if (houbiCryptos != null) {
            cryptos.addAll(houbiCryptos);
        }

        if (!cryptos.isEmpty()) {
            Crypto bestBuy = priceService.getBestBuyPrice("ETHUSDT", cryptos);

            if (bestBuy != null) {
                priceService.addBestBuyPrice(bestBuy);
            }

            bestBuy = priceService.getBestBuyPrice("BTCUSDT", cryptos);

            if (bestBuy != null) {
                priceService.addBestBuyPrice(bestBuy);
            }
        }
    }

    @Scheduled(fixedRate = 11000)
    private void addSellBuy() {
        List<Crypto> cryptos = new ArrayList<>();

        if (binanceCryptos != null) {
            cryptos.addAll(binanceCryptos);
        }

        if (houbiCryptos != null) {
            cryptos.addAll(houbiCryptos);
        }

        if (!cryptos.isEmpty()) {
            Crypto bestSell = priceService.getBestSellPrice("ETHUSDT", cryptos);

            if (bestSell != null) {
                priceService.addBestSellPrice(bestSell);
            }

            bestSell = priceService.getBestSellPrice("BTCUSDT", cryptos);

            if (bestSell != null) {
                priceService.addBestSellPrice(bestSell);
            }
        }
    }
}
