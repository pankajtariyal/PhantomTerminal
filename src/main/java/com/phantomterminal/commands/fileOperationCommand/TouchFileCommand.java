package com.phantomterminal.commands.fileOperationCommand;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The {@code TouchFileCommand} class implements the {@link FileCommand} interface
 * and provides functionality similar to the Linux <b>touch</b> command.
 *
 * <p>This command creates one or more empty files in the file system.
 * If the file path provided is relative, it will be created inside
 * the current working directory stored in
 * {@link CommonVariable#currentPath}.</p>
 *
 * <h3>Features</h3>
 * <ul>
 *     <li>Create single file</li>
 *     <li>Create multiple files in one command</li>
 *     <li>Supports both absolute and relative file paths</li>
 * </ul>
 *
 * <h3>Examples</h3>
 * <pre>
 * touch file1.txt
 * touch file1.txt file2.txt file3.txt
 * touch /home/user/test.txt
 * </pre>
 *
 * <p>If a file already exists, the command will display a message
 * and continue processing the remaining files.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class TouchFileCommand implements FileCommand {

    /**
     * Executes the touch command to create new files.
     *
     * @param args command arguments where:
     *             <ul>
     *                 <li>args[0] → command name ("touch")</li>
     *                 <li>args[1..n] → file names or paths</li>
     *             </ul>
     *
     * @throws IOException if an I/O error occurs while creating the file
     */
    @Override
    public void execute(List<String> args) throws IOException {

        // Check if file name is provided
        if(args.size() < 2){
            CommonVariable.outputAreaCommon.appendText("touch: missing operand\n");
            return;
        }

        // Iterate through all provided file names
        for(int i = 1; i < args.size(); i++){

            String fileName = args.get(i);

            // Convert string to Path object
            Path path = Paths.get(fileName);

            // If path is relative, attach current working directory
            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath, fileName);
            }

            File file = path.toFile();

            // Check if file already exists
            if(file.exists()){
                boolean boolModified = file.setLastModified(System.currentTimeMillis());
                continue;
            }

            // Create new file
            boolean newFile = file.createNewFile();

            if(newFile){
                System.out.println(fileName + " created successfully");
            } else {
                CommonVariable.outputAreaCommon.appendText("Failed to create file: " + fileName + "\n");
            }
        }
    }
}
