package com.creditcard.writer;

import com.creditcard.model.CardValidationResult;
import java.io.IOException;
import java.util.List;

/**
 * Interface for file writing
 */
public interface FileWriter {
    void write(String filePath, List<CardValidationResult> results) throws IOException;
}
