package com.phantomterminal.commands.fileOperationCommand;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The {@code MkdirFileCommand} class implements the {@link FileCommand} interface
 * and provides functionality similar to the Linux <b>mkdir</b> command.
 *
 * <p>This command creates one or more directories in the current
 * working directory of the PhantomTerminal.</p>
 *
 * <h3>Features</h3>
 * <ul>
 *     <li>Create single directory</li>
 *     <li>Create multiple directories in one command</li>
 *     <li>Supports relative paths</li>
 * </ul>
 *
 * <h3>Examples</h3>
 * <pre>
 * mkdir test
 * mkdir dir1 dir2 dir3
 * mkdir projects/java
 * </pre>
 *
 * <p>If a directory already exists, an error message is displayed.</p>
 *
 * <p>The base path for directory creation is taken from
 * {@link CommonVariable#currentPath}.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class MkdirFileCommand implements FileCommand {

    /**
     * Executes the mkdir command to create directories.
     *
     * @param args command arguments where:
     *             <ul>
     *                 <li>args[0] → command name ("mkdir")</li>
     *                 <li>args[1..n] → directory names</li>
     *             </ul>
     */
    @Override
    public void execute(List<String> args) {

        // Check if directory name is provided
        if(args.size() < 2){
            CommonVariable.outputAreaCommon.appendText("mkdir: missing operand\n");
            return;
        }

        String directory = "";

        // Iterate through all provided directory names
        for(int i = 1; i < args.size(); i++){

            directory = args.get(i);

            // Create path object
            Path path = Paths.get(directory);

            // If path is relative, attach current working directory
            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath, directory);
            }

            File file = path.toFile();

            // Check if directory already exists
            if(file.exists() && file.isDirectory()){
                CommonVariable.outputAreaCommon.appendText(directory + " directory is already exits\n");
                return;
            }

            // Create directory (including parent directories if needed)
            boolean mkdir = file.mkdirs();

            if(!mkdir){
                CommonVariable.outputAreaCommon.appendText("Failed to create " + directory + " Directory\n");
                return;
            } else {
                System.out.println("created successfully directory of: " + directory);
            }
        }
    }
}
