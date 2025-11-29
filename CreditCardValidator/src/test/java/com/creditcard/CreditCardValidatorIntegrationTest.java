package com.creditcard;

import com.creditcard.model.CardValidationResult;
import com.creditcard.processor.CardProcessor;
import com.creditcard.factory.ParserFactory;
import com.creditcard.factory.WriterFactory;
import com.creditcard.model.CardRecord;
import com.creditcard.parser.FileParser;
import com.creditcard.writer.FileWriter;
import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Integration tests for the complete CreditCardValidator application
 */
public class CreditCardValidatorIntegrationTest {
    
    private CardProcessor cardProcessor;
    
    @Before
    public void setUp() {
        cardProcessor = new CardProcessor();
    }
    
    // ===== INTEGRATION TESTS: CSV =====
    @Test
    public void testProcessCSVFile() throws IOException {
        String inputFile = "nf/input_file-1.csv";
        String outputFile = "test_output.csv";
        
        CreditCardValidator validator = new CreditCardValidator();
        validator.processFile(inputFile, outputFile);
        
        // Verify output file was created
        assertTrue("Output file should exist", Files.exists(Paths.get(outputFile)));
        
        // Verify output file has content
        String content = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertTrue("Output should contain header", content.contains("cardNumber"));
        assertTrue("Output should contain card type column", content.contains("cardType"));
        
        // Clean up
        Files.deleteIfExists(Paths.get(outputFile));
    }
    
    // ===== INTEGRATION TESTS: JSON =====
    @Test
    public void testProcessJSONFile() throws IOException {
        String inputFile = "nf/input_file.json";
        String outputFile = "test_output.json";
        
        CreditCardValidator validator = new CreditCardValidator();
        validator.processFile(inputFile, outputFile);
        
        // Verify output file was created
        assertTrue("Output file should exist", Files.exists(Paths.get(outputFile)));
        
        // Verify output file has JSON content
        String content = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertTrue("Output should contain JSON array", content.contains("["));
        assertTrue("Output should contain cardNumber", content.contains("cardNumber"));
        
        // Clean up
        Files.deleteIfExists(Paths.get(outputFile));
    }
    
    // ===== INTEGRATION TESTS: XML =====
    @Test
    public void testProcessXMLFile() throws IOException {
        String inputFile = "nf/input_file.xml";
        String outputFile = "test_output.xml";
        
        CreditCardValidator validator = new CreditCardValidator();
        validator.processFile(inputFile, outputFile);
        
        // Verify output file was created
        assertTrue("Output file should exist", Files.exists(Paths.get(outputFile)));
        
        // Verify output file has XML content
        String content = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertTrue("Output should contain XML tags", content.contains("<CARDS>"));
        assertTrue("Output should contain CARD elements", content.contains("<CARD>"));
        
        // Clean up
        Files.deleteIfExists(Paths.get(outputFile));
    }
    
    // ===== FACTORY TESTS =====
    @Test
    public void testParserFactoryCSV() {
        FileParser parser = ParserFactory.getParser("test.csv");
        assertNotNull("CSV parser should not be null", parser);
        assertTrue("Should return CSVParser", parser.getClass().getSimpleName().equals("CSVParser"));
    }
    
    @Test
    public void testParserFactoryJSON() {
        FileParser parser = ParserFactory.getParser("test.json");
        assertNotNull("JSON parser should not be null", parser);
        assertTrue("Should return JSONParser", parser.getClass().getSimpleName().equals("JSONParser"));
    }
    
    @Test
    public void testParserFactoryXML() {
        FileParser parser = ParserFactory.getParser("test.xml");
        assertNotNull("XML parser should not be null", parser);
        assertTrue("Should return XMLParser", parser.getClass().getSimpleName().equals("XMLParser"));
    }
    
    @Test
    public void testWriterFactoryCSV() {
        FileWriter writer = WriterFactory.getWriter("test.csv");
        assertNotNull("CSV writer should not be null", writer);
        assertTrue("Should return CSVWriter", writer.getClass().getSimpleName().equals("CSVWriter"));
    }
    
    @Test
    public void testWriterFactoryJSON() {
        FileWriter writer = WriterFactory.getWriter("test.json");
        assertNotNull("JSON writer should not be null", writer);
        assertTrue("Should return JSONWriter", writer.getClass().getSimpleName().equals("JSONWriter"));
    }
    
    @Test
    public void testWriterFactoryXML() {
        FileWriter writer = WriterFactory.getWriter("test.xml");
        assertNotNull("XML writer should not be null", writer);
        assertTrue("Should return XMLWriter", writer.getClass().getSimpleName().equals("XMLWriter"));
    }
    
    @Test
    public void testParserFactoryInvalidExtension() {
        try {
            ParserFactory.getParser("test.txt");
            fail("Should throw IllegalArgumentException for unsupported format");
        } catch (IllegalArgumentException e) {
            assertTrue("Should mention unsupported format", e.getMessage().contains("Unsupported"));
        }
    }
    
    @Test
    public void testWriterFactoryInvalidExtension() {
        try {
            WriterFactory.getWriter("test.txt");
            fail("Should throw IllegalArgumentException for unsupported format");
        } catch (IllegalArgumentException e) {
            assertTrue("Should mention unsupported format", e.getMessage().contains("Unsupported"));
        }
    }
    
    // ===== VALIDATION RESULT TESTS =====
    @Test
    public void testValidationResultsContainAllCards() throws IOException {
        FileParser parser = ParserFactory.getParser("nf/input_file.csv");
        List<CardRecord> records = parser.parse("nf/input_file.csv");
        
        List<CardValidationResult> results = new ArrayList<>();
        for (CardRecord record : records) {
            results.add(cardProcessor.processCard(record.getCardNumber()));
        }
        
        assertEquals("Results should match record count", records.size(), results.size());
    }
    
    @Test
    public void testValidAndInvalidCardsProcessed() throws IOException {
        // Process test CSV which contains both valid and invalid cards
        FileParser parser = ParserFactory.getParser("nf/input_file.csv");
        List<CardRecord> records = parser.parse("nf/input_file.csv");
        
        int validCount = 0;
        int invalidCount = 0;
        
        for (CardRecord record : records) {
            CardValidationResult result = cardProcessor.processCard(record.getCardNumber());
            if (result.isValid()) {
                validCount++;
            } else {
                invalidCount++;
            }
        }
        
        assertTrue("Should have some valid cards", validCount > 0);
        assertTrue("Should have some invalid cards", invalidCount > 0);
    }
}
