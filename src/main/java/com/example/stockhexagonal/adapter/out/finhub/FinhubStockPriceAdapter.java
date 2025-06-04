package com.example.stockhexagonal.adapter.out.finhub;

import com.example.stockhexagonal.application.port.out.StockNotFoundException;
import com.example.stockhexagonal.application.port.out.StockPriceProviderPort;
import com.example.stockhexagonal.model.StockPrice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;

/**
 * Implementation of StockPriceProviderPort that fetches real stock prices from Finhub API
 */
@Component
@Profile("finhub")
public class FinhubStockPriceAdapter implements StockPriceProviderPort {

    private final WebClient webClient;
    private final String apiKey;
    
    public FinhubStockPriceAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${finhub.api.url}") String apiUrl,
            @Value("${finhub.api.key}") String apiKey) {
        
        this.webClient = webClientBuilder
                .baseUrl(apiUrl)
                .build();
        this.apiKey = apiKey;
    }

    @Override
    public StockPrice fetchStockPrice(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock symbol cannot be empty");
        }
        
        String normalizedSymbol = symbol.toUpperCase();
        
        try {
            // This is a simplification - actual implementation would call the Finhub API
            // and map the response to a StockPrice object
            FinhubQuoteResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/quote")
                    .queryParam("symbol", normalizedSymbol)
                    .queryParam("token", apiKey)
                    .build())
                .retrieve()
                .bodyToMono(FinhubQuoteResponse.class)
                .block();
                
            if (response == null || response.getCurrentPrice() <= 0) {
                throw new StockNotFoundException(normalizedSymbol);
            }
            
            return new StockPrice(
                normalizedSymbol,
                response.getCurrentPrice(),
                LocalDateTime.now(),
                "USD"
            );
            
        } catch (WebClientResponseException e) {
            if (e.getStatusCode().value() == 404) {
                throw new StockNotFoundException(normalizedSymbol);
            }
            throw new RuntimeException("Error fetching stock price: " + e.getMessage(), e);
        }
    }
    
    // Inner class to deserialize the Finhub API response
    static class FinhubQuoteResponse {
        private double c; // Current price
        
        // No-args constructor required for deserialization
        public FinhubQuoteResponse() {
        }
        
        public double getCurrentPrice() {
            return c;
        }
        
        public void setC(double c) {
            this.c = c;
        }
    }
}
