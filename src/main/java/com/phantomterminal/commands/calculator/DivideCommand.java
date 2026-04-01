package com.phantomterminal.commands.calculator;

/**
 * The {@code DivideCommand} class represents the calculator command
 * responsible for performing division operations in the
 * PhantomTerminal application.
 *
 * <p>This class extends {@link AbstractCalculatorCommand} and provides
 * the specific operator and command name required to execute
 * division calculations.</p>
 *
 * <p>The core logic such as argument validation, numeric parsing,
 * stack management, and result output is handled by the
 * {@link AbstractCalculatorCommand} base class.</p>
 *
 * <h3>Example Usage</h3>
 * <pre>
 * div 20 5
 * </pre>
 *
 * Output:
 * <pre>
 * 4
 * </pre>
 *
 * <p>The command expects operands to be provided as
 * space-separated numeric values.</p>
 *
 * <p>Division operations are performed using
 * {@link java.math.BigDecimal} to maintain high precision
 * in calculations.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class DivideCommand extends AbstractCalculatorCommand {

    /**
     * Returns the mathematical operator used for division.
     *
     * @return the division operator "/"
     */
    @Override
    protected String getOperator() {
        return "/";
    }

    /**
     * Returns the command name used in the terminal.
     *
     * <p>This name is used in error and warning messages
     * during command execution.</p>
     *
     * @return the command name "div"
     */
    @Override
    protected String getCommandName() {
        return "div";
    }
}
