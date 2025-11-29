package com.creditcard.validation;

import com.creditcard.model.CardValidationResult;

/**
 * Strategy pattern interface for credit card validation.
 * Each card type implements its own validation logic.
 */
public interface CardValidator {
    /**
     * Validate the card number and return validation result
     * @param cardNumber the card number to validate
     * @return CardValidationResult with card type and validity
     */
    CardValidationResult validate(String cardNumber);
}
