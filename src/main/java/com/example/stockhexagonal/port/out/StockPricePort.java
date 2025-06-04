package com.example.stockhexagonal.port.out;

import com.example.stockhexagonal.core.model.StockPrice;
import com.example.stockhexagonal.core.usecase.StockNotFoundException;

/**
 * Secondary port (output) for fetching stock price data from external sources
 */
public interface StockPricePort {
    
    /**
     * Fetches the current stock price for the given symbol
     * 
     * @param symbol the stock symbol (e.g., AAPL, MSFT)
     * @return StockPrice object containing the current price and timestamp
     * @throws StockNotFoundException if the stock symbol is not found
     */
    StockPrice fetchStockPrice(String symbol) throws StockNotFoundException;
}
