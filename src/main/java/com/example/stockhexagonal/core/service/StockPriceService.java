package com.example.stockhexagonal.core.service;

import com.example.stockhexagonal.core.model.StockPrice;
import com.example.stockhexagonal.core.usecase.GetStockPriceUseCase;
import com.example.stockhexagonal.core.usecase.StockNotFoundException;
import com.example.stockhexagonal.port.out.StockPricePort;
import org.springframework.stereotype.Service;

/**
 * Implementation of the GetStockPriceUseCase that uses a StockPricePort
 * to fetch stock prices from an external source
 */
@Service
public class StockPriceService implements GetStockPriceUseCase {

    private final StockPricePort stockPricePort;

    public StockPriceService(StockPricePort stockPricePort) {
        this.stockPricePort = stockPricePort;
    }

    @Override
    public StockPrice getStockPrice(String symbol) throws StockNotFoundException {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock symbol cannot be empty");
        }
        
        return stockPricePort.fetchStockPrice(symbol.toUpperCase());
    }
}
