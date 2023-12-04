package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.promotion.date.PromotionDays;
import christmas.domain.promotion.date.PromotionPeriod;
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
        VisitingDate visitingDate = new VisitingDate(2);
        assertThat(PromotionPeriod.DECEMBER.contains(visitingDate)).isEqualTo(true);
    }

    @DisplayName("방문날짜가 주어진 날들에 포함되는지 확인")
    @Test
    void isContainedInDays() {
        VisitingDate visitingDate = new VisitingDate(3);
        assertThat(PromotionDays.SPECIAL_DAYS.contains(visitingDate)).isEqualTo(true);
    }
}