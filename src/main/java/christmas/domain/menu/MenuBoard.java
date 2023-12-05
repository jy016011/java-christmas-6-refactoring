package christmas.domain.menu;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MenuBoard {
    private MenuBoard() {
    }

    public static List<Menu> getAll() {
        return Stream.of(Appetizer.getAll(), Dessert.getAll(), Drink.getAll(), Main.getAll())
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static Menu getBy(String name) {
        return getAll().stream().filter(menu -> menu.isSameName(name)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
