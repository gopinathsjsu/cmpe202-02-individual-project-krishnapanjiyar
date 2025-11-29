package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import java.io.*;
import java.util.*;

/**
 * CSV file parser for credit card records
 */
public class CSVParser implements FileParser {
    
    @Override
    public List<CardRecord> parse(String filePath) throws IOException {
        List<CardRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header row
                }
                
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split(",", -1); // -1 to keep empty strings
                
                if (parts.length >= 3) {
                    String cardNumber = parts[0].trim();
                    String expirationDate = parts[1].trim();
                    String cardHolderName = parts[2].trim();
                    
                    CardRecord record = new CardRecord(cardNumber, expirationDate, cardHolderName);
                    records.add(record);
                }
            }
        }
        
        return records;
    }
}
