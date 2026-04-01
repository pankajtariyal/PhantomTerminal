package com.phantomterminal.engine;

import com.phantomterminal.commands.Command;
import com.phantomterminal.commands.fileOperationCommand.FileCommand;
import com.phantomterminal.commands.fileOperationCommand.LsFileCommand;
import com.phantomterminal.common.CommonVariable;

import java.io.IOException;
import java.util.List;

/**
 * The {@code CommandEngine} class is responsible for executing commands
 * entered by the user in the PhantomTerminal.
 *
 * <p>It acts as the central command processor of the terminal. The engine
 * takes the raw input string entered by the user, parses it into tokens,
 * identifies the command name, retrieves the corresponding command
 * implementation, and executes it.</p>
 *
 * <h3>Execution Flow</h3>
 * <ol>
 *     <li>User enters a command in the terminal.</li>
 *     <li>The input string is parsed using {@link CommandParser#parseCommand(String)}.</li>
 *     <li>The command name is extracted from the parsed input.</li>
 *     <li>The corresponding command implementation is retrieved using
 *         {@link CommandRegistry#getCommand(String)}.</li>
 *     <li>If the command exists, its {@link FileCommand#execute(List)} method is executed.</li>
 *     <li>If the command does not exist, an error message is displayed.</li>
 * </ol>
 *
 * <h3>Example</h3>
 * <pre>
 * Input:  ls -a
 * Parsed: ["ls", "-a"]
 * Action: Executes {@link LsFileCommand}
 * </pre>
 *
 * <p>This class follows a command-based architecture where each command
 * is implemented as a separate class that implements the {@link FileCommand}
 * interface.</p>
 *
 * @author Abhishek
 * @version 1.0
 */
public class CommandEngine {

    /**
     * Parses the user input and executes the corresponding command.
     *
     * <p>This method performs the following steps:</p>
     * <ul>
     *     <li>Parses the input string into tokens</li>
     *     <li>Identifies the command name</li>
     *     <li>Retrieves the command from {@link CommandRegistry}</li>
     *     <li>Executes the command if it exists</li>
     * </ul>
     *
     * @param input the raw command string entered by the user
     * @throws IOException if an I/O error occurs during command execution
     */
    public static void executeCommand(String input) throws IOException {

        // Parse input fileCommand into tokens
        List<String> inputSplit = CommandParser.parseCommand(input);

        System.out.println("fileCommand " + inputSplit);

        // Handle empty fileCommand input
        if(inputSplit.isEmpty()){
            CommonVariable.outputAreaCommon.appendText(">>\n");
            return;
        }

        // Extract fileCommand name
        String commandName = inputSplit.get(0);

        System.out.println(commandName + " name");

        // Retrieve fileCommand implementation from registry
        Command command = CommandRegistry.getCommand(commandName);

        // If fileCommand is not found
        if(command == null){
            CommonVariable.outputAreaCommon.appendText(">> Unknown fileCommand: " + commandName + "\n");
            return;
        }

        // Execute command
        command.execute(inputSplit);
    }
}
