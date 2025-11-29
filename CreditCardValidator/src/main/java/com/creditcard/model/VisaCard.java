package com.creditcard.model;

/**
 * Represents a Visa credit card.
 * Extends the abstract CreditCard class.
 */
public class VisaCard extends CreditCard {
    
    /**
     * Gets the card type.
     * @return "Visa"
     */
    @Override
    public String getCardType() {
        return "Visa";
    }
}
