package christmas.view.constant.output;

import static christmas.domain.constraint.DateConstraint.PROMOTION_MONTH;

public enum HeadingOfResult implements OutputMessage {
    ORDERS("<주문 메뉴>"),
    ORIGIN_TOTAL_PRICE("<할인 전 총주문 금액>"),
    GIFT_DETAIL("<증정 메뉴>"),
    BENEFIT_DETAIL("<혜택 내역>"),
    TOTAL_BENEFIT("<총혜택 금액>"),
    EXPECTED_PAYMENT_AMOUNT("<할인 후 예상 결제 금액>"),
    BADGE(String.format("<%d월 이벤트 배지>", PROMOTION_MONTH.getValue()));

    private final String message;

    HeadingOfResult(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
