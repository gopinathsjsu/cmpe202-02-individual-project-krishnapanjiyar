package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import java.io.IOException;
import java.util.List;

/**
 * Interface for parsing credit card records from files.
 * Different file formats (CSV, JSON, XML) implement this interface.
 */
public interface FileParser {
    
    /**
     * Parses a file and extracts card records.
     * @param filePath The path to the file to parse
     * @return A list of CardRecord objects
     * @throws IOException If there's an error reading the file
     */
    List<CardRecord> parse(String filePath) throws IOException;
}
