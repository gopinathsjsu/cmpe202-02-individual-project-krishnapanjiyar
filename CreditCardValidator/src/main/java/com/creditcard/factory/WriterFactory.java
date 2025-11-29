package com.creditcard.factory;

import com.creditcard.writer.*;

/**
 * Factory for creating appropriate file writer based on file extension
 */
public class WriterFactory {
    
    public static FileWriter getWriter(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        
        String lowerPath = filePath.toLowerCase();
        
        if (lowerPath.endsWith(".csv")) {
            return new CSVWriter();
        } else if (lowerPath.endsWith(".json")) {
            return new JSONWriter();
        } else if (lowerPath.endsWith(".xml")) {
            return new XMLWriter();
        } else {
            throw new IllegalArgumentException("Unsupported file format. Supported formats: CSV, JSON, XML");
        }
    }
}
