# Credit Card Validation System

A Java application for validating credit card numbers and processing them through multiple file formats (CSV, JSON, XML).

## Overview

This project implements a credit card validation system using design patterns:
- **Strategy Pattern** for validating different card types (Visa, MasterCard, American Express, Discover)
- **Factory Pattern** for creating appropriate card objects
- **Adapter Pattern** for handling multiple file formats

## Requirements

- Java 8 or higher
- Maven 3.6+

## Build Instructions

```bash
# Compile the project
mvn clean compile

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=CreditCardFactoryTest
```

## Project Structure

```
CreditCardValidator/
├── src/main/java/com/creditcard/
│   ├── model/              - Credit card domain models
│   ├── validation/         - Card validation strategies
│   ├── factory/            - Factory patterns for cards and formats
│   ├── parser/             - File parsers (CSV, JSON, XML)
│   ├── writer/             - File writers (CSV, JSON, XML)
│   └── processor/          - Main orchestrator
├── src/test/java/          - JUnit test cases
├── pom.xml                 - Maven configuration
└── README.md               - This file
```

## Supported Card Types

- **Visa**: Starts with 4, length 13 or 16 digits
- **MasterCard**: Starts with 51-55, length 16 digits
- **American Express**: Starts with 34 or 37, length 15 digits
- **Discover**: Starts with 6011, length 16 digits

## Supported File Formats

- CSV (Comma-Separated Values)
- JSON (JavaScript Object Notation)
- XML (Extensible Markup Language)

## Usage Example

```java
import com.creditcard.processor.CreditCardProcessor;

public class Main {
    public static void main(String[] args) throws IOException {
        CreditCardProcessor processor = new CreditCardProcessor();
        processor.processFile("input_file.csv", "output_file.csv");
        processor.processFile("input_file.json", "output_file.json");
        processor.processFile("input_file.xml", "output_file.xml");
    }
}
```

## Input File Format

### CSV
```
cardNumber,expirationDate,cardHolderName
5567894523129089,08/26,John Doe
4123456789123,04/26,Martha Clark
```

### JSON
```json
[
  {
    "cardNumber": "5567894523129089",
    "expirationDate": "08/26",
    "cardHolderName": "John Doe"
  }
]
```

### XML
```xml
<CARDS>
  <CARD>
    <CARD_NUMBER>5567894523129089</CARD_NUMBER>
    <EXPIRATION_DATE>08/26</EXPIRATION_DATE>
    <CARD_HOLDER_NAME>John Doe</CARD_HOLDER_NAME>
  </CARD>
</CARDS>
```

## Output File Format

### CSV
```
cardNumber,cardType
5567894523129089,MasterCard
4123456789123,Visa
```

### JSON
```json
[
  {
    "cardNumber": "5567894523129089",
    "cardType": "MasterCard"
  }
]
```

### XML
```xml
<CARDS>
  <CARD>
    <CARD_NUMBER>5567894523129089</CARD_NUMBER>
    <CARD_TYPE>MasterCard</CARD_TYPE>
  </CARD>
</CARDS>
```

## Validation Rules

- Card numbers must be numeric only
- Card numbers cannot be null or empty
- Card numbers must not exceed 19 digits
- Invalid cards will include error message in output

## Error Messages

- "Invalid: empty/null card number" - Null or empty card number
- "Invalid: non numeric characters" - Card contains letters or special characters
- "Invalid: more than 19 digits" - Card number is too long
- "Invalid: not a possible card number" - Card doesn't match any known type

## Dependencies

- Jackson 2.15.2 - JSON processing
- JAXB 2.3.1 - XML processing
- JUnit 4.13.2 - Unit testing

## Class Descriptions

### Model Classes
- **CreditCard** - Abstract base class for all card types
- **VisaCard, MasterCardCC, AmExCard, DiscoverCard** - Concrete card implementations
- **CardRecord** - Input record from file

### Validation Classes
- **CardValidationStrategy** - Strategy interface for validation
- **VisaValidationStrategy, MasterCardValidationStrategy, etc.** - Concrete strategies
- **ValidationResult** - Validation outcome wrapper

### Factory Classes
- **CreditCardFactory** - Creates and validates cards
- **FileFormatFactory** - Detects and routes to appropriate format handlers
- **CreditCardResult** - Card creation result wrapper

### Parser/Writer Classes
- **FileParser** - Interface for file parsing
- **CSVParser, JSONParser, XMLParser** - Format-specific parsers
- **FileWriter** - Interface for file writing
- **CSVWriter, JSONWriter, XMLWriter** - Format-specific writers

### Processor Classes
- **CreditCardProcessor** - Main orchestrator
- **CardValidationResult** - Output result for writing

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CreditCardFactoryTest

# Run with verbose output
mvn test -X

# Skip tests during build
mvn clean install -DskipTests
```

## Design Patterns Used

1. **Strategy Pattern** - Different validation rules for each card type
2. **Factory Pattern** - Creating appropriate card objects based on validation
3. **Adapter Pattern** - Abstracting different file format handling

## Author Notes

This project demonstrates the practical application of design patterns in a real-world scenario. The separation of concerns and use of interfaces makes the code:
- Extensible (easy to add new card types or formats)
- Testable (each component can be tested independently)
- Maintainable (clear responsibilities)
- Flexible (strategies can be swapped without affecting clients)
