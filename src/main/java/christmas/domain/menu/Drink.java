package christmas.domain.menu;

import java.util.Arrays;
import java.util.List;

public enum Drink implements Menu {
    ZERO_COKE(3_000, "제로콜라"),
    RED_WINE(60_000, "레드와인"),
    CHAMPAGNE(25_000, "샴페인");

    private final int price;
    private final String name;

    Drink(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static List<Drink> getAll() {
        return Arrays.stream(Drink.values()).toList();
    }

    @Override
    public boolean isSameCategory(Class<? extends Menu> category) {
        return this.getClass() == category;
    }

    @Override
    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }
}
