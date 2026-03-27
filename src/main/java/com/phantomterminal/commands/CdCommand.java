package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The {@code CdCommand} class implements the {@link Command} interface
 * and provides functionality similar to the Linux <b>cd</b> command.
 *
 * <p>This command is used to change the current working directory
 * of the PhantomTerminal application.</p>
 *
 * <p>The command supports both:</p>
 * <ul>
 *     <li>Absolute paths (e.g., /home/user/docs)</li>
 *     <li>Relative paths (e.g., ../folder or folderName)</li>
 * </ul>
 *
 * <p>The current directory path is stored in
 * {@link CommonVariable#currentPath} and updated when a valid
 * directory is provided.</p>
 *
 * <h3>Example Usage</h3>
 * <pre>
 * cd Documents
 * cd ../Downloads
 * cd /home/user/Desktop
 * </pre>
 *
 * <h3>Error Handling</h3>
 * <ul>
 *     <li>If no directory is provided → "cd: missing operand"</li>
 *     <li>If directory does not exist → "cd: directory not exits"</li>
 *     <li>If path is not a directory → "cd: not a directory"</li>
 * </ul>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class CdCommand implements Command {

    /**
     * Executes the cd command to change the current working directory.
     *
     * @param args List of command arguments.
     *             <p>
     *             args[0] → command name ("cd") <br>
     *             args[1] → target directory path
     *             </p>
     */
    @Override
    public void execute(List<String> args) {

        // Check if directory argument is provided
        if(args.size() < 2){
            CommonVariable.outputAreaCommon.appendText("cd: missing operand\n");
            return;
        }

        String filePath = args.get(1);

        // Convert string path to Path object
        Path path = Paths.get(filePath);

        // Handle relative paths
        if(!path.isAbsolute()){
            path = Paths.get(CommonVariable.currentPath + File.separator + filePath);
        }

        // Normalize path to remove ../ or . symbols
        path = path.normalize();

        File file = path.toFile();

        // Check if directory exists
        if(!file.exists()){
            CommonVariable.outputAreaCommon.appendText("cd: directory not exits\n");
            return;
        }

        // Check if the path is a directory
        if(!file.isDirectory()){
            CommonVariable.outputAreaCommon.appendText("cd: not a directory\n");
            return;
        }

        // Update current working directory
        CommonVariable.currentPath = file.getAbsolutePath();
    }
}
