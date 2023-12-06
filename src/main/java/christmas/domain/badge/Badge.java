package christmas.domain.badge;

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
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 총혜택 금액입니다."));

    }

    public String getName() {
        return name;
    }
}
