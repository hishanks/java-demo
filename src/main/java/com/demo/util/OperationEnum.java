package com.demo.util;

import java.util.function.DoubleBinaryOperator;

/**
 * @author Shanks
 * @date 2019-06-20
 */
public enum OperationEnum {

    /**
     * 加，顺便把(x, y) -> x + y)优化为方法引用Double::sum
     */
    PLUS("+", Double::sum),
    /**
     * 减
     */
    MINUS("-", (x, y) -> x - y),
    /**
     * 乘
     */
    TIMES("*", (x, y) -> x * y),
    /**
     * 除
     */
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator operator;

    OperationEnum(String symbol, DoubleBinaryOperator operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    public static OperationEnum getBySymbol(String symbol) {
        for (OperationEnum value : OperationEnum.values()) {
            if (value.symbol.equals(symbol)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return operator.applyAsDouble(x, y);
    }
}