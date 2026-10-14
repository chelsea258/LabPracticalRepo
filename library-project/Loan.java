package library;

import java.time.LocalDate;

public class Loan {

    private final LibraryItem item;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    public Loan(LibraryItem item, Member member, LocalDate borrowDate) {
        if (item == null || member == null || borrowDate == null) {
            throw new IllegalArgumentException("A loan needs an item, a member and a date.");
        }
        this.item = item;
        this.member = member;
        this.borrowDate = borrowDate;
        // Polymorphism: a Book adds 21 days, a Dvd adds 7 - Loan does not need to know which
        this.dueDate = borrowDate.plusDays(item.loanPeriodDays());
    }

    public LibraryItem getItem() {
        return item;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isOverdue(LocalDate today) {
        return today.isAfter(dueDate);
    }

    @Override
    public String toString() {
        return item.getTitle() + " [" + item.getId() + "] borrowed by " + member.getName()
                + " (" + member.getId() + "), due " + dueDate;
    }
}
