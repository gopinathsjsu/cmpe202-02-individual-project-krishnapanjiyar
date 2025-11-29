package com.creditcard.validation;

/**
 * Validation strategy for Visa cards.
 * 
 * Visa cards:
 * - First digit: 4
 * - Length: 13 or 16 digits
 */
public class VisaValidationStrategy implements CardValidationStrategy {

    @Override
    public ValidationResult validate(String cardNumber) {
        // Rule 1: First digit must be 4
        if (!cardNumber.startsWith("4")) {
            return new ValidationResult(false, "Invalid: not a Visa card");
        }

        // Rule 2: Length must be 13 or 16
        int length = cardNumber.length();
        if (length != 13 && length != 16) {
            return new ValidationResult(false, "Invalid: not a Visa card");
        }

        return new ValidationResult(true);
    }

    @Override
    public String getCardType() {
        return "Visa";
    }
}
