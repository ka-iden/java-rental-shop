package io.github.it_is_final.java_rental_shop;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BookLoader {

    public Map<String, Book> loadBooks(InputStream xmlFile) {
        Map<String, Book> books = new HashMap<>();

        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Get all <book> elements
            NodeList bookNodes = document.getElementsByTagName("book");

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Node bookNode = bookNodes.item(i);

                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;

                    // Extract book details
                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                    double price = Double.parseDouble(bookElement.getElementsByTagName("price").item(0).getTextContent());

                    // Create a Book object and add it to the map
                    Book book = new Book(title, author, price);
                    books.put(title.toLowerCase(), book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load books from XML: " + e.getMessage());
        }

        return books;
    }
}
