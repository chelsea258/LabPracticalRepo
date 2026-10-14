package lab9;

// Same as Book but WITHOUT hashCode: this is the classic mistake (for comparison only)
public class BookNoHash {

    private final String isbn;
    private final String title;

    public BookNoHash(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookNoHash other)) {
            return false;
        }
        return isbn.equals(other.isbn);
    }

    // hashCode() NOT overridden on purpose
}
