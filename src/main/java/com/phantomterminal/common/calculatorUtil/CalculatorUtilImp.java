package com.phantomterminal.common.calculatorUtil;

import com.phantomterminal.common.CommonVariable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

/**
 * Implementation of the {@link CalculatorUtil} interface.
 *
 * <p>This class provides utility methods used by the calculator module
 * in PhantomTerminal to evaluate mathematical expressions.</p>
 *
 * <p>The class follows the <b>Singleton Design Pattern</b> using
 * double-checked locking to ensure only one instance of the utility
 * exists during application runtime.</p>
 *
 * <p>Main responsibilities of this class include:</p>
 * <ul>
 *     <li>Checking whether a token is a valid number</li>
 *     <li>Checking whether a token is a valid operator</li>
 *     <li>Determining operator precedence</li>
 *     <li>Performing arithmetic operations using a stack</li>
 * </ul>
 *
 * <p>This class is primarily used in expression parsing and evaluation
 * algorithms such as stack-based infix expression evaluation.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class CalculatorUtilImp implements CalculatorUtil {

    /**
     * Singleton instance of {@link CalculatorUtil}.
     */
    public static CalculatorUtil calculatorUtil = null;

    /**
     * Checks whether the given token is a valid numeric value.
     *
     * <p>This method attempts to convert the token into a {@link BigDecimal}.
     * If the conversion succeeds, the token is considered a number.</p>
     *
     * @param token the string token to validate
     * @return true if the token is a valid number, otherwise false
     */
    @Override
    public boolean isNumber(String token) {
        try {
            new BigDecimal(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Checks whether the given string represents a supported arithmetic operator.
     *
     * <p>Supported operators:</p>
     * <ul>
     *     <li>+</li>
     *     <li>-</li>
     *     <li>*</li>
     *     <li>/</li>
     * </ul>
     *
     * @param operator the operator token
     * @return true if it is a supported operator, otherwise false
     */
    @Override
    public boolean isOperator(String operator) {
        return operator.equals("+") ||
                operator.equals("-") ||
                operator.equals("*") ||
                operator.equals("/");
    }

    /**
     * Returns the precedence level of the given operator.
     *
     * <p>Operator precedence determines the order in which operations
     * should be executed during expression evaluation.</p>
     *
     * <p>Precedence levels:</p>
     * <ul>
     *     <li>+ and - : precedence 1</li>
     *     <li>* and / : precedence 2</li>
     * </ul>
     *
     * @param operator the operator whose precedence is requested
     * @return precedence value as an integer
     */
    @Override
    public int operatorPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
                return 2;
        }
        return 0;
    }

    /**
     * Performs the arithmetic operation on the top two numbers
     * from the stack and pushes the result back to the stack.
     *
     * <p>Execution flow:</p>
     * <ol>
     *     <li>Pop the top two numbers from the stack</li>
     *     <li>Apply the specified operator</li>
     *     <li>Push the result back onto the stack</li>
     * </ol>
     *
     * <p>This method is typically used in stack-based expression
     * evaluation algorithms.</p>
     *
     * @param numbers stack containing numeric operands
     * @param exp operator to apply
     */
    public void performOperation(Stack<BigDecimal> numbers, String exp) {
        if (numbers.size()<2) return;

        BigDecimal num2 = numbers.pop();
        BigDecimal num1 = numbers.pop();
        BigDecimal result = null;

        switch (exp) {
            case "+":
                result = num1.add(num2);
                break;

            case "-":
                result = num1.subtract(num2);
                break;

            case "*":
                result = num1.multiply(num2);
                break;

            case "/":
                if(num2.intValue()==2){
                    CommonVariable.outputAreaCommon.appendText("can not divide by zero");
                    return;
                }
                result = num1.divide(num2, MathContext.DECIMAL128);
                break;
        }

        numbers.push(result);
    }

    /**
     * Returns the singleton instance of {@link CalculatorUtil}.
     *
     * <p>This method implements the <b>Double-Checked Locking</b>
     * mechanism to ensure thread-safe lazy initialization.</p>
     *
     * <p>The instance will be created only once and reused
     * throughout the application.</p>
     *
     * @return singleton instance of CalculatorUtil
     */
    public static CalculatorUtil getInstance() {
        if (calculatorUtil == null) {
            synchronized (CalculatorUtilImp.class) {
                if (calculatorUtil == null) {
                    calculatorUtil = new CalculatorUtilImp();
                }
            }
        }
        return calculatorUtil;
    }
}
