package com.phantomterminal.commands.fileOperationCommand;

import com.phantomterminal.common.CommonVariable;

import java.io.IOException;
import java.util.List;

/**
 * The HelpFileCommand class provides a list of all supported commands
 * available in the PhantomTerminal application.
 *
 * <p>This command prints a help guide describing the syntax and
 * purpose of each command supported by the terminal.</p>
 *
 * <p>Category: File and Directory Operations</p>
 *
 * Supported commands include:
 * <ul>
 *     <li>ls - List files in a directory</li>
 *     <li>mkdir - Create a directory</li>
 *     <li>rmdir - Remove a file or directory</li>
 *     <li>cd - Change directory</li>
 *     <li>touch - Create a new file</li>
 *     <li>cat - Display file contents</li>
 *     <li>ioexists - Check if a file exists</li>
 * </ul>
 *
 * This command prints the help information to the terminal output area.
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class HelpFileCommand implements FileCommand {

    /**
     * Executes the help command.
     *
     * <p>This method prints a list of available terminal commands
     * along with their descriptions and usage syntax.</p>
     *
     * @param args list of arguments passed with the help command (not used)
     * @throws IOException if an I/O error occurs while executing the command
     */
    @Override
    public void execute(List<String> args) throws IOException {

        CommonVariable.outputAreaCommon.appendText("> File Operations:\n");

        CommonVariable.outputAreaCommon.appendText(
                "> ls  - Lists all files and directories in the current directory.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> ls <path> - Lists files and directories of the specified path.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> ls -a  - Lists all files including hidden files in the current directory.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> mkdir <directory_name>  - Creates a new directory in the current path.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> rmdir <name>  - Deletes a specified file or directory from the current path.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> cd <path> - Changes the current directory to the specified path (absolute or relative).\n");

        CommonVariable.outputAreaCommon.appendText(
                "> cd ..  - Moves to the parent directory.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> ioexists <file_path> - Checks whether the specified file exists.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> touch <file_name> - Creates a new empty file in the current directory.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> cat <file_name> - Displays the content of a file.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> mv <file_name_Old> <newFileName> - Rename a file.\n");

        CommonVariable.outputAreaCommon.appendText(
                "> Calculation Operations:\n");

        CommonVariable.outputAreaCommon.appendText(
                "> add num1 num2 .... Operations: addition\n");

        CommonVariable.outputAreaCommon.appendText(
                "> sub num1 num2 .... Operations: subtraction\n");

        CommonVariable.outputAreaCommon.appendText(
                "> mul num1 num2 .... Operations: multiplication\n");

        CommonVariable.outputAreaCommon.appendText(
                "> div num1 num2 .... Operations: divide\n");

        CommonVariable.outputAreaCommon.appendText(
                "> cal num1 expression num2 .... Operations: for all operation in one\n");

    }
}
