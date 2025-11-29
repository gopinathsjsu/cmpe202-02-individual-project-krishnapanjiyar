package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses credit card records from JSON files.
 */
public class JSONParser implements FileParser {
    
    @Override
    public List<CardRecord> parse(String filePath) throws IOException {
        List<CardRecord> records = new ArrayList<>();
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(filePath));
            
            if (root.isArray()) {
                for (JsonNode node : root) {
                    CardRecord record = new CardRecord();
                    
                    if (node.has("cardNumber")) {
                        record.setCardNumber(node.get("cardNumber").asText());
                    }
                    
                    if (node.has("expirationDate")) {
                        record.setExpirationDate(node.get("expirationDate").asText());
                    }
                    
                    if (node.has("cardHolderName")) {
                        record.setCardHolderName(node.get("cardHolderName").asText());
                    }
                    
                    records.add(record);
                }
            }
        } catch (IOException e) {
            System.err.println("Error parsing JSON file: " + e.getMessage());
            throw e;
        }
        
        return records;
    }
}
