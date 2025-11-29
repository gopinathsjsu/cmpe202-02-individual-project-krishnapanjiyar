package com.creditcard.validation;

import com.creditcard.model.CardValidationResult;

/**
 * Discover card validation strategy.
 * Rules: First 4 digits = 6011, Length = 16
 */
public class DiscoverValidator implements CardValidator {
    
    @Override
    public CardValidationResult validate(String cardNumber) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return new CardValidationResult(cardNumber, "Invalid: empty/null card number", false);
        }

        if (!cardNumber.matches("\\d+")) {
            return new CardValidationResult(cardNumber, "Invalid: non numeric characters", false);
        }

        if (cardNumber.length() > 19) {
            return new CardValidationResult(cardNumber, "Invalid: more than 19 digits", false);
        }

        if (cardNumber.length() < 4 || !cardNumber.substring(0, 4).equals("6011")) {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        if (cardNumber.length() != 16) {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        return new CardValidationResult(cardNumber, "Discover", true);
    }
}
