package com.phantomterminal.commands.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Advanced tokenizer for mathematical expressions used in PhantomTerminal.
 *
 * <p>This tokenizer converts a mathematical expression into tokens
 * such as numbers, operators, and parentheses.</p>
 *
 * <p>Supported features:</p>
 * <ul>
 *     <li>Integers and decimal numbers</li>
 *     <li>Operators (+, -, *, /)</li>
 *     <li>Parentheses</li>
 *     <li>Negative numbers</li>
 *     <li>Expressions with or without spaces</li>
 * </ul>
 *
 * <p><b>Example:</b></p>
 * <pre>
 * Input  : 2*-3 + (4.5/2)
 * Output : ["2", "*", "-3", "+", "(", "4.5", "/", "2", ")"]
 * </pre>
 */
public class Tokenize {

    /**
     * Tokenizes a mathematical expression into numbers,
     * operators, and parentheses.
     *
     * @param expression mathematical expression
     * @return list of tokens
     */
    public static List<String> tokenize(String expression) {

        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        char[] chars = expression.replaceAll("\\s+", "").toCharArray();

        for (int i = 0; i < chars.length; i++) {

            char ch = chars[i];

            // Build numbers
            if (Character.isDigit(ch) || ch == '.') {
                number.append(ch);
                continue;
            }

            // Handle negative numbers
            if (ch == '-') {

                if (i == 0 || isOperator(chars[i - 1]) || chars[i - 1] == '(') {
                    number.append(ch);
                    continue;
                }
            }

            // Flush number before operator
            if (number.length() > 0) {
                tokens.add(number.toString());
                number.setLength(0);
            }

            tokens.add(String.valueOf(ch));
        }

        // Add last number
        if (number.length() > 0) {
            tokens.add(number.toString());
        }

        return tokens;
    }

    /**
     * Checks whether a character is an operator.
     *
     * @param ch character to check
     * @return true if operator
     */
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}
