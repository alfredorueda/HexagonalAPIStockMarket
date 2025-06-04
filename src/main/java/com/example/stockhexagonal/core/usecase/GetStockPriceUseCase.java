package com.example.stockhexagonal.core.usecase;

import com.example.stockhexagonal.core.model.StockPrice;

/**
 * Primary port (input) for retrieving stock price information
 */
public interface GetStockPriceUseCase {
    
    /**
     * Retrieves the current stock price for the given symbol
     * 
     * @param symbol the stock symbol (e.g., AAPL, MSFT)
     * @return StockPrice object containing the current price and timestamp
     * @throws StockNotFoundException if the stock symbol is not found
     */
    StockPrice getStockPrice(String symbol) throws StockNotFoundException;
}
