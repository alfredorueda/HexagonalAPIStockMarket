package com.example.stockhexagonal.adapter.out.mock;

import com.example.stockhexagonal.application.port.out.StockNotFoundException;
import com.example.stockhexagonal.application.port.out.StockPriceProviderPort;
import com.example.stockhexagonal.model.StockPrice;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mock implementation of the StockPriceProviderPort for testing and development
 */
@Component
@Profile("mock")
public class MockStockPriceAdapter implements StockPriceProviderPort {

    private final Map<String, Double> mockPrices = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public MockStockPriceAdapter() {
        // Initialize with some sample stock prices
        mockPrices.put("AAPL", 175.23);
        mockPrices.put("MSFT", 310.65);
        mockPrices.put("GOOGL", 130.45);
        mockPrices.put("AMZN", 132.85);
        mockPrices.put("TSLA", 239.29);
        mockPrices.put("NVDA", 415.15);
        mockPrices.put("META", 325.66);
        mockPrices.put("JPM", 153.44);
        mockPrices.put("BAC", 32.15);
        mockPrices.put("WFC", 53.33);
        mockPrices.put("GS", 377.28);
    }

    @Override
    public StockPrice fetchStockPrice(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock symbol cannot be empty");
        }
        
        String normalizedSymbol = symbol.toUpperCase();
        
        // Check if the symbol exists in our mock database
        if (!mockPrices.containsKey(normalizedSymbol)) {
            throw new StockNotFoundException(normalizedSymbol);
        }
        
        // Get the base price
        double basePrice = mockPrices.get(normalizedSymbol);
        
        // Add some small random variation to simulate market changes
        double variation = (random.nextDouble() - 0.5) * 2.0; // -1.0 to 1.0
        double currentPrice = basePrice * (1.0 + (variation / 100.0));
        
        // Round to 2 decimal places
        currentPrice = Math.round(currentPrice * 100.0) / 100.0;
        
        return new StockPrice(normalizedSymbol, currentPrice, LocalDateTime.now(), "USD");
    }
}
