package christmas.domain.dto;

import christmas.domain.menu.Menu;
import christmas.domain.promotion.Promotion;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//key: gift name
public record AppliedGifts(Map<Menu, Integer> giftDetails) {

    @Override
    public Map<Menu, Integer> giftDetails() {
        return Collections.unmodifiableMap(giftDetails);
    }

    public Map<String, Integer> getContext() {
        Map<String, Integer> context = new HashMap<>();
        context.put(Promotion.giftPromotionName, getTotalBenefitAmount());
        return context;
    }

    public int getTotalBenefitAmount() {
        return giftDetails.keySet().stream()
                .mapToInt(Menu::getPrice)
                .sum();
    }
}
