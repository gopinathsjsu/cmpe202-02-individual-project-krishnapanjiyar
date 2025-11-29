package com.creditcard.model;

/**
 * Abstract base class representing a credit card.
 * All credit card types (Visa, MasterCard, etc.) extend this class.
 */
public abstract class CreditCard {
    protected String cardNumber;
    protected String expirationDate;
    protected String cardHolderName;

    /**
     * Gets the type of credit card.
     * @return The card type (e.g., "Visa", "MasterCard")
     */
    public abstract String getCardType();

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
     * Gets the expiration date.
     * @return The expiration date
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date.
     * @param expirationDate The expiration date to set
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the cardholder name.
     * @return The cardholder name
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Sets the cardholder name.
     * @param cardHolderName The cardholder name to set
     */
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
