package library;

// Checked exception: thrown when a member already has the maximum number of loans (3).
public class MemberLimitExceededException extends Exception {
    public MemberLimitExceededException(String message) {
        super(message);
    }
}
