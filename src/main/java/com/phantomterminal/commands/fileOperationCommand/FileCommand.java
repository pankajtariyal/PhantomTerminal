package com.phantomterminal.commands.fileOperationCommand;

import com.phantomterminal.commands.Command;

import java.io.IOException;
import java.util.List;

/**
 * The {@code FileCommand} interface represents a contract for all
 * file-related commands in the PhantomTerminal application.
 *
 * <p>Commands such as <b>cd, ls, mkdir, touch, cat, mv, rm</b> implement
 * this interface. Each command class provides its own implementation
 * of the {@link #execute(List)} method, which contains the logic
 * required to perform the specific file operation.</p>
 *
 * <p>This design follows the <b>Command Design Pattern</b>, where
 * each terminal command is encapsulated as a separate class that
 * implements a common interface.</p>
 *
 * <h3>Example Implementation</h3>
 * <pre>
 * public class ClearFileCommand implements FileCommand {
 *     @Override
 *     public void execute(List<String> args) throws IOException {
 *         // command logic
 *     }
 * }
 * </pre>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
@FunctionalInterface
public interface FileCommand extends Command {

    /**
     * Executes the command using the provided arguments.
     *
     * <p>The argument list follows the typical terminal structure:</p>
     * <ul>
     *     <li>{@code args[0]} – the command name</li>
     *     <li>{@code args[1..n]} – command parameters</li>
     * </ul>
     *
     * @param args list of command arguments
     *
     * @throws IOException if an I/O error occurs while executing
     *                     the command
     */
    void execute(List<String> args) throws IOException;
}
