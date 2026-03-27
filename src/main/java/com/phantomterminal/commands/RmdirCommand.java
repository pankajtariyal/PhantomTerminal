package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * The {@code RmdirCommand} class implements the {@link Command} interface
 * and provides functionality similar to the Linux <b>rmdir</b> command.
 *
 * <p>This command removes one or more directories from the file system.
 * The directories must exist and typically must be empty for successful deletion.</p>
 *
 * <h3>Features</h3>
 * <ul>
 *     <li>Delete one or multiple directories</li>
 *     <li>Supports both absolute and relative paths</li>
 * </ul>
 *
 * <h3>Examples</h3>
 * <pre>
 * rmdir test
 * rmdir dir1 dir2 dir3
 * rmdir /home/user/oldFolder
 * </pre>
 *
 * <p>If the directory does not exist or cannot be deleted,
 * an error message is displayed in the terminal.</p>
 *
 * <p>The current working directory is stored in
 * {@link CommonVariable#currentPath}.</p>
 *
 * @author Abhishek
 * @version 1.0
 */
public class RmdirCommand implements Command {

    /**
     * Executes the rmdir command to delete directories.
     *
     * @param args command arguments where:
     *             <ul>
     *                 <li>args[0] → command name ("rmdir")</li>
     *                 <li>args[1..n] → directory names or paths</li>
     *             </ul>
     *
     * @throws IOException if an input/output error occurs
     */
    @Override
    public void execute(List<String> args) throws IOException {

        // Check if directory name is provided
        if(args.size() < 2){
            CommonVariable.outputAreaCommon.appendText("rmdir: missing operand\n");
            return;
        }

        // Iterate through provided directory names
        for(int i = 1; i < args.size(); i++){

            String fileName = args.get(i);

            // Convert string path to Path object
            Path path = Paths.get(fileName);

            // Handle relative paths
            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath + File.separator + fileName);
            }

            File file = path.toFile();

            // Check if directory exists
            if(!file.exists()){
                CommonVariable.outputAreaCommon.appendText(fileName + " directory does not exits\n");
                continue;
            }

            // Check if it is directory exists
            if(!file.isDirectory()){
                CommonVariable.outputAreaCommon.appendText(fileName + "is not a directory\n");
                continue;
            }
            if(Objects.requireNonNull(file.list()).length != 0){
                CommonVariable.outputAreaCommon.appendText(fileName + " directory is not empty\n");
                continue;
            }


            // Attempt to delete directory
            if(file.delete()){
                System.out.println(fileName + " delete successfully");
            } else {
                CommonVariable.outputAreaCommon.appendText("Failed to delete Directory " + fileName + "\n");
            }
        }
    }
}
