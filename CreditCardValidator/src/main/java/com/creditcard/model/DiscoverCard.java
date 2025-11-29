package com.creditcard.model;

/**
 * Represents a Discover credit card.
 * Extends the abstract CreditCard class.
 */
public class DiscoverCard extends CreditCard {
    
    /**
     * Gets the card type.
     * @return "Discover"
     */
    @Override
    public String getCardType() {
        return "Discover";
    }
}
