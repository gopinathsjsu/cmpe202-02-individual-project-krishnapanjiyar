package com.creditcard.processor;

/**
 * Represents the validation result of a credit card for output.
 * Contains the card number and the card type (or error message).
 */
public class CardValidationResult {
    private String cardNumber;
    private String cardType;  // Either the card type or error message
    private boolean valid;

    /**
     * Default constructor.
     */
    public CardValidationResult() {
    }

    /**
     * Constructor with all fields.
     * @param cardNumber The card number
     * @param cardType The card type or error message
     * @param valid Whether the card is valid
     */
    public CardValidationResult(String cardNumber, String cardType, boolean valid) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.valid = valid;
    }

    /**
     * Gets the card number.
     * @return The card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the card number.
     * @param cardNumber The card number to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the card type or error message.
     * @return The card type or error message
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the card type or error message.
     * @param cardType The card type or error message to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * Checks if the card is valid.
     * @return True if valid, false otherwise
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the validity flag.
     * @param valid The validity flag to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
