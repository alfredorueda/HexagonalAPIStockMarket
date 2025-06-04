package com.example.stockhexagonal.adapter.in.web;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collections;
import java.util.List;

/**
 * REST API response class for a user's investments
 */
@Schema(description = "User investments response")
public class UserInvestmentsResponse {
    
    @Schema(description = "User ID", example = "1")
    private final String userId;
    
    @Schema(description = "User's name", example = "John Doe")
    private final String userName;
    
    @Schema(description = "List of user's investments with current prices")
    private final List<InvestmentResponse> investments;

    public UserInvestmentsResponse(String userId, String userName, List<InvestmentResponse> investments) {
        this.userId = userId;
        this.userName = userName;
        this.investments = investments != null ? investments : Collections.emptyList();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<InvestmentResponse> getInvestments() {
        return investments;
    }
}