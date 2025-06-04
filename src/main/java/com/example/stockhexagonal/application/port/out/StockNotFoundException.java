package com.example.stockhexagonal.application.port.out;

/**
 * Exception thrown when a requested stock symbol cannot be found
 */
public class StockNotFoundException extends RuntimeException {
    
    public StockNotFoundException(String symbol) {
        super("Stock not found for symbol: " + symbol);
    }
}