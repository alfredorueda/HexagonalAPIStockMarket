package com.example.stockhexagonal.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain model class representing stock price data
 */
public class StockPrice {
    private final String symbol;
    private final double currentPrice;
    private final LocalDateTime timestamp;
    private final String currency;

    /**
     * Creates a new StockPrice with the given details
     *
     * @param symbol The stock symbol (e.g., AAPL, MSFT)
     * @param currentPrice The current price of the stock
     * @param timestamp The timestamp when the price was recorded
     * @param currency The currency of the price (e.g., USD)
     */
    public StockPrice(String symbol, double currentPrice, LocalDateTime timestamp, String currency) {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }
        if (currentPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        
        this.symbol = symbol.toUpperCase();
        this.currentPrice = currentPrice;
        this.timestamp = Objects.requireNonNull(timestamp, "Timestamp cannot be null");
        this.currency = currency != null ? currency : "USD"; // Default to USD if currency not specified
    }

    /**
     * Creates a new StockPrice with the given details, assuming USD currency
     *
     * @param symbol The stock symbol (e.g., AAPL, MSFT)
     * @param currentPrice The current price of the stock
     * @param timestamp The timestamp when the price was recorded
     */
    public StockPrice(String symbol, double currentPrice, LocalDateTime timestamp) {
        this(symbol, currentPrice, timestamp, "USD");
    }

    /**
     * @return The stock symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return The current price of the stock
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @return The timestamp when the price was recorded
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return The currency of the price
     */
    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPrice that = (StockPrice) o;
        return Double.compare(that.currentPrice, currentPrice) == 0 &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, currentPrice, timestamp, currency);
    }
}