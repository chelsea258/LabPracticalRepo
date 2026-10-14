package lab9;

import java.util.HashSet;
import java.util.Set;

public class BookSetDemo {
    public static void main(String[] args) {
        // --- With equals AND hashCode ---
        Book b1 = new Book("978-0132350884", "Clean Code");
        Book b2 = new Book("978-0132350884", "Clean Code (2nd copy)");   // same ISBN
        Book b3 = new Book("978-0134685991", "Effective Java");

        Set<Book> books = new HashSet<>();
        System.out.println("add b1: " + books.add(b1));
        System.out.println("add b2: " + books.add(b2) + "  (duplicate ISBN, so the set refuses it)");
        System.out.println("add b3: " + books.add(b3));
        System.out.println("Set size: " + books.size());
        System.out.println("b1.equals(b2): " + b1.equals(b2));
        System.out.println("same hash codes: " + (b1.hashCode() == b2.hashCode()));

        // --- With equals but NO hashCode: the contract is broken ---
        Set<BookNoHash> broken = new HashSet<>();
        broken.add(new BookNoHash("111", "A"));
        broken.add(new BookNoHash("111", "A again"));
        System.out.println("Broken set size: " + broken.size() + "  (should be 1, but equal books got different hash codes)");
    }
}
