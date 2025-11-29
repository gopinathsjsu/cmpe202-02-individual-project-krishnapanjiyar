package com.creditcard.factory;

import com.creditcard.model.*;
import com.creditcard.validation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating and validating credit cards.
 * Uses the Strategy pattern for validation and Factory pattern for creation.
 */
public class CreditCardFactory {
    // Map of card types to their validation strategies
    private static final Map<String, CardValidationStrategy> strategies = new HashMap<>();

    static {
        // Initialize strategies
        strategies.put("Visa", new VisaValidationStrategy());
        strategies.put("MasterCard", new MasterCardValidationStrategy());
        strategies.put("AmEx", new AmExValidationStrategy());
        strategies.put("Discover", new DiscoverValidationStrategy());
    }

    /**
     * Creates a credit card from the provided information.
     * Performs validation and creates the appropriate card type.
     *
     * @param cardNumber The card number
     * @param expirationDate The expiration date
     * @param cardHolderName The cardholder name
     * @return A CreditCardResult with the card or error message
     */
    public static CreditCardResult createCard(String cardNumber, 
                                             String expirationDate, 
                                             String cardHolderName) {
        
        // Step 1: Basic validation
        ValidationResult basicValidation = validateBasic(cardNumber);
        if (!basicValidation.isValid()) {
            return CreditCardResult.error(basicValidation.getErrorMessage());
        }

        // Step 2: Determine card type
        String cardType = determineCardType(cardNumber);
        if (cardType == null) {
            return CreditCardResult.error("Invalid: not a possible card number");
        }

        // Step 3: Apply strategy-specific validation
        CardValidationStrategy strategy = strategies.get(cardType);
        ValidationResult strategyValidation = strategy.validate(cardNumber);
        if (!strategyValidation.isValid()) {
            return CreditCardResult.error(strategyValidation.getErrorMessage());
        }

        // Step 4: Create the appropriate card instance
        CreditCard card = createCardInstance(cardType, cardNumber, expirationDate, cardHolderName);
        return CreditCardResult.success(card);
    }

    /**
     * Performs basic validation on the card number.
     * Checks for null, empty, non-numeric, and length constraints.
     *
     * @param cardNumber The card number to validate
     * @return A ValidationResult with validation outcome
     */
    private static ValidationResult validateBasic(String cardNumber) {
        // Check 1: Null or empty
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return new ValidationResult(false, "Invalid: empty/null card number");
        }

        // Check 2: Non-numeric characters
        if (!cardNumber.matches("\\d+")) {
            return new ValidationResult(false, "Invalid: non numeric characters");
        }

        // Check 3: Length <= 19 (max for credit cards)
        if (cardNumber.length() > 19) {
            return new ValidationResult(false, "Invalid: more than 19 digits");
        }

        return new ValidationResult(true);
    }

    /**
     * Determines the card type based on the card number.
     * Uses prefix and length rules for each card type.
     *
     * @param cardNumber The card number
     * @return The card type, or null if no match found
     */
    private static String determineCardType(String cardNumber) {
        // Visa: starts with 4
        if (cardNumber.startsWith("4")) {
            return "Visa";
        }

        // MasterCard: starts with 51-55, length 16
        if (cardNumber.startsWith("5") && cardNumber.length() == 16) {
            char secondDigit = cardNumber.charAt(1);
            if (secondDigit >= '1' && secondDigit <= '5') {
                return "MasterCard";
            }
        }

        // AmEx: starts with 34 or 37
        if (cardNumber.startsWith("3")) {
            if (cardNumber.length() >= 2) {
                char secondDigit = cardNumber.charAt(1);
                if (secondDigit == '4' || secondDigit == '7') {
                    return "AmEx";
                }
            }
        }

        // Discover: starts with 6011, length 16
        if (cardNumber.startsWith("6011") && cardNumber.length() == 16) {
            return "Discover";
        }

        return null;
    }

    /**
     * Creates an instance of the appropriate card type.
     *
     * @param cardType The type of card to create
     * @param cardNumber The card number
     * @param expirationDate The expiration date
     * @param cardHolderName The cardholder name
     * @return The created card instance
     */
    private static CreditCard createCardInstance(String cardType,
                                                String cardNumber,
                                                String expirationDate,
                                                String cardHolderName) {
        CreditCard card;

        switch (cardType) {
            case "Visa":
                card = new VisaCard();
                break;
            case "MasterCard":
                card = new MasterCardCC();
                break;
            case "AmEx":
                card = new AmExCard();
                break;
            case "Discover":
                card = new DiscoverCard();
                break;
            default:
                return null;
        }

        // Set the card properties
        card.setCardNumber(cardNumber);
        card.setExpirationDate(expirationDate);
        card.setCardHolderName(cardHolderName);

        return card;
    }
}
