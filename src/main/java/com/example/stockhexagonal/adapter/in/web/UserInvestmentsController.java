package com.example.stockhexagonal.adapter.in.web;

import com.example.stockhexagonal.application.InvestmentDto;
import com.example.stockhexagonal.application.UserInvestmentsDto;
import com.example.stockhexagonal.application.port.in.GetUserInvestmentsUseCase;
import com.example.stockhexagonal.application.port.in.UserNotFoundException;

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
 * REST controller for user investment queries
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Investments API", description = "API for retrieving user investment information")
public class UserInvestmentsController {

    private final GetUserInvestmentsUseCase getUserInvestmentsUseCase;

    public UserInvestmentsController(GetUserInvestmentsUseCase getUserInvestmentsUseCase) {
        this.getUserInvestmentsUseCase = getUserInvestmentsUseCase;
    }

    @GetMapping("/{id}/investments")
    @Operation(summary = "Get user's investments with current prices")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User investments found",
                    content = @Content(schema = @Schema(implementation = UserInvestmentsResponse.class))),
        @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid user ID provided",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> getUserInvestments(
            @Parameter(description = "User ID")
            @PathVariable String id) {
        
        try {
            UserInvestmentsDto investments = getUserInvestmentsUseCase.getUserInvestments(id);
            
            UserInvestmentsResponse response = new UserInvestmentsResponse(
                investments.getUserId(),
                investments.getUserName(),
                investments.getInvestments().stream()
                    .map(dto -> new InvestmentResponse(
                        dto.getSymbol(),
                        dto.getPrice(),
                        dto.getCurrency(),
                        dto.getFetchedAt()))
                    .toList()
            );
            
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ErrorResponse(404, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(400, e.getMessage()));
        }
    }
}