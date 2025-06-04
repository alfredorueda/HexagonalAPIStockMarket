package com.example.stockhexagonal.application;

import com.example.stockhexagonal.adapter.out.memory.InMemoryUserRepository;
import com.example.stockhexagonal.adapter.out.mock.MockStockPriceAdapter;
import com.example.stockhexagonal.application.port.in.GetUserInvestmentsUseCase;
import com.example.stockhexagonal.application.port.in.UserNotFoundException;
import com.example.stockhexagonal.application.port.out.LoadUserPort;
import com.example.stockhexagonal.application.port.out.StockPriceProviderPort;
import com.example.stockhexagonal.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for GetUserInvestmentsUseCase implementation
 */
class GetUserInvestmentsServiceTest {

    private GetUserInvestmentsUseCase getUserInvestmentsUseCase;
    private LoadUserPort loadUserPort;
    private StockPriceProviderPort stockPriceProviderPort;

    @BeforeEach
    void setUp() {
        // Create the in-memory repository with a test user
        loadUserPort = new InMemoryUserRepository();
        stockPriceProviderPort = new MockStockPriceAdapter();
        getUserInvestmentsUseCase = new GetUserInvestmentsService(loadUserPort, stockPriceProviderPort);
    }

    @Test
    void shouldReturnUserInvestmentsForValidUserId() {
        // Given a valid user ID (from pre-populated in-memory repository)
        String userId = "1";
        
        // When getUserInvestments is called
        UserInvestmentsDto result = getUserInvestmentsUseCase.getUserInvestments(userId);
        
        // Then it should return user investments
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("John Doe", result.getUserName());
        
        // Should have investments for AAPL, MSFT, GOOGL
        assertFalse(result.getInvestments().isEmpty());
        assertEquals(3, result.getInvestments().size());
        
        // Verify that all expected symbols are present
        List<String> symbols = result.getInvestments().stream()
                .map(InvestmentDto::getSymbol)
                .toList();
        
        assertTrue(symbols.contains("AAPL"));
        assertTrue(symbols.contains("MSFT"));
        assertTrue(symbols.contains("GOOGL"));
    }

    @Test
    void shouldThrowExceptionForInvalidUserId() {
        // Given an invalid user ID
        String userId = "999";
        
        // When getUserInvestments is called
        // Then it should throw UserNotFoundException
        assertThrows(UserNotFoundException.class, () -> {
            getUserInvestmentsUseCase.getUserInvestments(userId);
        });
    }

    @Test
    void shouldThrowExceptionForEmptyUserId() {
        // Given an empty user ID
        String userId = "";
        
        // When getUserInvestments is called
        // Then it should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            getUserInvestmentsUseCase.getUserInvestments(userId);
        });
    }

    @Test
    void shouldThrowExceptionForNullUserId() {
        // Given a null user ID
        String userId = null;
        
        // When getUserInvestments is called
        // Then it should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            getUserInvestmentsUseCase.getUserInvestments(userId);
        });
    }
}