package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.promotion.condition.date.PromotionDays;
import christmas.domain.promotion.condition.date.PromotionPeriod;
import christmas.utils.StringParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitingDateTest {
    @DisplayName("입력 숫자가 1보다 작거나 31초과 일경우 예외가 발생")
    @ValueSource(strings = {"0", "32"})
    @ParameterizedTest
    void createVisitingDateByOutOfRangeNumber(String userInput) {
        int day = StringParser.toInteger(userInput);
        assertThatThrownBy(() -> new VisitingDate(day)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문날짜가 주어진 기간에 포함되는지 확인")
    @Test
    void isContainedInPeriod() {
        VisitingDate visitingDate = new VisitingDate(25);
        assertThat(PromotionPeriod.UNTIL_CHRISTMAS.contains(visitingDate)).isEqualTo(true);
    }

    @DisplayName("26일부터는 크리스마스 디데이 기간에 포함 안됨")
    @Test
    void isContainedInChristmasDDayPeriod() {
        VisitingDate visitingDate = new VisitingDate(26);
        assertThat(PromotionPeriod.UNTIL_CHRISTMAS.contains(visitingDate)).isEqualTo(false);
    }

    @DisplayName("방문날짜가 주어진 날들에 포함되는지 확인")
    @Test
    void isContainedInDays() {
        VisitingDate visitingDate = new VisitingDate(3);
        assertThat(PromotionDays.SPECIAL_DAYS.contains(visitingDate)).isEqualTo(true);
    }

    @DisplayName("크리스마스 디데이 할인액 산출에 필요한 시작 첫 날로부터의 차이 반환")
    @Test
    void getCountOfAdditionalChristmasDDayDiscount() {
        VisitingDate visitingDate = new VisitingDate(3);
        assertThat(visitingDate.getDifferenceFromStartDay()).isEqualTo(2);
    }
}