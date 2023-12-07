package christmas.domain.dto;

import christmas.domain.AppliedGifts;
import java.util.Map;

public record AppliedGiftsResponse(
        Map<String, Integer> giftNameAndQuantity,
        String promotionName,
        int totalBenefitAmount
) {
    public AppliedGiftsResponse(AppliedGifts appliedGifts) {
        this(
                appliedGifts.getGiftNameAndQuantity(),
                appliedGifts.getPromotionName(),
                appliedGifts.getTotalBenefitAmount()
        );
    }
}
