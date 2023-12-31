package christmas.domain.dto;

import christmas.domain.AppliedDiscounts;
import java.util.Map;

public record AppliedDiscountsResponse(Map<String, Integer> promotionNameAndBenefit, int totalBenefitAmount) {
    public AppliedDiscountsResponse(AppliedDiscounts appliedDiscounts) {
        this(
                appliedDiscounts.getPromotionNameAndBenefit(),
                appliedDiscounts.getTotalBenefitAmount()
        );
    }
}
