package com.creditcard.validation;

/**
 * Validation strategy for American Express cards.
 * 
 * American Express cards:
 * - First digit: 3
 * - Second digit: 4 or 7 (so 34 or 37)
 * - Length: 15 digits
 */
public class AmExValidationStrategy implements CardValidationStrategy {

    @Override
    public ValidationResult validate(String cardNumber) {
        // Rule 1: First digit must be 3
        if (!cardNumber.startsWith("3")) {
            return new ValidationResult(false, "Invalid: not an AmEx card");
        }

        // Rule 2: Second digit must be 4 or 7
        if (cardNumber.length() < 2) {
            return new ValidationResult(false, "Invalid: not an AmEx card");
        }
        char secondDigit = cardNumber.charAt(1);
        if (secondDigit != '4' && secondDigit != '7') {
            return new ValidationResult(false, "Invalid: not an AmEx card");
        }

        // Rule 3: Length must be 15
        if (cardNumber.length() != 15) {
            return new ValidationResult(false, "Invalid: not an AmEx card");
        }

        return new ValidationResult(true);
    }

    @Override
    public String getCardType() {
        return "AmericanExpress";
    }
}
