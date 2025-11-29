package com.creditcard.validation;

/**
 * Interface for credit card validation strategies.
 * Different card types (Visa, MasterCard, etc.) implement this interface
 * to provide their specific validation rules.
 */
public interface CardValidationStrategy {
    
    /**
     * Validates a credit card number according to the strategy's rules.
     * @param cardNumber The card number to validate
     * @return A ValidationResult containing the validation outcome
     */
    ValidationResult validate(String cardNumber);
    
    /**
     * Gets the card type this strategy validates.
     * @return The card type (e.g., "Visa", "MasterCard")
     */
    String getCardType();
}
