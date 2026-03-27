package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.IOException;
import java.util.List;

/**
 * The {@code ClearCommand} class implements the {@link Command} interface
 * and provides functionality similar to the Linux <b>clear</b> command.
 *
 * <p>This command clears the terminal output area in the PhantomTerminal
 * application. It removes all previously printed text from the terminal
 * screen.</p>
 *
 * <h3>Example Usage</h3>
 * <pre>
 * clear
 * </pre>
 *
 * <p>After executing this command, the terminal display becomes empty.</p>
 *
 * <h3>Internal Behavior</h3>
 * <ul>
 *     <li>Accesses the shared terminal output component stored in
 *     {@link CommonVariable#outputAreaCommon}</li>
 *     <li>Calls the {@code clear()} method to remove all text</li>
 * </ul>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class ClearCommand implements Command {

    /**
     * Executes the clear command which removes all text from the terminal output.
     *
     * @param args list of command arguments (not used for this command)
     * @throws IOException if an input/output error occurs
     */
    @Override
    public void execute(List<String> args) throws IOException {
        if(args.size()>1){
            CommonVariable.outputAreaCommon.appendText("Too many argument");
            return;
        }
        CommonVariable.outputAreaCommon.clear();
    }
}
