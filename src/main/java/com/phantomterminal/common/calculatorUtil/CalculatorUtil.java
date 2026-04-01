package com.phantomterminal.common.calculatorUtil;

import java.math.BigDecimal;
import java.util.Stack;

public interface CalculatorUtil {
    boolean isNumber(String token);
    boolean isOperator(String operator);
    int operatorPrecedence(String operator);
    void performOperation(Stack<BigDecimal> numbers,String operator);
}
