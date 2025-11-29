## Project Structure

## Project Files

### Main Source Code

```
project/
├── pom.xml                              # Maven configuration
├── README.md                            # This file
├── src/
│   ├── main/java/com/creditcard/
│   │   ├── CreditCardValidator.java
│   │   ├── model/
│   │   │   ├── CardRecord.java
│   │   │   └── CardValidationResult.java
│   │   ├── validation/
│   │   │   ├── CardValidator.java
│   │   │   ├── VisaValidator.java
│   │   │   ├── MasterCardValidator.java
│   │   │   ├── AmericanExpressValidator.java
│   │   │   └── DiscoverValidator.java
│   │   ├── processor/
│   │   │   └── CardProcessor.java
│   │   ├── parser/
│   │   │   ├── FileParser.java
│   │   │   ├── CSVParser.java
│   │   │   ├── JSONParser.java
│   │   │   └── XMLParser.java
│   │   ├── writer/
│   │   │   ├── FileWriter.java
│   │   │   ├── CSVWriter.java
│   │   │   ├── JSONWriter.java
│   │   │   └── XMLWriter.java
│   │   └── factory/
│   │       ├── ParserFactory.java
│   │       └── WriterFactory.java
│   └── test/java/com/creditcard/
│       ├── CreditCardValidatorIntegrationTest.java
│       ├── parser/FileParserTest.java
│       ├── processor/CardProcessorTest.java
│       └── validation/CardValidatorTest.java
└── nf/                                  # Test files
    ├── input_file-1.csv
    ├── input_file.json
    ├── input_file.xml
    ├── output_file.csv
    ├── output_file.json
    └── output_file.xml
```

## Design Patterns Used

### 1. Strategy Pattern (Validation Logic)

Each credit card type implements the `CardValidator` interface with its own validation rules:

- VisaValidator: First digit = 4, Length = 13 or 16
- MasterCardValidator: First digit = 5, Second digit = 1-5, Length = 16
- AmericanExpressValidator: First digit = 3, Second digit = 4 or 7, Length = 15
- DiscoverValidator: First 4 digits = 6011, Length = 16

### 2. Factory Pattern (Object Creation)

- `CardProcessor`: Selects appropriate validator based on card number
- `ParserFactory`: Selects appropriate parser based on file extension
- `WriterFactory`: Selects appropriate writer based on file extension

### 3. Template Method Pattern (File Processing)

All parsers and writers implement common interfaces (`FileParser`, `FileWriter`)

**Note**: The application uses GSON and other libraries that need Maven. For full functionality, ensure Maven can access repositories.

## Building the Application

### Using Maven (Recommended - requires internet)

# Quick Start

### 1. Build the Project

```bash
cd CreditCardValidator
mvn clean install
mvn clean package
```

### 2. Run Application on Test Files

### Command Line

```bash
java -cp target/classes com.creditcard.CreditCardValidator <input_file> <output_file>

mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/inputFilename outputFilename"
cat filename
```

**Process CSV:**

```bash
mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/input_file.csv output.csv"
cat output.csv

mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/input_file-1.csv output-1.csv"
cat output.csv
```

**Process JSON:**

```bash
mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/input_file.json output.json"
cat output.json

```

**Process XML:**

```bash
mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/input_file.xml output.xml"
cat output.xml
```

### 3. Run All Tests

```bash
mvn test
```

### 4. ReBuild

```bash
mvn clean install
```

## Testing Output

After rebuilding, test with each format:

```bash
# CSV (already working)
mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/input_file.csv output.csv"
cat output.csv

# XML (already working)
mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/input_file.xml output.xml"
cat output.xml

# JSON (now fixed)
mvn exec:java -Dexec.mainClass="com.creditcard.CreditCardValidator" \
              -Dexec.args="nf/input_file.json output.json"
cat output.json
jq . output.json  # Validate JSON
```

**Results:**

- CardValidatorTest: 18 tests
- CardProcessorTest: 16 tests
- FileParserTest: 13 tests
- CreditCardValidatorIntegrationTest: 16 tests
- **Total: 63 tests** ✓
