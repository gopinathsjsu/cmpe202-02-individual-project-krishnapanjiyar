package com.creditcard.parser;

import com.creditcard.model.CardRecord;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses credit card records from XML files.
 */
public class XMLParser implements FileParser {
    
    @Override
    public List<CardRecord> parse(String filePath) throws IOException {
        List<CardRecord> records = new ArrayList<>();
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            
            // Get all CARD elements
            NodeList cardNodes = doc.getElementsByTagName("CARD");
            
            for (int i = 0; i < cardNodes.getLength(); i++) {
                Element cardElement = (Element) cardNodes.item(i);
                
                CardRecord record = new CardRecord();
                
                // Extract CARD_NUMBER
                NodeList cardNumberNodes = cardElement.getElementsByTagName("CARD_NUMBER");
                if (cardNumberNodes.getLength() > 0) {
                    record.setCardNumber(cardNumberNodes.item(0).getTextContent());
                }
                
                // Extract EXPIRATION_DATE
                NodeList expirationNodes = cardElement.getElementsByTagName("EXPIRATION_DATE");
                if (expirationNodes.getLength() > 0) {
                    record.setExpirationDate(expirationNodes.item(0).getTextContent());
                }
                
                // Extract CARD_HOLDER_NAME
                NodeList nameNodes = cardElement.getElementsByTagName("CARD_HOLDER_NAME");
                if (nameNodes.getLength() > 0) {
                    record.setCardHolderName(nameNodes.item(0).getTextContent());
                }
                
                records.add(record);
            }
        } catch (SAXException | ParserConfigurationException e) {
            System.err.println("Error parsing XML file: " + e.getMessage());
            throw new IOException("Failed to parse XML file", e);
        }
        
        return records;
    }
}
