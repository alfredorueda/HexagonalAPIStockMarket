package com.example.stockhexagonal.config;

import com.example.stockhexagonal.application.GetStockPriceService;
import com.example.stockhexagonal.application.GetUserInvestmentsService;
import com.example.stockhexagonal.application.port.in.GetStockPriceUseCase;
import com.example.stockhexagonal.application.port.in.GetUserInvestmentsUseCase;
import com.example.stockhexagonal.application.port.out.LoadUserPort;
import com.example.stockhexagonal.application.port.out.StockPriceProviderPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for application services and use cases
 */
@Configuration
public class ApplicationConfig {

    /**
     * Configures the GetUserInvestmentsUseCase bean
     * 
     * @param loadUserPort The port to load user data
     * @param stockPriceProviderPort The port to fetch stock prices
     * @return A configured GetUserInvestmentsUseCase instance
     */
    @Bean
    public GetUserInvestmentsUseCase getUserInvestmentsUseCase(
            LoadUserPort loadUserPort,
            StockPriceProviderPort stockPriceProviderPort) {
        
        return new GetUserInvestmentsService(loadUserPort, stockPriceProviderPort);
    }
    
    /**
     * Configures the GetStockPriceUseCase bean
     * 
     * @param stockPriceProviderPort The port to fetch stock prices
     * @return A configured GetStockPriceUseCase instance
     */
    @Bean
    public GetStockPriceUseCase getStockPriceUseCase(
            StockPriceProviderPort stockPriceProviderPort) {
        
        return new GetStockPriceService(stockPriceProviderPort);
    }
}