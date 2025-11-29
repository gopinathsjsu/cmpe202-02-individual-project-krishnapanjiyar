package com.creditcard.writer;

import com.creditcard.model.CardValidationResult;
import java.io.*;
import java.util.List;

/**
 * XML file writer for card validation results
 */
public class XMLWriter implements FileWriter {
    
    @Override
    public void write(String filePath, List<CardValidationResult> results) throws IOException {
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(filePath))) {
            // Write XML header
            writer.println("<CARDS>");
            
            // Write each card result
            for (CardValidationResult result : results) {
                writer.println("  <CARD>");
                writer.println("      <CARD_NUMBER>" + escapeXML(result.getCardNumber()) + "</CARD_NUMBER>");
                writer.println("      <CARD_TYPE>" + escapeXML(result.getCardType()) + "</CARD_TYPE>");
                writer.println("  </CARD>");
            }
            
            // Write XML closing tag
            writer.println("</CARDS>");
        }
    }
    
    private String escapeXML(String value) {
        if (value == null) {
            return "";
        }
        
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
