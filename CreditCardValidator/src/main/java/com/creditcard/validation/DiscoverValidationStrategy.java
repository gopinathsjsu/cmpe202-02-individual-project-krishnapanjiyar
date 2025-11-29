package com.creditcard.validation;

/**
 * Validation strategy for Discover cards.
 * 
 * Discover cards:
 * - First four digits: 6011
 * - Length: 16 digits
 */
public class DiscoverValidationStrategy implements CardValidationStrategy {

    @Override
    public ValidationResult validate(String cardNumber) {
        // Rule 1: First four digits must be 6011
        if (!cardNumber.startsWith("6011")) {
            return new ValidationResult(false, "Invalid: not a Discover card");
        }

        // Rule 2: Length must be 16
        if (cardNumber.length() != 16) {
            return new ValidationResult(false, "Invalid: not a Discover card");
        }

        return new ValidationResult(true);
    }

    @Override
    public String getCardType() {
        return "Discover";
    }
}
