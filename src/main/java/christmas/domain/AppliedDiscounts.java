package christmas.domain;

import christmas.domain.promotion.DiscountPromotion;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//key: discount name, value: benefit amount of discount
public class AppliedDiscounts {
    private final Map<DiscountPromotion, Integer> discountPromotionAndBenefit;

    public AppliedDiscounts(Map<DiscountPromotion, Integer> discountPromotionAndBenefit) {
        this.discountPromotionAndBenefit = discountPromotionAndBenefit;
    }

    public Map<String, Integer> getPromotionNameAndBenefit() {
        return discountPromotionAndBenefit.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Entry::getValue
                ));
    }

    public int getTotalBenefitAmount() {
        return discountPromotionAndBenefit.keySet().stream()
                .mapToInt(discountPromotionAndBenefit::get)
                .sum();
    }
}
