package com.example.stockhexagonal.application;

import com.example.stockhexagonal.application.port.in.GetUserInvestmentsUseCase;
import com.example.stockhexagonal.application.port.in.UserNotFoundException;
import com.example.stockhexagonal.application.port.out.LoadUserPort;
import com.example.stockhexagonal.application.port.out.StockNotFoundException;
import com.example.stockhexagonal.application.port.out.StockPriceProviderPort;
import com.example.stockhexagonal.model.StockPrice;
import com.example.stockhexagonal.model.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the GetUserInvestmentsUseCase that coordinates between
 * loading user data and fetching current stock prices
 */
@Service
public class GetUserInvestmentsService implements GetUserInvestmentsUseCase {

    private final LoadUserPort loadUserPort;
    private final StockPriceProviderPort stockPriceProviderPort;

    public GetUserInvestmentsService(LoadUserPort loadUserPort, StockPriceProviderPort stockPriceProviderPort) {
        this.loadUserPort = loadUserPort;
        this.stockPriceProviderPort = stockPriceProviderPort;
    }

    @Override
    public UserInvestmentsDto getUserInvestments(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }

        // Load the user from the repository
        User user = loadUserPort.loadUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // Fetch current stock prices for all user's company symbols
        List<InvestmentDto> investments = new ArrayList<>();
        
        for (String symbol : user.getCompanySymbols()) {
            try {
                StockPrice stockPrice = stockPriceProviderPort.fetchStockPrice(symbol);
                
                InvestmentDto investment = new InvestmentDto(
                    stockPrice.getSymbol(),
                    stockPrice.getCurrentPrice(),
                    stockPrice.getCurrency(),
                    stockPrice.getTimestamp()
                );
                
                investments.add(investment);
            } catch (StockNotFoundException e) {
                // Log the error but continue processing other symbols
                System.err.println("Could not fetch price for symbol: " + symbol + ". " + e.getMessage());
            }
        }

        // Return the combined user information with investment data
        return new UserInvestmentsDto(
            user.getId(),
            user.getName(),
            investments
        );
    }
}