
// 'final' class prevents inheritance, ensuring subclasses can't override methods or change state
public final class ImmutablePerson {
    // EXPLAIN: 'final' fields ensure values are assigned once during construction and never changed
    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        this.name = name;
        this.age = age;
    }

    // EXPLAIN: Only getters are provided; no setters exist
    public String getName() { return name; }
    public int getAge()  { return age; }

    @Override
    public String toString() {
        return "ImmutablePerson{name='" + name + "', age=" + age + '}';
    }
}
