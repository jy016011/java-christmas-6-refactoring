package christmas.domain;

import static christmas.domain.constraint.DateConstraint.DAY_OF_LAST;
import static christmas.domain.constraint.DateConstraint.DAY_OF_START;
import static christmas.domain.constraint.DateConstraint.PROMOTION_MONTH;
import static christmas.domain.constraint.DateConstraint.PROMOTION_YEAR;

import christmas.utils.ArgumentValidator;
import java.time.LocalDate;
import java.util.List;

public class VisitingDate {
    private static final int FRIDAY_VALUE = 5;
    private static final int SATURDAY_VALUE = 6;

    private final LocalDate visitDay;

    public VisitingDate(int day) {
        validate(day);
        visitDay = LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), day);
    }

    public boolean isContainedIn(LocalDate startDayInclusive, LocalDate lastDayInclusive) {
        return !(visitDay.isBefore(startDayInclusive) || visitDay.isAfter(lastDayInclusive));
    }

    public boolean isContainedIn(List<LocalDate> days) {
        return days.contains(visitDay);
    }

    public int getDifferenceFromStartDay() {
        return visitDay.getDayOfMonth() - DAY_OF_START.getValue();
    }

    public boolean isWeekend() {
        int dayOfWeekValue = visitDay.getDayOfWeek().getValue();
        return dayOfWeekValue == FRIDAY_VALUE || dayOfWeekValue == SATURDAY_VALUE;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    private void validate(int day) {
        ArgumentValidator.isNotLessThan(day, DAY_OF_START.getValue());
        ArgumentValidator.isNotGreaterThan(day, DAY_OF_LAST.getValue());
    }
}
