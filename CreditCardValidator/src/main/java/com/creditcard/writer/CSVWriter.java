package com.creditcard.writer;

import com.creditcard.processor.CardValidationResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes validation results to CSV files.
 */
public class CSVWriter implements com.creditcard.writer.FileWriter {
    
    @Override
    public void write(List<CardValidationResult> results, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.append("cardNumber,cardType\n");
            
            // Write each result
            for (CardValidationResult result : results) {
                String cardNumber = result.getCardNumber() != null ? result.getCardNumber() : "";
                String cardType = result.getCardType() != null ? result.getCardType() : "";
                
                writer.append("\"").append(cardNumber).append("\",");
                writer.append("\"").append(cardType).append("\"\n");
            }
            
            writer.flush();
        }
    }
}
