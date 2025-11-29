package com.creditcard.factory;

import com.creditcard.model.CreditCard;

/**
 * Wraps the result of creating a credit card.
 * Contains either a successfully created card or an error message.
 */
public class CreditCardResult {
    private boolean success;
    private CreditCard card;
    private String errorMessage;

    /**
     * Private constructor - use static factory methods instead.
     */
    private CreditCardResult() {
    }

    /**
     * Creates a successful result with a card.
     * @param card The successfully created card
     * @return A CreditCardResult indicating success
     */
    public static CreditCardResult success(CreditCard card) {
        CreditCardResult result = new CreditCardResult();
        result.success = true;
        result.card = card;
        result.errorMessage = null;
        return result;
    }

    /**
     * Creates a failed result with an error message.
     * @param errorMessage The error message
     * @return A CreditCardResult indicating failure
     */
    public static CreditCardResult error(String errorMessage) {
        CreditCardResult result = new CreditCardResult();
        result.success = false;
        result.card = null;
        result.errorMessage = errorMessage;
        return result;
    }

    /**
     * Checks if the card creation was successful.
     * @return True if successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the created card.
     * @return The card, or null if creation failed
     */
    public CreditCard getCard() {
        return card;
    }

    /**
     * Gets the error message.
     * @return The error message, or null if successful
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
