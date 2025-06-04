package com.example.stockhexagonal.application;

import com.example.stockhexagonal.application.port.in.GetStockPriceUseCase;
import com.example.stockhexagonal.application.port.out.StockNotFoundException;
import com.example.stockhexagonal.application.port.out.StockPriceProviderPort;
import com.example.stockhexagonal.model.StockPrice;
import org.springframework.stereotype.Service;

/**
 * Implementation of the GetStockPriceUseCase that uses a StockPriceProviderPort
 * to fetch stock prices from an external source
 */
@Service
public class GetStockPriceService implements GetStockPriceUseCase {

    private final StockPriceProviderPort stockPriceProviderPort;

    public GetStockPriceService(StockPriceProviderPort stockPriceProviderPort) {
        this.stockPriceProviderPort = stockPriceProviderPort;
    }

    @Override
    public StockPrice getStockPrice(String symbol) throws StockNotFoundException {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock symbol cannot be empty");
        }
        
        return stockPriceProviderPort.fetchStockPrice(symbol.toUpperCase());
    }
}