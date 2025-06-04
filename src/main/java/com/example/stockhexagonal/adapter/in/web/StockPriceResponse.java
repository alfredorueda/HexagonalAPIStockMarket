package com.example.stockhexagonal.adapter.in.web;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * REST API response class for stock price information
 */
@Schema(description = "Stock price response")
public class StockPriceResponse {
    
    @Schema(description = "Stock symbol", example = "AAPL")
    private final String symbol;
    
    @Schema(description = "Current stock price", example = "175.23")
    private final double price;
    
    @Schema(description = "Timestamp of the price data", example = "2023-12-08T14:30:00")
    private final LocalDateTime timestamp;

    public StockPriceResponse(String symbol, double price, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
