package com.creditcard.writer;

import com.creditcard.model.CardValidationResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;

/**
 * JSON file writer for card validation results
 */
public class JSONWriter implements FileWriter {
    
    @Override
    public void write(String filePath, List<CardValidationResult> results) throws IOException {
        // Convert results to a format suitable for JSON output
        List<Map<String, String>> jsonData = new ArrayList<>();
        
        for (CardValidationResult result : results) {
            Map<String, String> entry = new LinkedHashMap<>();
            entry.put("cardNumber", result.getCardNumber() != null ? result.getCardNumber() : "");
            entry.put("cardType", result.getCardType());
            jsonData.add(entry);
        }
        
        // Create Gson with proper settings for valid JSON
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();
        String jsonString = gson.toJson(jsonData);
        
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(filePath))) {
            writer.print(jsonString);
        }
    }
}
