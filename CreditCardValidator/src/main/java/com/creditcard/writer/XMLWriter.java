package com.creditcard.writer;

import com.creditcard.processor.CardValidationResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Writes validation results to XML files.
 */
public class XMLWriter implements com.creditcard.writer.FileWriter {
    
    @Override
    public void write(List<CardValidationResult> results, String filePath) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            // Create root CARDS element
            Element root = doc.createElement("CARDS");
            doc.appendChild(root);
            
            // Create CARD element for each result
            for (CardValidationResult result : results) {
                Element cardElement = doc.createElement("CARD");
                
                // Add CARD_NUMBER
                Element cardNumberElement = doc.createElement("CARD_NUMBER");
                cardNumberElement.setTextContent(result.getCardNumber() != null ? 
                    result.getCardNumber() : "");
                cardElement.appendChild(cardNumberElement);
                
                // Add CARD_TYPE
                Element cardTypeElement = doc.createElement("CARD_TYPE");
                cardTypeElement.setTextContent(result.getCardType() != null ? 
                    result.getCardType() : "");
                cardElement.appendChild(cardTypeElement);
                
                root.appendChild(cardElement);
            }
            
            // Write to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(filePath));
            transformer.transform(source, streamResult);
            
        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("Error writing XML file: " + e.getMessage());
            throw new IOException("Failed to write XML file", e);
        }
    }
}
