package lotto.utils;

import lotto.Constant;

public class ValidationUtil {

    public static String ERROR_AMOUNT_MSG = "[오류] 구입금액은 1000원이상으로 입력해야됩니다.";
    public static String ERROR_NEGATIVE_MSG = "[오류] 입력값이 정수여야합니다.";

    public static void checkNumber(String num) {
        isNumber(num);
        isNegativeNum(num);
        isPurchaseAmount(num);
    }

    public static void isPurchaseAmount(String num) {
        if (Integer.parseInt(num) < 1000) {
            throw new IllegalArgumentException(ERROR_AMOUNT_MSG);
        }
    }

    public static void isNegativeNum(String num) {
        if (Integer.parseInt(num) < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_MSG);
        }
    }

    public static void isNumber(String num) {
        if (num == null) {
            throw new IllegalArgumentException(Constant.ERROR_NULL_MSG);
        }
    }
}
