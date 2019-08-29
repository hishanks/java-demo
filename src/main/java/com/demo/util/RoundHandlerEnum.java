package com.demo.util;

import java.math.BigDecimal;
import java.util.function.BiFunction;

/**
 * @author Shanks
 * @date 2019-08-17
 */
public enum RoundHandlerEnum {

    SHE_RU("1", (x, y) -> y.setScale(x, BigDecimal.ROUND_HALF_UP)),

    QU_WEI("2", (x, y) -> y.setScale(x, BigDecimal.ROUND_DOWN)),

    JIN_YI("3", (x, y) -> y.setScale(x, BigDecimal.ROUND_CEILING));

    private String method;
    private BiFunction<Integer, BigDecimal, BigDecimal> operator;

    public BigDecimal apply(Integer x, BigDecimal y) {
        return operator.apply(x, y);
    }

    RoundHandlerEnum(String method, BiFunction<Integer, BigDecimal, BigDecimal> operator) {
        this.method = method;
        this.operator = operator;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public BiFunction<Integer, BigDecimal, BigDecimal> getOperator() {
        return operator;
    }

    public void setOperator(BiFunction<Integer, BigDecimal, BigDecimal> operator) {
        this.operator = operator;
    }
}
