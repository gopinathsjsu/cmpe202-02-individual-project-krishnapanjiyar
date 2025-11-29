package com.creditcard.processor;

import com.creditcard.factory.CreditCardFactory;
import com.creditcard.factory.CreditCardResult;
import com.creditcard.factory.FileFormatFactory;
import com.creditcard.model.CardRecord;
import com.creditcard.parser.FileParser;
import com.creditcard.writer.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main orchestrator for the credit card validation system.
 * Coordinates reading input files, validating cards, and writing results.
 */
public class CreditCardProcessor {
    
    /**
     * Processes a credit card input file and writes validation results.
     * Automatically detects input/output format from file extensions.
     *
     * @param inputPath The path to the input file (CSV, JSON, or XML)
     * @param outputPath The path to the output file (same format as input)
     * @throws IOException If there's an error reading or writing files
     * @throws IllegalArgumentException If unsupported file format
     */
    public void processFile(String inputPath, String outputPath) throws IOException {
        // Step 1: Get the appropriate parser based on file extension
        FileParser parser = FileFormatFactory.getParser(inputPath);
        
        // Step 2: Parse the input file
        List<CardRecord> records = parser.parse(inputPath);
        
        // Step 3: Process each record through the validation factory
        List<CardValidationResult> results = new ArrayList<>();
        
        for (CardRecord record : records) {
            // Validate the card using the CreditCardFactory
            CreditCardResult cardResult = CreditCardFactory.createCard(
                record.getCardNumber(),
                record.getExpirationDate(),
                record.getCardHolderName()
            );
            
            // Convert to validation result for output
            CardValidationResult validationResult = new CardValidationResult();
            validationResult.setCardNumber(record.getCardNumber());
            
            if (cardResult.isSuccess()) {
                validationResult.setCardType(cardResult.getCard().getCardType());
                validationResult.setValid(true);
            } else {
                validationResult.setCardType(cardResult.getErrorMessage());
                validationResult.setValid(false);
            }
            
            results.add(validationResult);
        }
        
        // Step 4: Get the appropriate writer (same format as output file extension)
        FileWriter writer = FileFormatFactory.getWriter(outputPath);
        
        // Step 5: Write the results
        writer.write(results, outputPath);
    }
}
