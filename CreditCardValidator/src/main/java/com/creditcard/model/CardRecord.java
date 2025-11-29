package com.creditcard.model;

/**
 * Represents a credit card record read from input file.
 * Contains card number, expiration date, and cardholder name.
 */
public class CardRecord {
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;

    /**
     * Default constructor.
     */
    public CardRecord() {
    }

    /**
     * Constructor with all fields.
     * @param cardNumber The card number
     * @param expirationDate The expiration date
     * @param cardHolderName The cardholder name
     */
    public CardRecord(String cardNumber, String expirationDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
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
