package christmas.domain.badge;

import static christmas.view.constant.output.ErrorMessage.INVALID_BENEFIT_AMOUNT;

import christmas.view.constant.output.OutputMessage;
import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE(OutputMessage.NO_BENEFIT, 0);

    private final String name;
    private final int lowerBoundInclusive;

    Badge(String name, int lowerBoundInclusive) {
        this.name = name;
        this.lowerBoundInclusive = lowerBoundInclusive;
    }

    public static Badge getBy(int benefitAmount) {
        return Arrays.stream(values())
                .filter(badge -> benefitAmount >= badge.lowerBoundInclusive)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_BENEFIT_AMOUNT.getMessage()));
    }

    public String getName() {
        return name;
    }
}
