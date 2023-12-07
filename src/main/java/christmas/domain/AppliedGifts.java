package christmas.domain;

import christmas.domain.promotion.Gift;
import christmas.domain.promotion.Promotion;
import java.util.Map;
import java.util.stream.Collectors;

//key: gift, value: amount of benefit
public class AppliedGifts {
    private final Map<Gift, Integer> giftAndAmount;

    public AppliedGifts(Map<Gift, Integer> giftAndAmount) {
        this.giftAndAmount = giftAndAmount;
    }

    public Map<String, Integer> getGiftNameAndQuantity() {
        return giftAndAmount.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getMenu().getName(),
                        entry -> entry.getKey().getQuantity()
                ));
    }

    public String getPromotionName() {
        return Promotion.giftPromotionName;
    }

    public int getTotalBenefitAmount() {
        return giftAndAmount.keySet().stream()
                .mapToInt(giftAndAmount::get)
                .sum();
    }
}
