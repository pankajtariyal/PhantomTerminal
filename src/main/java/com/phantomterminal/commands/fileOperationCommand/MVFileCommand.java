package com.phantomterminal.commands.fileOperationCommand;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Implementation of the {@code mv} command for PhantomTerminal.
 *
 * <p>This command is used to move files or directories from one location
 * to another, similar to the Unix/Linux {@code mv} command.</p>
 *
 * <p>Supported operations:</p>
 * <ul>
 *     <li>Move a single file to a target directory</li>
 *     <li>Move multiple files into a target directory</li>
 *     <li>Handles both absolute and relative paths</li>
 * </ul>
 *
 * <p>Examples:</p>
 * <pre>
 * mv file.txt folder/
 * mv file1.txt file2.txt folder/
 * </pre>
 *
 * <p>If the destination file already exists, it will be replaced
 * when multiple files are moved.</p>
 *
 * @author Abhishek Tadiwal
 */
public class MVFileCommand implements FileCommand {

    /**
     * Executes the mv command.
     *
     * <p>The command expects at least two arguments:</p>
     * <ul>
     *     <li>Source file(s)</li>
     *     <li>Destination directory</li>
     * </ul>
     *
     * <p>If multiple source files are provided, they will all be moved
     * into the destination directory.</p>
     *
     * @param args list of command arguments where:
     *             <ul>
     *                 <li>{@code args[0]} = command name (mv)</li>
     *                 <li>{@code args[1..n-2]} = source files</li>
     *                 <li>{@code args[n-1]} = destination directory</li>
     *             </ul>
     *
     * @throws IOException if a file operation fails during moving
     */
    @Override
    public void execute(List<String> args) throws IOException {

        // Check if enough arguments are provided
        if (args.size() < 3) {
            CommonVariable.outputAreaCommon.appendText("mv: missing operand\n");
            return;
        }

        // Destination directory path
        String directoryToMoveInto = args.get(args.size() - 1);
        Path dirPath = Paths.get(directoryToMoveInto);

        // Convert relative path to absolute path
        if (!dirPath.isAbsolute()) {
            dirPath = Paths.get(CommonVariable.currentPath + File.separator + directoryToMoveInto);
        }

        /*
         * Case 1: Multiple source files
         * mv file1 file2 file3 directory
         */
        if (args.size() > 3) {

            for (int i = 1; i < args.size() - 1; i++) {

                Path source = Paths.get(args.get(i));

                // Convert relative path to absolute path
                if (!source.isAbsolute()) {
                    source = Paths.get(CommonVariable.currentPath + File.separator + args.get(i));
                }

                // Validate source and destination
                if (!Files.exists(source) || !Files.isDirectory(dirPath)) {
                    CommonVariable.outputAreaCommon.appendText(
                            "mv: cannot stat '" + source.getFileName() + "'\n"
                    );
                    continue;
                }

                // Resolve target path inside destination directory
                Path path = dirPath.resolve(source.getFileName());

                // Move file
                Files.move(path, path, StandardCopyOption.REPLACE_EXISTING);
            }

        } else {

            /*
             * Case 2: Single file move
             * mv file.txt directory/
             */

            Path source = Paths.get(args.get(1));

            // Convert relative path to absolute path
            if (!source.isAbsolute()) {
                source = Paths.get(CommonVariable.currentPath, args.get(1));
            }

            // Check if source exists
            if (!Files.exists(source)) {
                CommonVariable.outputAreaCommon.appendText(
                        "mv: cannot stat '" + source.getFileName() + "'\n"
                );
                return;
            }

            // Target location
            Path target = dirPath.resolve(source.getFileName());

            // Move the file
            Files.move(source, target);
        }
    }
}
