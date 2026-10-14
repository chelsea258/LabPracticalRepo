package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// Saves the library to three CSV-style text files (fields separated by "|") and loads them back.
//   items.csv    ->  BOOK|id|title|author   or   DVD|id|title|minutes
//   members.csv  ->  id|name
//   loans.csv    ->  itemId|memberId|borrowDate
public class LibraryFileStore {

    private final Path folder;
    private final Path itemsFile;
    private final Path membersFile;
    private final Path loansFile;

    public LibraryFileStore(String folderName) {
        this.folder = Path.of(folderName);
        this.itemsFile = folder.resolve("items.csv");
        this.membersFile = folder.resolve("members.csv");
        this.loansFile = folder.resolve("loans.csv");
    }

    public void save(Library library) throws IOException {
        Files.createDirectories(folder);

        List<String> itemLines = new ArrayList<>();
        for (LibraryItem item : library.getItems()) {
            itemLines.add(item.toCsv());          // polymorphism: Book and Dvd write themselves
        }
        Files.write(itemsFile, itemLines);

        List<String> memberLines = new ArrayList<>();
        for (Member member : library.getMembers()) {
            memberLines.add(member.getId() + "|" + member.getName());
        }
        Files.write(membersFile, memberLines);

        List<String> loanLines = new ArrayList<>();
        for (Loan loan : library.getActiveLoans()) {
            loanLines.add(loan.getItem().getId() + "|" + loan.getMember().getId() + "|" + loan.getBorrowDate());
        }
        Files.write(loansFile, loanLines);
    }

    // Fills an EMPTY library from the files. On the very first run the files do not exist yet.
    public void load(Library library) throws IOException {
        // Items first, then members, then loans (a loan needs both to exist already)
        if (Files.exists(itemsFile)) {
            for (String line : Files.readAllLines(itemsFile)) {
                if (line.isBlank()) continue;
                try {
                    library.addItem(parseItem(line));
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    throw new IOException("Bad line in items.csv: " + line, e);
                }
            }
        }

        if (Files.exists(membersFile)) {
            for (String line : Files.readAllLines(membersFile)) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                try {
                    library.registerMember(new Member(parts[0], parts[1]));
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    throw new IOException("Bad line in members.csv: " + line, e);
                }
            }
        }

        if (Files.exists(loansFile)) {
            for (String line : Files.readAllLines(loansFile)) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                try {
                    LocalDate date = LocalDate.parse(parts[2]);
                    library.borrow(parts[1], parts[0], date);    // rebuilds the loan AND marks the item unavailable
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException
                         | ItemNotAvailableException | MemberLimitExceededException e) {
                    throw new IOException("Bad line in loans.csv: " + line, e);
                }
            }
        }
    }

    private LibraryItem parseItem(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0];
        if (type.equals("BOOK")) {
            return new Book(parts[1], parts[2], parts[3]);
        }
        if (type.equals("DVD")) {
            return new Dvd(parts[1], parts[2], Integer.parseInt(parts[3]));
        }
        throw new IllegalArgumentException("Unknown item type: " + type);
    }
}
