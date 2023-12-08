package christmas.service;

import christmas.domain.AppliedDiscounts;
import christmas.domain.AppliedGifts;
import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.badge.Badge;
import christmas.domain.promotion.DiscountPromotion;
import christmas.domain.promotion.Gift;
import christmas.domain.promotion.GiftPromotion;
import christmas.domain.promotion.Promotion;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BenefitService {
    private BenefitService() {
    }

    public static AppliedDiscounts getAppliedDiscounts(VisitingDate visitingDate, Order order) {
        List<Promotion> discountPromotions = findApplicableDiscountPromotions(visitingDate, order);
        Map<DiscountPromotion, Integer> discountDetails = new LinkedHashMap<>();
        discountPromotions.stream()
                .map(promotion -> (DiscountPromotion) promotion)
                .forEach(
                        discountPromotion -> discountDetails.put(discountPromotion,
                                discountPromotion.getAmount(visitingDate, order)
                        )
                );
        return new AppliedDiscounts(discountDetails);
    }

    public static AppliedGifts getAppliedGifts(VisitingDate visitingDate, Order order) {
        List<Promotion> giftPromotions = findApplicableGiftPromotions(visitingDate, order);
        Map<Gift, Integer> giftDetails = new LinkedHashMap<>();
        giftPromotions.stream()
                .map(promotion -> (GiftPromotion) promotion)
                .forEach(
                        giftPromotion -> giftDetails.put(giftPromotion.getGift(),
                                giftPromotion.getAmount()
                        )
                );
        return new AppliedGifts(giftDetails);
    }

    public static Badge getBadgeBy(int totalBenefitAmount) {
        return Badge.getBy(totalBenefitAmount);
    }

    private static List<Promotion> findApplicableDiscountPromotions(VisitingDate visitingDate, Order order) {
        return Arrays.stream(DiscountPromotion.values())
                .filter(discountPromotion -> discountPromotion.isApplicable(visitingDate, order))
                .collect(Collectors.toList());
    }

    private static List<Promotion> findApplicableGiftPromotions(VisitingDate visitingDate, Order order) {
        return Arrays.stream(GiftPromotion.values())
                .filter(giftPromotion -> giftPromotion.isApplicable(visitingDate, order))
                .collect(Collectors.toList());
    }
}
