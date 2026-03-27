package com.phantomterminal.commands;

import java.io.IOException;
import java.util.List;

/**
 * The {@code Command} interface represents a generic command
 * in the PhantomTerminal application.
 *
 * <p>All terminal commands such as <b>cd, ls, mkdir, touch, cat, clear</b>
 * implement this interface. It defines a common contract that every
 * command must follow.</p>
 *
 * <p>Each command class must provide its own implementation of the
 * {@link #execute(List)} method, which contains the logic required
 * to perform the specific command operation.</p>
 *
 * <h3>Example</h3>
 * <pre>
 * public class ClearCommand implements Command {
 *     public void execute(List<String> args) {
 *         // command logic
 *     }
 * }
 * </pre>
 *
 * <p>This design follows the <b>Command Design Pattern</b>, where
 * each command is encapsulated as a separate class.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public interface Command {

    /**
     * Executes the command with the provided arguments.
     *
     * @param args a list of command arguments where:
     *             <ul>
     *                 <li>args[0] represents the command name</li>
     *                 <li>args[1..n] represent command parameters</li>
     *             </ul>
     *
     * @throws IOException if an input/output error occurs during execution
     */
    void execute(List<String> args) throws IOException;
}
