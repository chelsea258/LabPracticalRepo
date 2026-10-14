package library;

public class Dvd extends LibraryItem {

    private final int minutes;

    public Dvd(String id, String title, int minutes) {
        super(id, title);
        if (minutes <= 0) {
            throw new IllegalArgumentException("Running time must be more than 0 minutes.");
        }
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public int loanPeriodDays() {
        return 7;      // DVDs only for 1 week
    }

    @Override
    public String toCsv() {
        return "DVD|" + getId() + "|" + getTitle() + "|" + minutes;
    }

    @Override
    public String toString() {
        return "[" + getId() + "] DVD: " + getTitle() + ", " + minutes + " min (" + statusText() + ")";
    }
}
