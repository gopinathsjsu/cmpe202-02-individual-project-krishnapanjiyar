package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import java.io.*;
import java.util.*;

/**
 * Interface for file parsing
 */
public interface FileParser {
    List<CardRecord> parse(String filePath) throws IOException;
}
