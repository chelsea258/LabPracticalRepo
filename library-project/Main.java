package library;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

// The text menu. Run with:  javac *.java   then   java Main
public class Main {

    private static final String DATA_FOLDER = "library-data";

    private static final Scanner keyboard = new Scanner(System.in);
    private static final Library library = new Library();
    private static final LibraryFileStore store = new LibraryFileStore(DATA_FOLDER);

    public static void main(String[] args) {
        // Reload what was saved last time
        try {
            store.load(library);
            System.out.println("Loaded " + library.getItems().size() + " items, "
                    + library.getMembers().size() + " members, "
                    + library.getActiveLoans().size() + " active loans.");
        } catch (IOException e) {
            // Stop here, otherwise saving on exit would overwrite the files with half-loaded data
            System.out.println("Could not load saved data: " + e.getMessage());
            System.out.println("Fix or delete the '" + DATA_FOLDER + "' folder and start again.");
            return;
        }

        boolean running = true;
        while (running) {
            printMenu();
            String choice = ask("Choose an option: ");

            if (choice.equals("1")) {
                addItem();
            } else if (choice.equals("2")) {
                registerMember();
            } else if (choice.equals("3")) {
                borrowItem();
            } else if (choice.equals("4")) {
                returnItem();
            } else if (choice.equals("5")) {
                listOverdue();
            } else if (choice.equals("6")) {
                searchByTitle();
            } else if (choice.equals("7")) {
                listItems();
            } else if (choice.equals("8")) {
                listMembers();
            } else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println("Please enter a number from the menu.");
            }
        }

        // Save on exit
        try {
            store.save(library);
            System.out.println("Data saved to the '" + DATA_FOLDER + "' folder. Goodbye!");
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Library Management System =====");
        System.out.println("1. Add item");
        System.out.println("2. Register member");
        System.out.println("3. Borrow");
        System.out.println("4. Return");
        System.out.println("5. List overdue items");
        System.out.println("6. Search by title");
        System.out.println("7. List all items");
        System.out.println("8. List all members");
        System.out.println("0. Save and exit");
    }

    // Prints a question and returns the trimmed answer
    private static String ask(String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine().trim();
    }

    private static void addItem() {
        String type = ask("Book or DVD? (B/D): ").toUpperCase();
        try {
            String id = ask("Item id: ");
            String title = ask("Title: ");
            if (type.equals("B")) {
                String author = ask("Author: ");
                library.addItem(new Book(id, title, author));
            } else if (type.equals("D")) {
                int minutes = Integer.parseInt(ask("Running time in minutes: "));
                library.addItem(new Dvd(id, title, minutes));
            } else {
                System.out.println("Please type B or D.");
                return;
            }
            System.out.println("Item added.");
        } catch (NumberFormatException e) {
            System.out.println("That was not a whole number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add item: " + e.getMessage());
        }
    }

    private static void registerMember() {
        try {
            String id = ask("Member id: ");
            String name = ask("Name: ");
            library.registerMember(new Member(id, name));
            System.out.println("Member registered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register member: " + e.getMessage());
        }
    }

    private static void borrowItem() {
        String memberId = ask("Member id: ");
        String itemId = ask("Item id: ");
        String dateText = ask("Borrow date (yyyy-mm-dd, or press Enter for today): ");
        try {
            LocalDate date = LocalDate.now();
            if (!dateText.isEmpty()) {
                date = LocalDate.parse(dateText);
            }
            Loan loan = library.borrow(memberId, itemId, date);
            System.out.println("Borrowed! Due back on " + loan.getDueDate() + ".");
        } catch (ItemNotAvailableException | MemberLimitExceededException e) {
            System.out.println("Cannot borrow: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot borrow: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("That date is not in the format yyyy-mm-dd.");
        }
    }

    private static void returnItem() {
        String itemId = ask("Item id to return: ");
        try {
            Loan loan = library.returnItem(itemId);
            System.out.println("Returned '" + loan.getItem().getTitle() + "'.");
            if (loan.isOverdue(LocalDate.now())) {
                System.out.println("Note: it was returned after its due date (" + loan.getDueDate() + ").");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot return: " + e.getMessage());
        }
    }

    private static void listOverdue() {
        List<Loan> overdue = library.listOverdue(LocalDate.now());
        if (overdue.isEmpty()) {
            System.out.println("No overdue items.");
        } else {
            System.out.println("Overdue items:");
            for (Loan loan : overdue) {
                System.out.println("  " + loan);
            }
        }
    }

    private static void searchByTitle() {
        String text = ask("Search for: ");
        List<LibraryItem> results = library.searchByTitle(text);
        if (results.isEmpty()) {
            System.out.println("No items match '" + text + "'.");
        } else {
            for (LibraryItem item : results) {
                System.out.println("  " + item);
            }
        }
    }

    private static void listItems() {
        if (library.getItems().isEmpty()) {
            System.out.println("No items yet.");
        }
        for (LibraryItem item : library.getItems()) {
            System.out.println("  " + item);
        }
    }

    private static void listMembers() {
        if (library.getMembers().isEmpty()) {
            System.out.println("No members yet.");
        }
        for (Member member : library.getMembers()) {
            System.out.println("  " + member);
        }
    }
}
