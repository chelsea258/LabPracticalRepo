package lab9;

import java.util.LinkedHashMap;
import java.util.Map;

// Lab 9 - Exercise 5 (Challenge): an enum with a field, a constructor and a method
public enum Coin {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    private final int cents;

    Coin(int cents) {              // enum constructors are always private
        this.cents = cents;
    }

    public int getCents() {
        return cents;
    }

    // Fewest coins for an amount: always take as many of the BIGGEST coin as possible.
    // (This "greedy" method is correct for these four coins. It is NOT correct for every coin system.)
    public static Map<Coin, Integer> fewestCoins(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        Map<Coin, Integer> result = new LinkedHashMap<>();
        Coin[] all = values();                          // PENNY, NICKEL, DIME, QUARTER
        for (int i = all.length - 1; i >= 0; i--) {     // go from QUARTER down to PENNY
            Coin coin = all[i];
            int howMany = amount / coin.cents;
            if (howMany > 0) {
                result.put(coin, howMany);
                amount = amount - howMany * coin.cents; // what is left to pay
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] amounts = {0, 3, 41, 67, 99};
        for (int amount : amounts) {
            Map<Coin, Integer> coins = fewestCoins(amount);
            int total = 0;
            for (int count : coins.values()) {
                total = total + count;
            }
            System.out.println(amount + " cents -> " + coins + "  (" + total + " coins)");
        }
    }
}
