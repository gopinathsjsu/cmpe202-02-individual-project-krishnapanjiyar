package com.creditcard.factory;

import com.creditcard.parser.*;

/**
 * Factory for creating appropriate file parser based on file extension
 */
public class ParserFactory {
    
    public static FileParser getParser(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        
        String lowerPath = filePath.toLowerCase();
        
        if (lowerPath.endsWith(".csv")) {
            return new CSVParser();
        } else if (lowerPath.endsWith(".json")) {
            return new JSONParser();
        } else if (lowerPath.endsWith(".xml")) {
            return new XMLParser();
        } else {
            throw new IllegalArgumentException("Unsupported file format. Supported formats: CSV, JSON, XML");
        }
    }
}
