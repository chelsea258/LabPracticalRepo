package lab9;

import java.util.Objects;

// Lab 9 - Exercise 3: two books are the SAME book when they have the same ISBN
public class Book {

    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {                   // must take Object, not Book!
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book other)) {               // null or a different type
            return false;
        }
        return isbn.equals(other.isbn);                 // equality by ISBN only
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);                      // uses the SAME field as equals
    }

    @Override
    public String toString() {
        return title + " (ISBN " + isbn + ")";
    }
}
