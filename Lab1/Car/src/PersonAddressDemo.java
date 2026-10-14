class Address {
    String city;
    String country;

    Address(String city, String country) {
        this.city = city;
        this.country = country;
    }
}

class Person {
    String name;
    Address address; // EXPLAIN: Person HAS-A reference to an Address object
}

public class PersonAddressDemo {
    public static void main(String[] args) {
        // 1. Valid chained dot operator usage
        Person p1 = new Person();
        p1.name = "Karlil";
        p1.address = new Address("Harare", "Zimbabwe");

        // EXPLAIN: Chained dot expression accesses p1 -> address -> city
        System.out.println(p1.name + " lives in: " + p1.address.city);

        // 2. Demonstrating null reference handling
        Person p2 = new Person();
        p2.name = "Chelsea";
        // p2.address is left as 'null' by default

        System.out.println("\nTesting person with null address:");

        // Safely checking for null to avoid NullPointerException
        if (p2.address != null) {
            System.out.println(p2.name + " lives in: " + p2.address.city);
        } else {
            System.out.println(p2.name + "'s address is null!");
        }

        //  Un-commenting the following line causes runtime NullPointerException
        // System.out.println(p2.address.city);
    }
}