package christmas.domain.dto;

import christmas.domain.promotion.Gift;
import christmas.domain.promotion.Promotion;
import java.util.HashMap;
import java.util.Map;

//key: gift, value: amount of benefit
public record AppliedGifts(Map<Gift, Integer> giftDetails) {

    public Map<String, Integer> getGifts() {
        Map<String, Integer> gifts = new HashMap<>();
        giftDetails.keySet()
                .forEach(gift -> gifts.put(gift.getMenu().getName(), gift.getQuantity()));
        return gifts;
    }

    public Map<String, Integer> getDetails() {
        Map<String, Integer> details = new HashMap<>();
        details.put(Promotion.giftPromotionName, getTotalBenefitAmount());
        return details;
    }

    public int getTotalBenefitAmount() {
        return giftDetails.keySet().stream()
                .mapToInt(giftDetails::get)
                .sum();
    }
}
