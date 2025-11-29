package com.creditcard.model;

/**
 * Represents a MasterCard credit card.
 * Extends the abstract CreditCard class.
 */
public class MasterCardCC extends CreditCard {
    
    /**
     * Gets the card type.
     * @return "MasterCard"
     */
    @Override
    public String getCardType() {
        return "MasterCard";
    }
}
