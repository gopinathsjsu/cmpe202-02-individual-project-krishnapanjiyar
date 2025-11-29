package com.creditcard.factory;

import com.creditcard.model.CreditCard;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Unit tests for CreditCardFactory.
 * Tests card creation and validation for all card types and error cases.
 */
public class CreditCardFactoryTest {
    
    // Valid Visa test cases
    @Test
    public void testValidVisa13Digits() {
        CreditCardResult result = CreditCardFactory.createCard(
            "4123456789123", "04/26", "Martha Clark"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertNotNull("Card should not be null", result.getCard());
        assertEquals("Card type should be Visa", "Visa", result.getCard().getCardType());
    }
    
    @Test
    public void testValidVisa16Digits() {
        CreditCardResult result = CreditCardFactory.createCard(
            "4123456789123999", "03/23", "Jane Dayton"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Card type should be Visa", "Visa", result.getCard().getCardType());
    }
    
    // Valid MasterCard test cases
    @Test
    public void testValidMasterCard() {
        CreditCardResult result = CreditCardFactory.createCard(
            "5567894523129089", "08/26", "John Doe"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Card type should be MasterCard", "MasterCard", result.getCard().getCardType());
    }
    
    @Test
    public void testValidMasterCardSecondDigit1() {
        CreditCardResult result = CreditCardFactory.createCard(
            "5167894523129089", "08/25", "Mark William"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Card type should be MasterCard", "MasterCard", result.getCard().getCardType());
    }
    
    @Test
    public void testValidMasterCardSecondDigit5() {
        CreditCardResult result = CreditCardFactory.createCard(
            "5567894523129089", "08/26", "John Doe"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Card type should be MasterCard", "MasterCard", result.getCard().getCardType());
    }
    
    // Valid American Express test cases
    @Test
    public void testValidAmExStartsWith34() {
        CreditCardResult result = CreditCardFactory.createCard(
            "347856341908126", "03/23", "Jane S. Dayton"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Card type should be AmericanExpress", "AmericanExpress", result.getCard().getCardType());
    }
    
    @Test
    public void testValidAmExStartsWith37() {
        CreditCardResult result = CreditCardFactory.createCard(
            "377856341908126", "03/25", "Martha Clark"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Card type should be AmericanExpress", "AmericanExpress", result.getCard().getCardType());
    }
    
    // Valid Discover test cases
    @Test
    public void testValidDiscover() {
        CreditCardResult result = CreditCardFactory.createCard(
            "6011111100007756", "02/24", "John Doe"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Card type should be Discover", "Discover", result.getCard().getCardType());
    }
    
    // Error cases - NULL/EMPTY
    @Test
    public void testInvalidNullCardNumber() {
        CreditCardResult result = CreditCardFactory.createCard(
            null, "08/26", "John Doe"
        );
        assertFalse("Card should be invalid", result.isSuccess());
        assertTrue("Error should mention null/empty",
            result.getErrorMessage().contains("empty/null"));
    }
    
    @Test
    public void testInvalidEmptyCardNumber() {
        CreditCardResult result = CreditCardFactory.createCard(
            "", "08/26", "John Doe"
        );
        assertFalse("Card should be invalid", result.isSuccess());
        assertEquals("Error message should match",
            "Invalid: empty/null card number", result.getErrorMessage());
    }
    
    // Error cases - NON-NUMERIC
    @Test
    public void testInvalidNonNumericCharacters() {
        CreditCardResult result = CreditCardFactory.createCard(
            "6011*890HJrt6789", "05/25", "Jill Davis"
        );
        assertFalse("Card should be invalid", result.isSuccess());
        assertEquals("Error message should match",
            "Invalid: non numeric characters", result.getErrorMessage());
    }
    
    // Error cases - TOO LONG
    @Test
    public void testInvalidTooLong() {
        CreditCardResult result = CreditCardFactory.createCard(
            "59012345678901234567890", "10/24", "Lisa Claire"
        );
        assertFalse("Card should be invalid", result.isSuccess());
        assertEquals("Error message should match",
            "Invalid: more than 19 digits", result.getErrorMessage());
    }
    
    // Error cases - INVALID PATTERN
    @Test
    public void testInvalidNotPossibleCardNumber() {
        CreditCardResult result = CreditCardFactory.createCard(
            "3601112345678789", "06/24", "Lara Wayne"
        );
        assertFalse("Card should be invalid", result.isSuccess());
        assertEquals("Error message should match",
            "Invalid: not a possible card number", result.getErrorMessage());
    }
    
    // Test cardholder information is preserved
    @Test
    public void testCardholderNamePreserved() {
        String name = "John Doe";
        CreditCardResult result = CreditCardFactory.createCard(
            "5567894523129089", "08/26", name
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Cardholder name should be preserved", name, result.getCard().getCardHolderName());
    }
    
    // Test expiration date is preserved
    @Test
    public void testExpirationDatePreserved() {
        String expiryDate = "08/26";
        CreditCardResult result = CreditCardFactory.createCard(
            "5567894523129089", expiryDate, "John Doe"
        );
        assertTrue("Card should be valid", result.isSuccess());
        assertEquals("Expiration date should be preserved", expiryDate, result.getCard().getExpirationDate());
    }
}
