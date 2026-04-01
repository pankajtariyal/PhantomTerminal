package com.phantomterminal.commands.calculator;

import com.phantomterminal.commands.Command;
import com.phantomterminal.common.calculatorUtil.CalculatorUtil;

import java.io.IOException;
import java.util.List;

/**
 * The {@code CalculatorCommand} interface defines the contract for
 * all calculator-related commands in the PhantomTerminal application.
 *
 * <p>Classes implementing this interface provide functionality for
 * performing mathematical operations such as addition, subtraction,
 * multiplication, division, or evaluating complex expressions.</p>
 *
 * <p>Each calculator command must implement the {@link #execute(List)}
 * method, which contains the logic for processing the provided
 * arguments and executing the required calculation.</p>
 *
 * <p>This interface follows the <b>Command Design Pattern</b>, where
 * each calculator operation is encapsulated within its own class.</p>
 *
 * <h3>Example Implementation</h3>
 * <pre>
 * public class AddCommand implements CalculatorCommand {
 *
 *     @Override
 *     public void execute(List<String> args) throws IOException {
 *         // Perform addition logic
 *     }
 * }
 * </pre>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
@FunctionalInterface
public interface CalculatorCommand extends Command {

    /**
     * Executes the calculator command using the provided arguments.
     *
     * <p>The argument list generally follows this structure:</p>
     * <ul>
     *     <li>{@code args[0]} – command name</li>
     *     <li>{@code args[1..n]} – operands or expression parameters</li>
     * </ul>
     *
     * @param args list of arguments used for performing the calculation
     *
     * @throws IOException if an error occurs during command execution
     */
    void execute(List<String> args) throws IOException;
}
