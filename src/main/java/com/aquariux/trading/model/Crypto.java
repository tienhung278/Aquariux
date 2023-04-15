package com.aquariux.trading.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crypto {

    private String symbol;
    private Double buy;
    private Double sell;

    public static class OrderByBuyPriceAsc implements Comparator<Crypto> {

        @Override
        public int compare(Crypto o1, Crypto o2) {
            return o1.getBuy() > o2.getBuy() ? 1 : (o1.getBuy() < o2.getBuy() ? -1 : 0);
        }
    }

    public static class OrderBySellPriceDesc implements Comparator<Crypto> {

        @Override
        public int compare(Crypto o1, Crypto o2) {
            return o1.getSell() < o2.getSell() ? 1 : (o1.getSell() > o2.getSell() ? -1 : 0);
        }
    }
}
