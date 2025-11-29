package com.creditcard.validation;

/**
 * Represents the result of a card validation operation.
 * Contains a boolean flag indicating validity and an optional error message.
 */
public class ValidationResult {
    private boolean valid;
    private String errorMessage;

    /**
     * Constructor for valid result (no error message).
     * @param valid Whether the validation passed
     */
    public ValidationResult(boolean valid) {
        this.valid = valid;
        this.errorMessage = null;
    }

    /**
     * Constructor with validation result and error message.
     * @param valid Whether the validation passed
     * @param errorMessage The error message if validation failed
     */
    public ValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    /**
     * Checks if the validation passed.
     * @return True if valid, false otherwise
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Gets the error message.
     * @return The error message, or null if no error
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
