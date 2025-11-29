package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * JSON file parser for credit card records
 */
public class JSONParser implements FileParser {
    
    @Override
    public List<CardRecord> parse(String filePath) throws IOException {
        List<CardRecord> records = new ArrayList<>();
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            
            Gson gson = new Gson();
            CardRecord[] cardArray = gson.fromJson(content, CardRecord[].class);
            
            if (cardArray != null) {
                records.addAll(Arrays.asList(cardArray));
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            throw new IOException("Invalid JSON format", e);
        }
        
        return records;
    }
}
