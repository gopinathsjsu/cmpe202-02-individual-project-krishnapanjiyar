package com.creditcard.validation;

import com.creditcard.model.CardValidationResult;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * JUnit tests for credit card validators
 */
public class CardValidatorTest {
    
    private VisaValidator visaValidator;
    private MasterCardValidator masterCardValidator;
    private AmericanExpressValidator amexValidator;
    private DiscoverValidator discoverValidator;
    
    @Before
    public void setUp() {
        visaValidator = new VisaValidator();
        masterCardValidator = new MasterCardValidator();
        amexValidator = new AmericanExpressValidator();
        discoverValidator = new DiscoverValidator();
    }
    
    // ===== VISA TESTS =====
    @Test
    public void testVisaValid13Digits() {
        CardValidationResult result = visaValidator.validate("4123456789123");
        assertTrue("Valid Visa card with 13 digits", result.isValid());
        assertEquals("Visa", result.getCardType());
    }
    
    @Test
    public void testVisaValid16Digits() {
        CardValidationResult result = visaValidator.validate("4123456789123999");
        assertTrue("Valid Visa card with 16 digits", result.isValid());
        assertEquals("Visa", result.getCardType());
    }
    
    @Test
    public void testVisaInvalidWrongFirstDigit() {
        CardValidationResult result = visaValidator.validate("5123456789123");
        assertFalse("Invalid Visa with wrong first digit", result.isValid());
        assertEquals("Invalid: Not a possible card number", result.getCardType());
    }
    
    @Test
    public void testVisaInvalidLength15() {
        CardValidationResult result = visaValidator.validate("412345678912399");
        assertFalse("Invalid Visa with 15 digits", result.isValid());
    }
    
    @Test
    public void testVisaEmptyCardNumber() {
        CardValidationResult result = visaValidator.validate("");
        assertFalse("Empty Visa card number", result.isValid());
        assertEquals("Invalid: empty/null card number", result.getCardType());
    }
    
    @Test
    public void testVisaNullCardNumber() {
        CardValidationResult result = visaValidator.validate(null);
        assertFalse("Null Visa card number", result.isValid());
        assertEquals("Invalid: empty/null card number", result.getCardType());
    }
    
    @Test
    public void testVisaNonNumericCharacters() {
        CardValidationResult result = visaValidator.validate("412345*789123");
        assertFalse("Visa with non-numeric characters", result.isValid());
        assertEquals("Invalid: non numeric characters", result.getCardType());
    }
    
    // ===== MASTERCARD TESTS =====
    @Test
    public void testMasterCardValid() {
        CardValidationResult result = masterCardValidator.validate("5567894523129089");
        assertTrue("Valid MasterCard", result.isValid());
        assertEquals("MasterCard", result.getCardType());
    }
    
    @Test
    public void testMasterCardValidSecondDigit5() {
        CardValidationResult result = masterCardValidator.validate("5567894523129089");
        assertTrue("Valid MasterCard with second digit 5", result.isValid());
    }
    
    @Test
    public void testMasterCardInvalidSecondDigit() {
        CardValidationResult result = masterCardValidator.validate("5667894523129089");
        assertFalse("Invalid MasterCard with second digit 6", result.isValid());
        assertEquals("Invalid: Not a possible card number", result.getCardType());
    }
    
    @Test
    public void testMasterCardInvalidLength() {
        CardValidationResult result = masterCardValidator.validate("556789452312908");
        assertFalse("Invalid MasterCard with 15 digits", result.isValid());
        assertEquals("Invalid: Not a possible card number", result.getCardType());
    }
    
    @Test
    public void testMasterCardTooLong() {
        CardValidationResult result = masterCardValidator.validate("59012345678901234567890");
        assertFalse("MasterCard with more than 19 digits", result.isValid());
        assertEquals("Invalid: more than 19 digits", result.getCardType());
    }
    
    // ===== AMERICAN EXPRESS TESTS =====
    @Test
    public void testAmexValidSecondDigit4() {
        CardValidationResult result = amexValidator.validate("347856341908126");
        assertTrue("Valid AmEx with second digit 4", result.isValid());
        assertEquals("AmericanExpress", result.getCardType());
    }
    
    @Test
    public void testAmexValidSecondDigit7() {
        CardValidationResult result = amexValidator.validate("377856341908126");
        assertTrue("Valid AmEx with second digit 7", result.isValid());
        assertEquals("AmericanExpress", result.getCardType());
    }
    
    @Test
    public void testAmexInvalidSecondDigit() {
        CardValidationResult result = amexValidator.validate("358856341908126");
        assertFalse("Invalid AmEx with second digit 5", result.isValid());
        assertEquals("Invalid: Not a possible card number", result.getCardType());
    }
    
    @Test
    public void testAmexInvalidLength() {
        CardValidationResult result = amexValidator.validate("34785634190812");
        assertFalse("Invalid AmEx with 14 digits", result.isValid());
    }
    
    // ===== DISCOVER TESTS =====
    @Test
    public void testDiscoverValid() {
        CardValidationResult result = discoverValidator.validate("6011111100007756");
        assertTrue("Valid Discover card", result.isValid());
        assertEquals("Discover", result.getCardType());
    }
    
    @Test
    public void testDiscoverInvalidStart() {
        CardValidationResult result = discoverValidator.validate("6010111100007756");
        assertFalse("Invalid Discover with wrong start", result.isValid());
        assertEquals("Invalid: Not a possible card number", result.getCardType());
    }
    
    @Test
    public void testDiscoverInvalidLength() {
        CardValidationResult result = discoverValidator.validate("601011110000775");
        assertFalse("Invalid Discover with 15 digits", result.isValid());
    }
    
    // ===== EDGE CASES =====
    @Test
    public void testNonNumericCharacters() {
        CardValidationResult result = visaValidator.validate("6011*890HJrt6789");
        assertFalse("Card with non-numeric characters", result.isValid());
        assertEquals("Invalid: non numeric characters", result.getCardType());
    }
    
    @Test
    public void testMoreThan19Digits() {
        CardValidationResult result = visaValidator.validate("59012345678901234567890");
        assertFalse("Card with more than 19 digits", result.isValid());
        assertEquals("Invalid: more than 19 digits", result.getCardType());
    }
}
