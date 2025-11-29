package com.creditcard.model;

import com.google.gson.annotations.SerializedName;

public class CardRecord {
    @SerializedName("cardNumber")
    private String cardNumber;
    
    @SerializedName("expirationDate")
    private String expirationDate;
    
    @SerializedName("cardHolderName")
    private String cardHolderName;

    public CardRecord() {
    }

    public CardRecord(String cardNumber, String expirationDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public String toString() {
        return "CardRecord{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                '}';
    }
}
