package christmas.service;

import christmas.domain.Order;
import christmas.domain.VisitingDate;
import christmas.domain.dto.AppliedDiscounts;
import christmas.domain.dto.AppliedGifts;
import christmas.domain.menu.Menu;
import christmas.domain.promotion.DiscountPromotion;
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

    public static AppliedDiscounts getApplicableDiscounts(VisitingDate visitingDate, Order order) {
        List<Promotion> discountPromotions = findApplicableDiscountPromotions(visitingDate, order);
        Map<String, Integer> discountDetails = new LinkedHashMap<>();
        discountPromotions.stream()
                .map(promotion -> (DiscountPromotion) promotion)
                .forEach(
                        discountPromotion -> discountDetails.put(discountPromotion.getName(),
                                discountPromotion.getAmount(visitingDate, order)
                        )
                );
        return new AppliedDiscounts(discountDetails);
    }

    public static AppliedGifts getApplicableGifts(VisitingDate visitingDate, Order order) {
        List<Promotion> giftPromotions = findApplicableGiftPromotions(visitingDate, order);
        Map<Menu, Integer> giftDetails = new LinkedHashMap<>();
        giftPromotions.stream()
                .map(promotion -> (GiftPromotion) promotion)
                .forEach(
                        giftPromotion -> giftDetails.put(giftPromotion.getGift(), giftPromotion.getQuantity())
                );
        return new AppliedGifts(giftDetails);
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
