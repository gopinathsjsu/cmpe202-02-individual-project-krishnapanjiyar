package com.creditcard.factory;

import com.creditcard.parser.*;
import com.creditcard.writer.*;

/**
 * Factory for detecting file format and providing appropriate parsers and writers.
 * Supports CSV, JSON, and XML formats.
 */
public class FileFormatFactory {
    
    /**
     * Gets the appropriate parser based on file extension.
     * @param filePath The path to the file
     * @return A FileParser for the detected format
     * @throws IllegalArgumentException If format is not supported
     */
    public static FileParser getParser(String filePath) throws IllegalArgumentException {
        String extension = getFileExtension(filePath).toLowerCase();
        
        switch (extension) {
            case "csv":
                return new CSVParser();
            case "json":
                return new JSONParser();
            case "xml":
                return new XMLParser();
            default:
                throw new IllegalArgumentException("Unsupported format: " + extension);
        }
    }
    
    /**
     * Gets the appropriate writer based on file extension.
     * @param filePath The path to the file
     * @return A FileWriter for the detected format
     * @throws IllegalArgumentException If format is not supported
     */
    public static FileWriter getWriter(String filePath) throws IllegalArgumentException {
        String extension = getFileExtension(filePath).toLowerCase();
        
        switch (extension) {
            case "csv":
                return new CSVWriter();
            case "json":
                return new JSONWriter();
            case "xml":
                return new XMLWriter();
            default:
                throw new IllegalArgumentException("Unsupported format: " + extension);
        }
    }
    
    /**
     * Extracts the file extension from a file path.
     * @param filePath The file path
     * @return The file extension without the dot
     */
    private static String getFileExtension(String filePath) {
        int lastDot = filePath.lastIndexOf('.');
        if (lastDot > 0 && lastDot < filePath.length() - 1) {
            return filePath.substring(lastDot + 1);
        }
        return "";
    }
}
