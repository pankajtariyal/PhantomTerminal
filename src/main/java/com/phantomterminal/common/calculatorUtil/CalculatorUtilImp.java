package com.phantomterminal.common.calculatorUtil;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.math.BigDecimal;
import java.util.Stack;

public class CalculatorUtilImp implements CalculatorUtil{
    public static CalculatorUtil calculatorUtil = null;
    @Override
    public boolean isNumber(String token) {
        try{
            new BigDecimal(token);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean isOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
    }

    @Override
    public int operatorPrecedence(String operator) {
        switch (operator){
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return 0;
    }

    public void performOperation(Stack<BigDecimal> numbers, String exp){
        if(numbers.isEmpty())return;
        BigDecimal num2 = numbers.pop();
        BigDecimal num1 = numbers.pop();
        BigDecimal result = null;
        switch (exp){
            case "+": result =  num1.add(num2);
                break;
            case "-":result = num1.subtract(num2);
                break;
            case "*":result = num1.multiply(num2);
                break;
            case "/":result = num1.divide(num2);
                break;
        }
        numbers.push(result);
    }

    public static CalculatorUtil getInstance(){
        if(calculatorUtil == null){
            synchronized (CalculatorUtilImp.class){
                if(calculatorUtil == null){
                    calculatorUtil = new CalculatorUtilImp();
                }
            }
        }
        return calculatorUtil;
    }
}
