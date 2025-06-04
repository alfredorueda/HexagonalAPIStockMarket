package com.example.stockhexagonal.adapter.in.web;

import com.example.stockhexagonal.application.port.in.GetStockPriceUseCase;
import com.example.stockhexagonal.application.port.out.StockNotFoundException;
import com.example.stockhexagonal.model.StockPrice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for stock price queries (primary adapter)
 */
@RestController
@RequestMapping("/api/stocks")
@Tag(name = "Stock Price API", description = "API for retrieving stock price information")
public class StockPriceController {

    private final GetStockPriceUseCase getStockPriceUseCase;

    public StockPriceController(GetStockPriceUseCase getStockPriceUseCase) {
        this.getStockPriceUseCase = getStockPriceUseCase;
    }

    @GetMapping("/{symbol}")
    @Operation(summary = "Get current stock price by symbol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock price found",
                    content = @Content(schema = @Schema(implementation = StockPriceResponse.class))),
        @ApiResponse(responseCode = "404", description = "Stock not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid symbol provided",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> getStockPrice(
            @Parameter(description = "Stock symbol (e.g., AAPL, MSFT)")
            @PathVariable String symbol) {
        
        try {
            StockPrice stockPrice = getStockPriceUseCase.getStockPrice(symbol);
            return ResponseEntity.ok(new StockPriceResponse(
                stockPrice.getSymbol(),
                stockPrice.getCurrentPrice(),
                stockPrice.getTimestamp()
            ));
        } catch (StockNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, e.getMessage()));
        }
    }
}
