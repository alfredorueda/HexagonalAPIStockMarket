package com.example.stockhexagonal.adapter.in.web;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * REST API response class for individual investment information
 */
@Schema(description = "Investment response")
public class InvestmentResponse {
    
    @Schema(description = "Stock symbol", example = "AAPL")
    private final String symbol;
    
    @Schema(description = "Current stock price", example = "175.23")
    private final double price;
    
    @Schema(description = "Currency of the price", example = "USD")
    private final String currency;
    
    @Schema(description = "Timestamp when the price was fetched", example = "2023-12-08T14:30:00")
    private final LocalDateTime fetchedAt;

    public InvestmentResponse(String symbol, double price, String currency, LocalDateTime fetchedAt) {
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