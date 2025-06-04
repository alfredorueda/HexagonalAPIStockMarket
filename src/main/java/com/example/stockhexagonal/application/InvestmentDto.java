package com.example.stockhexagonal.application;

import java.time.LocalDateTime;

/**
 * Data Transfer Object representing a single stock investment
 */
public class InvestmentDto {
    private final String symbol;
    private final double price;
    private final String currency;
    private final LocalDateTime fetchedAt;

    public InvestmentDto(String symbol, double price, String currency, LocalDateTime fetchedAt) {
        this.symbol = symbol;
        this.price = price;
        this.currency = currency;
        this.fetchedAt = fetchedAt;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }
}