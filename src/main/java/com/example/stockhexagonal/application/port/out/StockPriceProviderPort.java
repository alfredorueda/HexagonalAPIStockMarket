package com.example.stockhexagonal.application.port.out;

import com.example.stockhexagonal.model.StockPrice;

/**
 * Secondary port (output) for fetching stock price data from external sources
 */
public interface StockPriceProviderPort {
    
    /**
     * Fetches the current stock price for the given symbol
     * 
     * @param symbol the stock symbol (e.g., AAPL, MSFT)
     * @return StockPrice object containing the current price and timestamp
     * @throws StockNotFoundException if the stock symbol is not found
     */
    StockPrice fetchStockPrice(String symbol);
}