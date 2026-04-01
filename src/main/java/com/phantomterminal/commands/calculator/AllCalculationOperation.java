package com.phantomterminal.commands.calculator;

import com.phantomterminal.commands.tokenizer.Tokenize;
import com.phantomterminal.common.CommonVariable;
import com.phantomterminal.common.calculatorUtil.CalculatorUtil;
import com.phantomterminal.common.calculatorUtil.CalculatorUtilImp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

/**
 * The {@code AllCalculationOperation} class implements the
 * {@link CalculatorCommand} interface and provides support for
 * evaluating full mathematical expressions in the PhantomTerminal
 * calculator.
 *
 * <p>This command allows users to perform complex calculations
 * including multiple operators and parentheses. The expression
 * is first tokenized and then evaluated using a stack-based
 * algorithm that respects operator precedence.</p>
 *
 * <h3>Supported Operators</h3>
 * <ul>
 *     <li>Addition (+)</li>
 *     <li>Subtraction (-)</li>
 *     <li>Multiplication (*)</li>
 *     <li>Division (/)</li>
 * </ul>
 *
 * <h3>Features</h3>
 * <ul>
 *     <li>Handles multiple operators in a single expression</li>
 *     <li>Supports parentheses for grouping operations</li>
 *     <li>Respects mathematical operator precedence</li>
 *     <li>Uses {@link BigDecimal} for high precision arithmetic</li>
 * </ul>
 *
 * <h3>Example Usage</h3>
 * <pre>
 * cal 5+3*2
 * </pre>
 *
 * Output:
 * <pre>
 * 11
 * </pre>
 *
 * <pre>
 * cal (5+3)*2
 * </pre>
 *
 * Output:
 * <pre>
 * 16
 * </pre>
 *
 * <p>The expression is tokenized using the {@link Tokenize} utility
 * class before evaluation.</p>
 *
 * <p>The evaluation algorithm uses two stacks:</p>
 * <ul>
 *     <li>A number stack for operands</li>
 *     <li>An operator stack for operators</li>
 * </ul>
 *
 * <p>This approach is similar to the <b>Shunting Yard Algorithm</b>
 * used in many expression evaluators.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class AllCalculationOperation implements CalculatorCommand {

    /**
     * Utility instance used for validating numbers, operators,
     * and performing arithmetic operations.
     */
    private final CalculatorUtil calculatorUtil;

    /**
     * Constructs a new {@code AllCalculationOperation} and
     * initializes the calculator utility using the singleton
     * implementation {@link CalculatorUtilImp}.
     */
    public AllCalculationOperation() {
        calculatorUtil = CalculatorUtilImp.getInstance();
    }

    /**
     * Executes the calculator command and evaluates the provided
     * mathematical expression.
     *
     * <p>The expression is first combined into a single string,
     * tokenized, and then evaluated using stacks for numbers
     * and operators.</p>
     *
     * <h3>Argument Structure</h3>
     * <ul>
     *     <li>{@code args[0]} – command name (cal)</li>
     *     <li>{@code args[1..n]} – mathematical expression</li>
     * </ul>
     *
     * @param args list of command arguments
     * @throws IOException if an error occurs during execution
     */
    @Override
    public void execute(List<String> args) throws IOException {

        if (args.size() < 2) {
            CommonVariable.outputAreaCommon.appendText("cal missing operand");
            return;
        }

        String expression = String.join("", args.subList(1, args.size()));
        List<String> tokenizer = Tokenize.tokenize(expression);

        Stack<BigDecimal> number = new Stack<>();
        Stack<String> operator = new Stack<>();

        for (int i = 0; i < tokenizer.size(); i++) {

            String token = tokenizer.get(i);

            if (calculatorUtil.isNumber(token)) {
                number.add(new BigDecimal(token));
            }

            else if (token.equalsIgnoreCase("(")) {
                operator.add(token);
            }

            else if (token.equals(")")) {
                while (!operator.isEmpty() && !operator.peek().equals("(")) {
                    calculatorUtil.performOperation(number, operator.pop());
                }

                if (!operator.isEmpty()) {
                    operator.pop();
                }
            }

            else if (calculatorUtil.isOperator(token)) {

                while (!operator.isEmpty() &&
                        calculatorUtil.operatorPrecedence(operator.peek())
                                >= calculatorUtil.operatorPrecedence(token)) {

                    calculatorUtil.performOperation(number, operator.pop());
                }

                operator.push(token);
            }
        }

        while (!operator.isEmpty()) {
            calculatorUtil.performOperation(number, operator.pop());
        }

        BigDecimal result = number.pop();
        CommonVariable.outputAreaCommon.appendText(result.toString() + "\n");
    }
}
