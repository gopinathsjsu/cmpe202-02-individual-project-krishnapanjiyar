package com.creditcard.writer;

import com.creditcard.model.CardValidationResult;
import java.io.*;
import java.util.List;

/**
 * CSV file writer for card validation results
 */
public class CSVWriter implements FileWriter {
    
    @Override
    public void write(String filePath, List<CardValidationResult> results) throws IOException {
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(filePath))) {
            // Write header
            writer.println("cardNumber,cardType");
            
            // Write data rows
            for (CardValidationResult result : results) {
                String cardNum = result.getCardNumber() != null ? result.getCardNumber() : "";
                String cardType = result.getCardType() != null ? result.getCardType() : "";
                
                writer.println(escapeCSV(cardNum) + "," + escapeCSV(cardType));
            }
        }
    }
    
    private String escapeCSV(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        
        // If value contains comma, quote, or newline, wrap in quotes and escape quotes
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        
        return value;
    }
}
