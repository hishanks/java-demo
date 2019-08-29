package com.demo.util;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

/**
 * @author Shanks
 * @date 2019-08-16
 */
public enum RoundMethodEnum {

    SHE_RU("1", (x, y) -> y.setScale(x.intValue(), BigDecimal.ROUND_HALF_UP)),

    QU_WEI("2", (x, y) -> y.setScale(x.intValue(), BigDecimal.ROUND_DOWN)),

    JIN_YI("3", (x, y) -> y.setScale(x.intValue(), BigDecimal.ROUND_CEILING));

    private String method;
    private BinaryOperator<BigDecimal> operator;

    public BigDecimal apply(BigDecimal x, BigDecimal y) {
        return operator.apply(x, y);
    }

    RoundMethodEnum(String method, BinaryOperator<BigDecimal> operator) {
        this.method = method;
        this.operator = operator;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public BinaryOperator<BigDecimal> getOperator() {
        return operator;
    }

    public void setOperator(BinaryOperator<BigDecimal> operator) {
        this.operator = operator;
    }
}