package com.creditcard.validation;

import com.creditcard.model.CardValidationResult;

/**
 * Visa card validation strategy.
 * Rules: First digit = 4, Length = 13 or 16
 */
public class VisaValidator implements CardValidator {
    
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

        if (cardNumber.charAt(0) != '4') {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        int length = cardNumber.length();
        if (length != 13 && length != 16) {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        return new CardValidationResult(cardNumber, "Visa", true);
    }
}
