package com.phantomterminal.commands;

import java.io.IOException;
import java.util.List;

/**
 * Represents a command that can be executed in PhantomTerminal.
 *
 * <p>This interface defines the contract for all terminal commands.
 * Any class implementing this interface must provide the logic
 * required to execute the command.</p>
 *
 * <p>Commands receive arguments from the terminal input as a list
 * of strings and perform the corresponding operation.</p>
 *
 * <p>Example commands may include:</p>
 * <ul>
 *     <li>Calculator commands</li>
 *     <li>File system commands</li>
 *     <li>Network commands</li>
 *     <li>System utility commands</li>
 * </ul>
 *
 * <p>Each command implementation should handle its own argument
 * validation and error handling.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public interface Command {

    /**
     * Executes the command with the given arguments.
     *
     * <p>The arguments are parsed from the user input in the terminal.
     * Implementations should process these arguments and perform the
     * appropriate action.</p>
     *
     * @param args list of arguments passed to the command
     * @throws IOException if an input/output error occurs during execution
     */
    void execute(List<String> args) throws IOException;
}
