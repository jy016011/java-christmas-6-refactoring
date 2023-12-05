package christmas.domain;

import christmas.domain.constraint.DateConstraint;
import christmas.utils.ArgumentValidator;
import java.time.LocalDate;
import java.util.List;

public class VisitingDate {
    private static final int PROMOTION_YEAR = DateConstraint.PROMOTION_YEAR.getValue();
    private static final int PROMOTION_MONTH = DateConstraint.PROMOTION_MONTH.getValue();
    private static final int START_DAY = DateConstraint.DAY_OF_START.getValue();
    private static final int LAST_DAY = DateConstraint.DAY_OF_LAST.getValue();
    private static final int FRIDAY_VALUE = 5;
    private static final int SATURDAY_VALUE = 6;

    private final LocalDate visitDay;

    public VisitingDate(int day) {
        validate(day);
        visitDay = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, day);
    }

    public boolean isContainedIn(LocalDate startDayInclusive, LocalDate lastDayInclusive) {
        return !(visitDay.isBefore(startDayInclusive) || visitDay.isAfter(lastDayInclusive));
    }

    public boolean isContainedIn(List<LocalDate> days) {
        return days.contains(visitDay);
    }

    public int getDifferenceFromStartDay() {
        return visitDay.getDayOfMonth() - START_DAY;
    }

    public boolean isWeekend() {
        int dayOfWeekValue = visitDay.getDayOfWeek().getValue();
        return dayOfWeekValue == FRIDAY_VALUE || dayOfWeekValue == SATURDAY_VALUE;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    private void validate(int day) {
        ArgumentValidator.isNotLessThan(day, START_DAY);
        ArgumentValidator.isNotGreaterThan(day, LAST_DAY);
    }
}
