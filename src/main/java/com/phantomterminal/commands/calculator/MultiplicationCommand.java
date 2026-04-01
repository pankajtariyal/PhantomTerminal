package com.phantomterminal.commands.calculator;

/**
 * The {@code MultiplicationCommand} class represents the calculator
 * command responsible for performing multiplication operations
 * in the PhantomTerminal application.
 *
 * <p>This class extends {@link AbstractCalculatorCommand} and
 * provides the specific operator and command name required
 * for multiplication.</p>
 *
 * <p>The common execution logic such as argument validation,
 * numeric parsing, stack handling, and result output is
 * implemented in the {@link AbstractCalculatorCommand}
 * base class.</p>
 *
 * <h3>Example Usage</h3>
 * <pre>
 * mul 5 4 3
 * </pre>
 *
 * Output:
 * <pre>
 * 60
 * </pre>
 *
 * <p>The command expects operands to be provided as
 * space-separated numeric values.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class MultiplicationCommand extends AbstractCalculatorCommand {

    /**
     * Returns the multiplication operator used by this command.
     *
     * @return the multiplication operator "*"
     */
    @Override
    protected String getOperator() {
        return "*";
    }

    /**
     * Returns the command name used in the terminal.
     *
     * <p>This name is used in error and warning messages
     * generated during command execution.</p>
     *
     * @return the command name "mul"
     */
    @Override
    protected String getCommandName() {
        return "mul";
    }
}
