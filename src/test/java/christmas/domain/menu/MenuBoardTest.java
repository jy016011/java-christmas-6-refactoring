package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuBoardTest {
    @DisplayName("메뉴판에 없는 메뉴 호출시 예외 발생")
    @Test
    void findMenuByNotInMenuBoard() {
        assertThatThrownBy(() -> MenuBoard.getBy("게살버거")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 있는 메뉴 호출 잘되는지")
    @Test
    void findMenuByInMenuBoard() {
        assertThat(MenuBoard.getBy("타파스")).isEqualTo(Appetizer.TAPAS);
    }
}