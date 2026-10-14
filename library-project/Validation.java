package library;

// Small helper used by the other classes to check text input.
// The file store uses "|" to separate fields, so "|" is not allowed inside text.
public class Validation {

    // Returns the trimmed text, or throws IllegalArgumentException if it is not acceptable
    public static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        if (value.contains("|")) {
            throw new IllegalArgumentException(fieldName + " cannot contain the '|' character.");
        }
        return value.trim();
    }
}
