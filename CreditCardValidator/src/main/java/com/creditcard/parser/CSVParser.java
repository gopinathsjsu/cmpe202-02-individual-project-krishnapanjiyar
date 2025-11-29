package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses credit card records from CSV files.
 */
public class CSVParser implements FileParser {
    
    @Override
    public List<CardRecord> parse(String filePath) throws IOException {
        List<CardRecord> records = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                // Skip the header line
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                // Parse CSV line
                String[] parts = line.split(",");
                
                if (parts.length >= 1) {
                    CardRecord record = new CardRecord();
                    
                    // Field 1: Card Number
                    record.setCardNumber(parts[0].trim());
                    
                    // Field 2: Expiration Date
                    if (parts.length > 1) {
                        record.setExpirationDate(parts[1].trim());
                    }
                    
                    // Field 3: Cardholder Name
                    if (parts.length > 2) {
                        record.setCardHolderName(parts[2].trim());
                    }
                    
                    records.add(record);
                }
            }
        }
        
        return records;
    }
}
