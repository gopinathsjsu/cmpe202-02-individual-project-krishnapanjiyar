package com.creditcard.validation;

/**
 * Validation strategy for MasterCard cards.
 * 
 * MasterCard cards:
 * - First digit: 5
 * - Second digit: 1-5 (so range is 51-55)
 * - Length: 16 digits
 */
public class MasterCardValidationStrategy implements CardValidationStrategy {

    @Override
    public ValidationResult validate(String cardNumber) {
        // Rule 1: First digit must be 5
        if (!cardNumber.startsWith("5")) {
            return new ValidationResult(false, "Invalid: not a MasterCard");
        }

        // Rule 2: Second digit must be 1-5
        if (cardNumber.length() < 2) {
            return new ValidationResult(false, "Invalid: not a MasterCard");
        }
        char secondDigit = cardNumber.charAt(1);
        if (secondDigit < '1' || secondDigit > '5') {
            return new ValidationResult(false, "Invalid: not a MasterCard");
        }

        // Rule 3: Length must be 16
        if (cardNumber.length() != 16) {
            return new ValidationResult(false, "Invalid: not a MasterCard");
        }

        return new ValidationResult(true);
    }

    @Override
    public String getCardType() {
        return "MasterCard";
    }
}
