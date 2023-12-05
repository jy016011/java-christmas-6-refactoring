package christmas.domain.dto;

import java.util.Collections;
import java.util.Map;

//key: discount name, value: benefit amount of discount
public record AppliedDiscounts(Map<String, Integer> discountContext) {

    @Override
    public Map<String, Integer> discountContext() {
        return Collections.unmodifiableMap(discountContext);
    }

    public int getTotalBenefitAmount() {
        return discountContext.keySet().stream()
                .mapToInt(discountContext::get)
                .sum();
    }
}
