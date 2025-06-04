package com.example.stockhexagonal.adapter.out.mock;

import com.example.stockhexagonal.core.model.StockPrice;
import com.example.stockhexagonal.core.usecase.StockNotFoundException;
import com.example.stockhexagonal.port.out.StockPricePort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Secondary adapter that implements StockPricePort with mock data
 */
@Component
@Profile("mock")
public class MockStockPriceAdapter implements StockPricePort {

    private final Map<String, Double> mockStockPrices;
    private final Random random;

    public MockStockPriceAdapter() {
        this.mockStockPrices = new HashMap<>();
        this.random = new Random();
        
        // Initialize with some mock data
        mockStockPrices.put("AAPL", 175.50);
        mockStockPrices.put("MSFT", 370.20);
        mockStockPrices.put("GOOGL", 133.10);
        mockStockPrices.put("AMZN", 147.80);
        mockStockPrices.put("META", 325.90);
        mockStockPrices.put("TSLA", 240.35);
    }

    @Override
    public StockPrice fetchStockPrice(String symbol) throws StockNotFoundException {
        // Check if the stock exists in our mock data
        if (!mockStockPrices.containsKey(symbol)) {
            throw new StockNotFoundException(symbol);
        }
        
        // Get the base price and add some randomness to simulate price fluctuations
        double basePrice = mockStockPrices.get(symbol);
        double fluctuation = basePrice * 0.02 * (random.nextDouble() - 0.5); // ±1% fluctuation
        double currentPrice = basePrice + fluctuation;
        
        return new StockPrice(symbol, currentPrice, LocalDateTime.now());
    }
}
