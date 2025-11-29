package com.creditcard.validation;

import com.creditcard.model.CardValidationResult;

/**
 * American Express card validation strategy.
 * Rules: First digit = 3, Second digit = 4 or 7, Length = 15
 */
public class AmericanExpressValidator implements CardValidator {
    
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

        if (cardNumber.charAt(0) != '3') {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        char secondDigit = cardNumber.charAt(1);
        if (secondDigit != '4' && secondDigit != '7') {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        if (cardNumber.length() != 15) {
            return new CardValidationResult(cardNumber, "Invalid: Not a possible card number", false);
        }

        return new CardValidationResult(cardNumber, "AmericanExpress", true);
    }
}
