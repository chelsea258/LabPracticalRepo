package library;

// Anything that can be borrowed and given back.
public interface Borrowable {

    void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException;

    void giveBack();
}
