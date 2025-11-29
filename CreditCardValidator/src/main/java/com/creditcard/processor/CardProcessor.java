package com.creditcard.processor;

import com.creditcard.model.CardValidationResult;
import com.creditcard.validation.*;

/**
 * Credit Card Processor using Factory Pattern.
 * Determines card type and applies appropriate validation strategy.
 */
public class CardProcessor {
    
    /**
     * Process a card number and return validation result
     * @param cardNumber the card number to validate
     * @return CardValidationResult with card type and validity
     */
    public CardValidationResult processCard(String cardNumber) {
        // Check for empty/null input
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return new CardValidationResult(cardNumber, "Invalid: empty/null card number", false);
        }

        // Check for non-numeric characters
        if (!cardNumber.matches("\\d+")) {
            return new CardValidationResult(cardNumber, "Invalid: non numeric characters", false);
        }

        // Check for more than 19 digits
        if (cardNumber.length() > 19) {
            return new CardValidationResult(cardNumber, "Invalid: more than 19 digits", false);
        }

        // Determine card type based on first digit(s)
        CardValidator validator = selectValidator(cardNumber);
        
        return validator.validate(cardNumber);
    }

    /**
     * Select the appropriate validator based on card number pattern
     * @param cardNumber the card number
     * @return the appropriate CardValidator
     */
    private CardValidator selectValidator(String cardNumber) {
        if (cardNumber.length() < 1) {
            return new VisaValidator(); // Default fallback
        }

        char firstDigit = cardNumber.charAt(0);
        
        switch (firstDigit) {
            case '3':
                // American Express
                return new AmericanExpressValidator();
            case '4':
                // Visa
                return new VisaValidator();
            case '5':
                // MasterCard
                return new MasterCardValidator();
            case '6':
                // Discover
                return new DiscoverValidator();
            default:
                // Default to Visa for unknown types
                return new VisaValidator();
        }
    }
}
