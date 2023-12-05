package christmas.domain.promotion;

import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;

public enum Gift {
    CHAMPAGNE(Drink.CHAMPAGNE, 1);

    private final Menu menu;
    private final int quantity;

    Gift(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
