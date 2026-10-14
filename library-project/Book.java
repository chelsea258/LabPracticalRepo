package library;

public class Book extends LibraryItem {

    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);                                   // runs the LibraryItem constructor first
        this.author = Validation.requireText(author, "Author");
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int loanPeriodDays() {
        return 21;     // books can be kept for 3 weeks
    }

    @Override
    public String toCsv() {
        return "BOOK|" + getId() + "|" + getTitle() + "|" + author;
    }

    @Override
    public String toString() {
        return "[" + getId() + "] Book: " + getTitle() + " by " + author + " (" + statusText() + ")";
    }
}
