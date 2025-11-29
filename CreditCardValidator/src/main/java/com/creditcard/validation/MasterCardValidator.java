package com.creditcard.validation;

import com.creditcard.model.CardValidationResult;

/**
 * MasterCard validation strategy.
 * Rules: First digit = 5, Second digit = 1-5, Length = 16
 */
public class MasterCardValidator implements CardValidator {
    
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

        if (cardNumber.charAt(0) != '5') {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        char secondDigit = cardNumber.charAt(1);
        if (secondDigit < '1' || secondDigit > '5') {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        if (cardNumber.length() != 16) {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        return new CardValidationResult(cardNumber, "MasterCard", true);
    }
}
