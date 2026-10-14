package library;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// The core "service" class: it holds the data and the rules.
public class Library {

    // Maps keyed by id (LinkedHashMap is a Map that also remembers the order we added things)
    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> activeLoans = new ArrayList<>();

    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("An item with id '" + item.getId() + "' already exists.");
        }
        items.put(item.getId(), item);
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getId())) {
            throw new IllegalArgumentException("A member with id '" + member.getId() + "' already exists.");
        }
        members.put(member.getId(), member);
    }

    // Borrow with a chosen date (also used when reloading saved loans from the file)
    public Loan borrow(String memberId, String itemId, LocalDate date)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item
        Member member = members.get(memberId);
        if (member == null) {
            throw new IllegalArgumentException("No member with id '" + memberId + "'.");
        }
        LibraryItem item = items.get(itemId);
        if (item == null) {
            throw new IllegalArgumentException("No item with id '" + itemId + "'.");
        }

        // 2. check availability and the member's loan limit (the item does both checks)
        item.borrow(member);

        // 3. create the loan and record it
        Loan loan = new Loan(item, member, date);
        activeLoans.add(loan);
        member.addLoan(loan);
        return loan;
    }

    // Borrow today
    public Loan borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        return borrow(memberId, itemId, LocalDate.now());
    }

    // Return an item. Returns the Loan that has just ended.
    public Loan returnItem(String itemId) {
        Loan found = null;
        for (Loan loan : activeLoans) {
            if (loan.getItem().getId().equals(itemId)) {
                found = loan;
            }
        }
        if (found == null) {
            throw new IllegalArgumentException("Item '" + itemId + "' is not currently on loan.");
        }
        found.getItem().giveBack();
        found.getMember().removeLoan(found);
        activeLoans.remove(found);
        return found;
    }

    // Streams: keep only the loans whose due date has passed
    public List<Loan> listOverdue(LocalDate today) {
        return activeLoans.stream()
                .filter(loan -> loan.isOverdue(today))
                .toList();
    }

    // Streams: case-insensitive "contains" search, sorted by title
    public List<LibraryItem> searchByTitle(String text) {
        String wanted = text.toLowerCase();
        return items.values().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(wanted))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    // Read-only views for the menu and the file store
    public Collection<LibraryItem> getItems() {
        return Collections.unmodifiableCollection(items.values());
    }

    public Collection<Member> getMembers() {
        return Collections.unmodifiableCollection(members.values());
    }

    public List<Loan> getActiveLoans() {
        return Collections.unmodifiableList(activeLoans);
    }
}
