package com.creditcard.processor;

import com.creditcard.model.CardValidationResult;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * JUnit tests for CardProcessor (Factory Pattern)
 */
public class CardProcessorTest {
    
    private CardProcessor cardProcessor;
    
    @Before
    public void setUp() {
        cardProcessor = new CardProcessor();
    }
    
    // ===== VISA CARD TESTS =====
    @Test
    public void testProcessVisaCard13Digits() {
        CardValidationResult result = cardProcessor.processCard("4123456789123");
        assertTrue("Process valid Visa with 13 digits", result.isValid());
        assertEquals("Visa", result.getCardType());
        assertEquals("4123456789123", result.getCardNumber());
    }
    
    @Test
    public void testProcessVisaCard16Digits() {
        CardValidationResult result = cardProcessor.processCard("4123456789123999");
        assertTrue("Process valid Visa with 16 digits", result.isValid());
        assertEquals("Visa", result.getCardType());
    }
    
    // ===== MASTERCARD TESTS =====
    @Test
    public void testProcessMasterCard() {
        CardValidationResult result = cardProcessor.processCard("5567894523129089");
        assertTrue("Process valid MasterCard", result.isValid());
        assertEquals("MasterCard", result.getCardType());
    }
    
    @Test
    public void testProcessMasterCardMultiple() {
        CardValidationResult result1 = cardProcessor.processCard("5167894523129089");
        assertTrue("Process first MasterCard", result1.isValid());
        assertEquals("MasterCard", result1.getCardType());
        
        CardValidationResult result2 = cardProcessor.processCard("5367894523129089");
        assertTrue("Process second MasterCard", result2.isValid());
        assertEquals("MasterCard", result2.getCardType());
    }
    
    // ===== AMERICAN EXPRESS TESTS =====
    @Test
    public void testProcessAmex() {
        CardValidationResult result = cardProcessor.processCard("347856341908126");
        assertTrue("Process valid AmEx", result.isValid());
        assertEquals("AmericanExpress", result.getCardType());
    }
    
    @Test
    public void testProcessAmexSecondDigit7() {
        CardValidationResult result = cardProcessor.processCard("377856341908126");
        assertTrue("Process AmEx with digit 7", result.isValid());
        assertEquals("AmericanExpress", result.getCardType());
    }
    
    // ===== DISCOVER TESTS =====
    @Test
    public void testProcessDiscover() {
        CardValidationResult result = cardProcessor.processCard("6011111100007756");
        assertTrue("Process valid Discover", result.isValid());
        assertEquals("Discover", result.getCardType());
    }
    
    // ===== INVALID CARD TESTS =====
    @Test
    public void testProcessEmptyCardNumber() {
        CardValidationResult result = cardProcessor.processCard("");
        assertFalse("Process empty card number", result.isValid());
        assertEquals("Invalid: empty/null card number", result.getCardType());
    }
    
    @Test
    public void testProcessNullCardNumber() {
        CardValidationResult result = cardProcessor.processCard(null);
        assertFalse("Process null card number", result.isValid());
        assertEquals("Invalid: empty/null card number", result.getCardType());
    }
    
    @Test
    public void testProcessNonNumericCharacters() {
        CardValidationResult result = cardProcessor.processCard("6011*890HJrt6789");
        assertFalse("Process non-numeric card", result.isValid());
        assertEquals("Invalid: non numeric characters", result.getCardType());
    }
    
    @Test
    public void testProcessMoreThan19Digits() {
        CardValidationResult result = cardProcessor.processCard("59012345678901234567890");
        assertFalse("Process card with >19 digits", result.isValid());
        assertEquals("Invalid: more than 19 digits", result.getCardType());
    }
    
    @Test
    public void testProcessNotPossibleCardNumber() {
        CardValidationResult result = cardProcessor.processCard("3601112345678789");
        assertFalse("Process impossible card number", result.isValid());
        assertEquals("Invalid: Not a possible card number", result.getCardType());
    }
    
    // ===== EDGE CASES =====
    @Test
    public void testProcessWhitespaceCardNumber() {
        CardValidationResult result = cardProcessor.processCard("   ");
        assertFalse("Process whitespace card number", result.isValid());
    }
    
    @Test
    public void testProcessCardNumberWithSpaces() {
        // Spaces in middle - should fail non-numeric check
        CardValidationResult result = cardProcessor.processCard("4123 456789123");
        assertFalse("Process card with spaces", result.isValid());
        assertEquals("Invalid: non numeric characters", result.getCardType());
    }
}
