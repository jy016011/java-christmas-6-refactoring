package christmas.domain.dto;

import java.util.Collections;
import java.util.Map;

//key: discount name, value: benefit amount of discount
public record AppliedDiscounts(Map<String, Integer> details) {

    @Override
    public Map<String, Integer> details() {
        return Collections.unmodifiableMap(details);
    }

    public int getTotalBenefitAmount() {
        return details.keySet().stream()
                .mapToInt(details::get)
                .sum();
    }
}
