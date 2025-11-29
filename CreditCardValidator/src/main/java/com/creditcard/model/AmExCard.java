package com.creditcard.model;

/**
 * Represents an American Express credit card.
 * Extends the abstract CreditCard class.
 */
public class AmExCard extends CreditCard {
    
    /**
     * Gets the card type.
     * @return "AmericanExpress"
     */
    @Override
    public String getCardType() {
        return "AmericanExpress";
    }
}
