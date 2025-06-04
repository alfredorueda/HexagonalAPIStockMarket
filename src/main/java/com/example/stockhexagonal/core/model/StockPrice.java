package com.example.stockhexagonal.core.model;

import java.time.LocalDateTime;

/**
 * Domain model class representing stock price data
 */
public class StockPrice {
    private final String symbol;
    private final double currentPrice;
    private final LocalDateTime timestamp;

    public StockPrice(String symbol, double currentPrice, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
