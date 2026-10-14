package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    public static final int MAX_LOANS = 3;

    private final String id;
    private final String name;
    private final List<Loan> loans = new ArrayList<>();   // this member's current loans

    public Member(String id, String name) {
        this.id = Validation.requireText(id, "Member id");
        this.name = Validation.requireText(name, "Member name");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Callers get a read-only view, so they cannot change our list behind our back
    public List<Loan> getLoans() {
        return Collections.unmodifiableList(loans);
    }

    // Throws if this member already has the maximum number of loans
    void checkCanBorrow() throws MemberLimitExceededException {
        if (loans.size() >= MAX_LOANS) {
            throw new MemberLimitExceededException(
                    name + " already has " + MAX_LOANS + " loans (the maximum).");
        }
    }

    void addLoan(Loan loan) {
        loans.add(loan);
    }

    void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public String toString() {
        return "Member " + id + ": " + name + " (" + loans.size() + "/" + MAX_LOANS + " loans)";
    }
}
