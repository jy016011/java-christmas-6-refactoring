package christmas.view.constant.output;

import static christmas.domain.constraint.DateConstraint.PROMOTION_MONTH;

public interface OutputMessage {
    int MONTH = PROMOTION_MONTH.getValue();
    String GREETING = String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", MONTH);
    String NO_BENEFIT = "없음";
    String NO_BENEFIT_AMOUNT = "0원";
    String ERROR_HEADER = "[ERROR]";


    String getMessage();

    static String getPreviewOfPromotionResult(int day) {
        return String.format("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", MONTH, day);
    }

}
