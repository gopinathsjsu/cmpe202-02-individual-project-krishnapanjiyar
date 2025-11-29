package com.creditcard.writer;

import com.creditcard.processor.CardValidationResult;
import java.io.IOException;
import java.util.List;

/**
 * Interface for writing validation results to files.
 * Different file formats (CSV, JSON, XML) implement this interface.
 */
public interface FileWriter {
    
    /**
     * Writes validation results to a file.
     * @param results The list of validation results to write
     * @param filePath The path to the file to write
     * @throws IOException If there's an error writing the file
     */
    void write(List<CardValidationResult> results, String filePath) throws IOException;
}
