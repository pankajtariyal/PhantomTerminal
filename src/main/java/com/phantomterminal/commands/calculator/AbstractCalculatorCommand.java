package com.phantomterminal.commands.calculator;

import com.phantomterminal.common.CommonVariable;
import com.phantomterminal.common.calculatorUtil.CalculatorUtil;
import com.phantomterminal.common.calculatorUtil.CalculatorUtilImp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

/**
 * The {@code AbstractCalculatorCommand} class provides a base implementation
 * for all calculator commands in the PhantomTerminal application.
 *
 * <p>This abstract class implements the {@link CalculatorCommand} interface
 * and contains the common logic required for performing mathematical
 * operations such as parsing operands, validating numeric input,
 * executing calculations, and displaying results.</p>
 *
 * <p>Concrete calculator commands such as {@code AddCommand},
 * {@code SubtractCommand}, {@code MultiplyCommand}, and
 * {@code DivideCommand} extend this class and only need to
 * specify the operator and command name.</p>
 *
 * <p>This design follows the <b>Template Method Pattern</b>, where
 * the general algorithm for executing calculator commands is
 * defined in this class, while subclasses provide specific
 * behavior through the {@link #getOperator()} and
 * {@link #getCommandName()} methods.</p>
 *
 * <h3>Example Implementation</h3>
 * <pre>
 * public class AddCommand extends AbstractCalculatorCommand {
 *
 *     protected String getOperator() {
 *         return "+";
 *     }
 *
 *     protected String getCommandName() {
 *         return "add";
 *     }
 * }
 * </pre>
 *
 * <p>The command expects operands to be provided as space-separated
 * numeric values.</p>
 *
 * <h3>Example Usage</h3>
 * <pre>
 * add 10 20 30
 * </pre>
 *
 * Output:
 * <pre>
 * 60
 * </pre>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public abstract class AbstractCalculatorCommand implements CalculatorCommand {

    /**
     * Utility instance used for validating numbers and performing
     * arithmetic operations.
     */
    private final CalculatorUtil calculatorUtil;

    /**
     * Constructs the abstract calculator command and initializes
     * the calculator utility using the singleton implementation
     * {@link CalculatorUtilImp}.
     */
    public AbstractCalculatorCommand() {
        this.calculatorUtil = CalculatorUtilImp.getInstance();
    }

    /**
     * Returns the mathematical operator associated with the command.
     *
     * <p>Subclasses must implement this method to specify the
     * operation symbol (e.g., {@code +}, {@code -}, {@code *}, {@code /}).</p>
     *
     * @return the operator symbol used for calculation
     */
    protected abstract String getOperator();

    /**
     * Returns the name of the calculator command.
     *
     * <p>This name is used in error and warning messages displayed
     * in the terminal.</p>
     *
     * @return the command name
     */
    protected abstract String getCommandName();

    /**
     * Executes the calculator command.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *     <li>Validates that enough operands are provided</li>
     *     <li>Extracts and validates numeric tokens</li>
     *     <li>Pushes operands onto a stack</li>
     *     <li>Performs the specified mathematical operation
     *         until a single result remains</li>
     *     <li>Displays the result in the terminal output</li>
     * </ol>
     *
     * <h3>Argument Structure</h3>
     * <ul>
     *     <li>{@code args[0]} – command name</li>
     *     <li>{@code args[1..n]} – numeric operands</li>
     * </ul>
     *
     * @param args list of command arguments
     * @throws IOException if an input/output error occurs during execution
     */
    @Override
    public void execute(List<String> args) throws IOException {

        if (args.size() < 3) {
            CommonVariable.outputAreaCommon.appendText(
                    "error: " + getCommandName() + " missing operand\n"
            );
            return;
        }

        List<String> tokens = args.subList(1, args.size());
        Stack<BigDecimal> numbers = new Stack<>();

        for (String token : tokens) {
            if (calculatorUtil.isNumber(token)) {
                numbers.add(new BigDecimal(token));
            } else {
                CommonVariable.outputAreaCommon.appendText(
                        "warning: " + getCommandName() + ": only numbers allowed\n"
                );
                return;
            }
        }

        while (numbers.size() > 1) {
            calculatorUtil.performOperation(numbers, getOperator());
        }

        CommonVariable.outputAreaCommon.appendText(numbers.pop() + "\n");
    }
}
