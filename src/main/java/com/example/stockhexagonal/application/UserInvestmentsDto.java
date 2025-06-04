package com.example.stockhexagonal.application;

import java.util.Collections;
import java.util.List;

/**
 * Data Transfer Object representing a user's investments
 */
public class UserInvestmentsDto {
    private final String userId;
    private final String userName;
    private final List<InvestmentDto> investments;

    public UserInvestmentsDto(String userId, String userName, List<InvestmentDto> investments) {
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

    public List<InvestmentDto> getInvestments() {
        return Collections.unmodifiableList(investments);
    }
}