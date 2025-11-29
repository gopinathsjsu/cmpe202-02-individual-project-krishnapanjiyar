package com.creditcard.writer;

import com.creditcard.processor.CardValidationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Writes validation results to JSON files.
 */
public class JSONWriter implements com.creditcard.writer.FileWriter {
    
    @Override
    public void write(List<CardValidationResult> results, String filePath) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(filePath), results);
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
            throw e;
        }
    }
}
