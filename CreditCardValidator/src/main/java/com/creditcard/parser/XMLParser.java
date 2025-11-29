package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;

/**
 * XML file parser for credit card records
 */
public class XMLParser implements FileParser {
    
    @Override
    public List<CardRecord> parse(String filePath) throws IOException {
        List<CardRecord> records = new ArrayList<>();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            
            NodeList cardNodes = document.getElementsByTagName("CARD");
            
            for (int i = 0; i < cardNodes.getLength(); i++) {
                Element cardElement = (Element) cardNodes.item(i);
                
                String cardNumber = getElementTextContent(cardElement, "CARD_NUMBER");
                String expirationDate = getElementTextContent(cardElement, "EXPIRATION_DATE");
                String cardHolderName = getElementTextContent(cardElement, "CARD_HOLDER_NAME");
                
                CardRecord record = new CardRecord(cardNumber, expirationDate, cardHolderName);
                records.add(record);
            }
        } catch (Exception e) {
            throw new IOException("Error parsing XML file: " + e.getMessage(), e);
        }
        
        return records;
    }
    
    private String getElementTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}
