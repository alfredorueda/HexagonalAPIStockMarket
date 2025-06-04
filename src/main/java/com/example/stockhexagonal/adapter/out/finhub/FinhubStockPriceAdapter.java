package com.example.stockhexagonal.adapter.out.finhub;

import com.example.stockhexagonal.core.model.StockPrice;
import com.example.stockhexagonal.core.usecase.StockNotFoundException;
import com.example.stockhexagonal.port.out.StockPricePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;

/**
 * Secondary adapter that implements StockPricePort by calling the Finhub API
 */
@Component
@Profile("finhub")
public class FinhubStockPriceAdapter implements StockPricePort {

    private final WebClient webClient;
    private final String apiKey;

    public FinhubStockPriceAdapter(WebClient.Builder webClientBuilder, 
                                  @Value("${finhub.api.url:https://finnhub.io/api/v1}") String apiUrl,
                                  @Value("${finhub.api.key:demo}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
        this.apiKey = apiKey;
    }

    @Override
    public StockPrice fetchStockPrice(String symbol) throws StockNotFoundException {
        try {
            FinhubQuoteResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/quote")
                    .queryParam("symbol", symbol)
                    .queryParam("token", apiKey)
                    .build())
                .retrieve()
                .bodyToMono(FinhubQuoteResponse.class)
                .block();

            if (response == null || response.getCurrentPrice() == 0) {
                throw new StockNotFoundException(symbol);
            }

            return new StockPrice(
                symbol,
                response.getCurrentPrice(),
                LocalDateTime.now()
            );
        } catch (WebClientResponseException e) {
            if (e.getStatusCode().value() == 404) {
                throw new StockNotFoundException(symbol);
            }
            throw new RuntimeException("Error fetching stock price: " + e.getMessage(), e);
        }
    }
}
