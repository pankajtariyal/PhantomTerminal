package com.phantomterminal.common.calculatorUtil;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Utility interface for calculator operations used in PhantomTerminal.
 *
 * <p>This interface defines helper methods required to parse and evaluate
 * mathematical expressions. It provides methods to identify numbers and
 * operators, determine operator precedence, and perform arithmetic operations
 * using a stack-based evaluation approach.</p>
 *
 * <p>Implementations of this interface are expected to provide the logic
 * for validating tokens and executing arithmetic operations.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public interface CalculatorUtil {

    /**
     * Checks whether the given token represents a valid number.
     *
     * <p>This method is typically used while parsing an expression
     * to determine whether the token should be pushed onto the
     * number stack.</p>
     *
     * @param token the string token to check
     * @return true if the token is a valid numeric value, otherwise false
     */
    boolean isNumber(String token);

    /**
     * Checks whether the given string represents a valid arithmetic operator.
     *
     * <p>Typical supported operators may include:
     * <ul>
     *     <li>+</li>
     *     <li>-</li>
     *     <li>*</li>
     *     <li>/</li>
     * </ul>
     * </p>
     *
     * @param operator the operator string
     * @return true if the token is a valid operator, otherwise false
     */
    boolean isOperator(String operator);

    /**
     * Returns the precedence level of the given operator.
     *
     * <p>Operator precedence determines the order in which operations
     * are evaluated in an expression.</p>
     *
     * <p>Example precedence levels:
     * <ul>
     *     <li>* and / : higher precedence</li>
     *     <li>+ and - : lower precedence</li>
     * </ul>
     * </p>
     *
     * @param operator the operator whose precedence is required
     * @return an integer representing the precedence level
     */
    int operatorPrecedence(String operator);

    /**
     * Performs the arithmetic operation using the top two numbers
     * from the stack and pushes the result back onto the stack.
     *
     * <p>The operation follows this process:
     * <ol>
     *     <li>Pop two numbers from the stack</li>
     *     <li>Apply the operator</li>
     *     <li>Push the result back onto the stack</li>
     * </ol>
     * </p>
     *
     * @param numbers the stack containing numeric values
     * @param operator the operator to apply
     */
    void performOperation(Stack<BigDecimal> numbers, String operator);
}
