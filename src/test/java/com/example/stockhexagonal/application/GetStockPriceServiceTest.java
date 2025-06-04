package com.example.stockhexagonal.application;

import com.example.stockhexagonal.adapter.out.mock.MockStockPriceAdapter;
import com.example.stockhexagonal.application.port.in.GetStockPriceUseCase;
import com.example.stockhexagonal.application.port.out.StockNotFoundException;
import com.example.stockhexagonal.application.port.out.StockPriceProviderPort;
import com.example.stockhexagonal.model.StockPrice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for GetStockPriceUseCase implementation using MockStockPriceAdapter
 */
class GetStockPriceServiceTest {

    private GetStockPriceUseCase getStockPriceUseCase;
    private StockPriceProviderPort stockPriceProviderPort;

    @BeforeEach
    void setUp() {
        stockPriceProviderPort = new MockStockPriceAdapter();
        getStockPriceUseCase = new GetStockPriceService(stockPriceProviderPort);
    }

    @Test
    void shouldReturnStockPriceForValidSymbol() {
        // Given a valid stock symbol
        String symbol = "AAPL";
        
        // When getStockPrice is called
        StockPrice stockPrice = getStockPriceUseCase.getStockPrice(symbol);
        
        // Then it should return a valid StockPrice object
        assertNotNull(stockPrice);
        assertEquals(symbol, stockPrice.getSymbol());
        assertTrue(stockPrice.getCurrentPrice() > 0);
        assertNotNull(stockPrice.getTimestamp());
    }

    @Test
    void shouldThrowExceptionForInvalidSymbol() {
        // Given an invalid stock symbol
        String symbol = "INVALID";
        
        // When getStockPrice is called
        // Then it should throw StockNotFoundException
        assertThrows(StockNotFoundException.class, () -> {
            getStockPriceUseCase.getStockPrice(symbol);
        });
    }

    @Test
    void shouldThrowExceptionForEmptySymbol() {
        // Given an empty stock symbol
        String symbol = "";
        
        // When getStockPrice is called
        // Then it should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            getStockPriceUseCase.getStockPrice(symbol);
        });
    }

    @Test
    void shouldThrowExceptionForNullSymbol() {
        // Given a null stock symbol
        String symbol = null;
        
        // When getStockPrice is called
        // Then it should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            getStockPriceUseCase.getStockPrice(symbol);
        });
    }
}