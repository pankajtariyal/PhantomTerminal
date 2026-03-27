package com.phantomterminal.uicontroller;

import com.phantomterminal.common.CommonVariable;
import com.phantomterminal.engine.CommandEngine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The {@code TerminalController} class acts as the JavaFX controller
 * for the PhantomTerminal user interface.
 *
 * <p>It manages user interaction with the terminal UI, including
 * receiving commands from the input field, sending them to the
 * {@link CommandEngine} for execution, and displaying output
 * in the terminal output area.</p>
 *
 * <h3>Main Responsibilities</h3>
 * <ul>
 *     <li>Handle user command input</li>
 *     <li>Display command output in the terminal area</li>
 *     <li>Initialize terminal environment</li>
 *     <li>Maintain the current working directory</li>
 * </ul>
 *
 * <h3>Execution Flow</h3>
 * <ol>
 *     <li>User enters a command in the input field.</li>
 *     <li>The {@link #handleCommand()} method is triggered.</li>
 *     <li>The command is sent to {@link CommandEngine#executeCommand(String)}.</li>
 *     <li>The command executes and updates the terminal output.</li>
 * </ol>
 *
 * <p>The controller also initializes shared variables such as
 * the current working directory and the output area through
 * {@link CommonVariable}.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class TerminalController implements Initializable {

    /**
     * Text area used to display terminal output and command results.
     */
    @FXML
    private TextArea outputArea;

    /**
     * Input field where users type commands.
     */
    @FXML
    private TextField inputField;

    /**
     * Handles the execution of commands entered by the user.
     *
     * <p>This method performs the following operations:</p>
     * <ul>
     *     <li>Reads the command from the input field</li>
     *     <li>Displays the command in the output area</li>
     *     <li>Passes the command to {@link CommandEngine}</li>
     *     <li>Clears the input field for the next command</li>
     * </ul>
     *
     * @throws IOException if an I/O error occurs during command execution
     */
    @FXML
    private void handleCommand() throws IOException {

        String command = inputField.getText().trim();

        // Ignore empty commands
        if (command.isEmpty()) return;

        // Display entered command in terminal
        outputArea.appendText(CommonVariable.currentPath + "> " + command + "\n");

        // Execute command using command engine
        CommandEngine.executeCommand(command);

        // Display prompt again
        outputArea.appendText(CommonVariable.currentPath + "> " + command + "\n");

        // Clear input field
        inputField.clear();
    }

    /**
     * Initializes the terminal when the JavaFX UI loads.
     *
     * <p>This method sets up the terminal environment by:</p>
     * <ul>
     *     <li>Setting the initial working directory</li>
     *     <li>Linking the output area with {@link CommonVariable}</li>
     *     <li>Displaying the initial terminal prompt</li>
     * </ul>
     *
     * @param location  location used to resolve relative paths for the root object
     * @param resources resources used to localize the root object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set initial working directory
        CommonVariable.currentPath = System.getProperty("user.dir");

        // Share output area across commands
        CommonVariable.outputAreaCommon = outputArea;

        // Show initial terminal prompt
        outputArea.appendText(CommonVariable.currentPath + ">\n");
    }
}
