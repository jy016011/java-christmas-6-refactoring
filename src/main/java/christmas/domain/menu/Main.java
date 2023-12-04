package christmas.domain.menu;

import java.util.Arrays;
import java.util.List;

public enum Main implements Menu {
    T_BONE_STEAK(55_000, "티본스테이크"),
    BARBECUE_RIBS(54_000, "바비큐립"),
    SEAFOOD_PASTA(35_000, "해산물파스타"),
    CHRISTMAS_PASTA(25_000, "크리스마스파스타");

    private final int price;
    private final String name;

    Main(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public static List<Main> getAll() {
        return Arrays.stream(Main.values()).toList();
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
