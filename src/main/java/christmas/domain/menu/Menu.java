package christmas.domain.menu;

public interface Menu {
    public boolean isSameCategory(Class<? extends Menu> category);

    public boolean isSameName(String name);

    public String getName();

    public int getPrice();
}
