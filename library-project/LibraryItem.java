package library;

// Abstract parent of Book and Dvd. You cannot write "new LibraryItem(...)".
public abstract class LibraryItem implements Borrowable {

    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        this.id = Validation.requireText(id, "Item id");
        this.title = Validation.requireText(title, "Title");
    }

    // Each subclass decides its own loan period (this is where polymorphism happens)
    public abstract int loanPeriodDays();

    // Each subclass knows how to write itself as one line of the CSV file
    public abstract String toCsv();

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    // Used by subclasses in their toString()
    protected String statusText() {
        if (available) {
            return "available";
        }
        return "on loan";
    }

    @Override
    public void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException {
        if (!available) {
            throw new ItemNotAvailableException("'" + title + "' is already on loan.");
        }
        member.checkCanBorrow();     // throws MemberLimitExceededException if the member is full
        available = false;           // only changed once BOTH checks have passed
    }

    @Override
    public void giveBack() {
        available = true;
    }
}
