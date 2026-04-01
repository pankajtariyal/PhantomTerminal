package com.phantomterminal.commands.calculator;

/**
 * The {@code AddCommand} class represents the calculator command
 * responsible for performing addition operations in the
 * PhantomTerminal application.
 *
 * <p>This class extends {@link AbstractCalculatorCommand} and provides
 * the specific behavior required for the addition operation.</p>
 *
 * <p>The common execution workflow such as argument validation,
 * number parsing, stack processing, and result output is handled
 * by the {@link AbstractCalculatorCommand} base class.</p>
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
 * <p>The command expects operands to be provided as
 * space-separated numeric values.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class AddCommand extends AbstractCalculatorCommand {

    /**
     * Returns the operator used for the addition operation.
     *
     * @return the addition operator "+"
     */
    @Override
    protected String getOperator() {
        return "+";
    }

    /**
     * Returns the name of the command.
     *
     * <p>This name is used in error and warning messages
     * generated during command execution.</p>
     *
     * @return the command name "add"
     */
    @Override
    protected String getCommandName() {
        return "add";
    }
}
