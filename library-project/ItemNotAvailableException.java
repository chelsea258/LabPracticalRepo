package library;

// Checked exception: thrown when someone tries to borrow an item that is already on loan.
public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}
