package com.creditcard;

import com.creditcard.factory.ParserFactory;
import com.creditcard.factory.WriterFactory;
import com.creditcard.model.CardRecord;
import com.creditcard.model.CardValidationResult;
import com.creditcard.parser.FileParser;
import com.creditcard.processor.CardProcessor;
import com.creditcard.writer.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application for credit card validation
 * Part 1 & Part 2: Reads input file, validates cards, and writes output file
 */
public class CreditCardValidator {
    
    private CardProcessor cardProcessor;
    
    public CreditCardValidator() {
        this.cardProcessor = new CardProcessor();
    }
    
    /**
     * Process credit card file from input and write results to output
     * @param inputFileName path to input file (CSV, JSON, or XML)
     * @param outputFileName path to output file (same format as input)
     * @throws IOException if file operations fail
     */
    public void processFile(String inputFileName, String outputFileName) throws IOException {
        // Validate input parameters
        if (inputFileName == null || inputFileName.isEmpty()) {
            throw new IllegalArgumentException("Input file name cannot be null or empty");
        }
        if (outputFileName == null || outputFileName.isEmpty()) {
            throw new IllegalArgumentException("Output file name cannot be null or empty");
        }
        
        System.out.println("Processing input file: " + inputFileName);
        
        // Parse input file
        FileParser parser = ParserFactory.getParser(inputFileName);
        List<CardRecord> cardRecords = parser.parse(inputFileName);
        System.out.println("Parsed " + cardRecords.size() + " card records");
        
        // Process each card record
        List<CardValidationResult> results = new ArrayList<>();
        for (CardRecord record : cardRecords) {
            CardValidationResult result = cardProcessor.processCard(record.getCardNumber());
            results.add(result);
        }
        
        System.out.println("Validated " + results.size() + " card records");
        
        // Write results to output file
        FileWriter writer = WriterFactory.getWriter(outputFileName);
        writer.write(outputFileName, results);
        System.out.println("Results written to: " + outputFileName);
    }
    
    /**
     * Main entry point
     * Usage: java CreditCardValidator <input_file> <output_file>
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java CreditCardValidator <input_file> <output_file>");
            System.err.println("Supported formats: CSV, JSON, XML");
            System.exit(1);
        }
        
        String inputFile = args[0];
        String outputFile = args[1];
        
        try {
            CreditCardValidator validator = new CreditCardValidator();
            validator.processFile(inputFile, outputFile);
            System.out.println("Processing completed successfully!");
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            System.exit(1);
        }
    }
}
