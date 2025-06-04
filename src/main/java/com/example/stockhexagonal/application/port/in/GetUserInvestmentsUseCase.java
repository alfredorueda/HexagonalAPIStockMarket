package com.example.stockhexagonal.application.port.in;

import com.example.stockhexagonal.application.UserInvestmentsDto;

/**
 * Primary port (input) for retrieving a user's investments
 */
public interface GetUserInvestmentsUseCase {
    
    /**
     * Retrieves the investments for a user
     * 
     * @param userId The unique identifier of the user
     * @return UserInvestmentsDto containing the user's information and current investments
     * @throws UserNotFoundException if the user is not found
     */
    UserInvestmentsDto getUserInvestments(String userId);
}