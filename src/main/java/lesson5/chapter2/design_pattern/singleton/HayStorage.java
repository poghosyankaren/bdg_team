package lesson5.chapter2.design_pattern.singleton;

public class HayStorage {
    private int quantity = 0;

    private HayStorage() {
    }

    private static final HayStorage INSTANCE = new HayStorage();

    public static HayStorage getInstance() {
        return INSTANCE;
    }

    public synchronized void addHay(int amount) {
        quantity += amount;
    }

    public synchronized boolean removeHay(int amount) {
        if (quantity < amount) return false;
        quantity -= amount;
        return true;
    }

    public synchronized int getHayQuantity() {
        return quantity;
    }
}
