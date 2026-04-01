package com.phantomterminal.commands.calculator;

/**
 * The {@code SubtractCommand} class represents the calculator command
 * responsible for performing subtraction operations in the
 * PhantomTerminal application.
 *
 * <p>This class extends {@link AbstractCalculatorCommand} and provides
 * the specific operator and command name required for subtraction.</p>
 *
 * <p>The common execution logic such as argument validation,
 * number parsing, stack processing, and result output is handled
 * by the {@link AbstractCalculatorCommand} base class.</p>
 *
 * <h3>Example Usage</h3>
 * <pre>
 * sub 20 5 3
 * </pre>
 *
 * Output:
 * <pre>
 * 12
 * </pre>
 *
 * <p>The command expects operands to be provided as
 * space-separated numeric values.</p>
 *
 * <p>All calculations are performed using
 * {@link java.math.BigDecimal} to maintain precision.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class SubtractCommand extends AbstractCalculatorCommand {

    /**
     * Returns the subtraction operator used by this command.
     *
     * @return the subtraction operator "-"
     */
    @Override
    protected String getOperator() {
        return "-";
    }

    /**
     * Returns the command name used in the terminal.
     *
     * <p>This name is used in error and warning messages
     * during command execution.</p>
     *
     * @return the command name "sub"
     */
    @Override
    protected String getCommandName() {
        return "sub";
    }
}
