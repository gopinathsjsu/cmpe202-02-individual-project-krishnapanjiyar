package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;

/**
 * JUnit tests for file parsers
 */
public class FileParserTest {
    
    private CSVParser csvParser;
    private JSONParser jsonParser;
    private XMLParser xmlParser;
    
    @Before
    public void setUp() {
        csvParser = new CSVParser();
        jsonParser = new JSONParser();
        xmlParser = new XMLParser();
    }
    
    // ===== CSV PARSER TESTS =====
    @Test
    public void testCSVParserValid() throws IOException {
        List<CardRecord> records = csvParser.parse("nf/input_file.csv");
        assertNotNull("CSV records should not be null", records);
        assertTrue("CSV should have records", records.size() > 0);
    }
    
    @Test
    public void testCSVParserRecordCount() throws IOException {
        List<CardRecord> records = csvParser.parse("nf/input_file.csv");
        // Should have 12 valid records (header + 12 data rows)
        assertTrue("CSV should parse multiple records", records.size() >= 10);
    }
    
    @Test
    public void testCSVParserFirstRecord() throws IOException {
        List<CardRecord> records = csvParser.parse("nf/input_file.csv");
        assertNotNull("First record should exist", records.get(0));
        assertEquals("First card number should match", "5567894523129089", records.get(0).getCardNumber());
        assertEquals("First expiration should match", "08/26", records.get(0).getExpirationDate());
        assertEquals("First holder name should match", "John Doe", records.get(0).getCardHolderName());
    }
    
    // ===== JSON PARSER TESTS =====
    @Test
    public void testJSONParserValid() throws IOException {
        List<CardRecord> records = jsonParser.parse("nf/input_file.json");
        assertNotNull("JSON records should not be null", records);
        assertTrue("JSON should have records", records.size() > 0);
    }
    
    @Test
    public void testJSONParserRecordCount() throws IOException {
        List<CardRecord> records = jsonParser.parse("nf/input_file.json");
        // Should have 12 records
        assertEquals("JSON should have 12 records", 12, records.size());
    }
    
    @Test
    public void testJSONParserFirstRecord() throws IOException {
        List<CardRecord> records = jsonParser.parse("nf/input_file.json");
        assertNotNull("First record should exist", records.get(0));
        assertEquals("First card number should match", "5567894523129089", records.get(0).getCardNumber());
    }
    
    // ===== XML PARSER TESTS =====
    @Test
    public void testXMLParserValid() throws IOException {
        List<CardRecord> records = xmlParser.parse("nf/input_file.xml");
        assertNotNull("XML records should not be null", records);
        assertTrue("XML should have records", records.size() > 0);
    }
    
    @Test
    public void testXMLParserRecordCount() throws IOException {
        List<CardRecord> records = xmlParser.parse("nf/input_file.xml");
        // Should have 12 records
        assertEquals("XML should have 12 records", 12, records.size());
    }
    
    @Test
    public void testXMLParserFirstRecord() throws IOException {
        List<CardRecord> records = xmlParser.parse("nf/input_file.xml");
        assertNotNull("First record should exist", records.get(0));
        assertEquals("First card number should match", "5567894523129089", records.get(0).getCardNumber());
        assertEquals("First expiration should match", "08/26", records.get(0).getExpirationDate());
    }
    
    @Test
    public void testXMLParserEmptyCardNumber() throws IOException {
        List<CardRecord> records = xmlParser.parse("nf/input_file.xml");
        // Last record should have empty card number
        CardRecord lastRecord = records.get(records.size() - 1);
        assertEquals("Last card should have empty card number", "", lastRecord.getCardNumber());
    }
}
