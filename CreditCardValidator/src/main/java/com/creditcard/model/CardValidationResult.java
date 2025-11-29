package com.creditcard.model;

public class CardValidationResult {
    private String cardNumber;
    private String cardType;
    private boolean isValid;

    public CardValidationResult(String cardNumber, String cardType, boolean isValid) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.isValid = isValid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "CardValidationResult{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
