package christmas.domain.menu;

public interface Menu {
    boolean isSameCategory(Class<? extends Menu> category);

    boolean isSameName(String name);

    String getName();

    int getPrice();
}
