package com.phantomterminal.uicontroller.Common;

import java.io.*;
import java.util.List;

/**
 * Utility class that provides common helper methods
 * for command parsing and file handling in the Phantom Terminal.
 * <p>
 * This class contains reusable logic for validating commands
 * and constructing file paths based on user input.
 * </p>
 *
 * <p>
 * Currently supports parsing of two-word commands such as:
 * <ul>
 *     <li>mkdir folderName</li>
 *     <li>touch fileName</li>
 *     <li>rmdir folderName</li>
 * </ul>
 * </p>
 *
 * <p><b>Note:</b> This class relies on {@link CommonVariable}
 * for accessing the current working directory and output area.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class CommonMethods {

    /**
     * Parses a two-word command and returns a {@link File} object
     * representing the target file or directory.
     * <p>
     * This method validates:
     * <ul>
     *     <li>Command must contain exactly two parts</li>
     *     <li>Command prefix must match the expected prefix</li>
     * </ul>
     * </p>
     *
     * <p>
     * If validation fails, an error message is displayed
     * in the output area and an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param command the full command entered by the user (e.g., "mkdir test")
     * @param prefixCmd the expected command prefix (e.g., "mkdir", "touch")
     * @return a File object representing the constructed path
     *
     * @throws IllegalArgumentException if the command format is invalid
     *                                  or prefix does not match
     */
    public static File twoWordCommand(String command, String prefixCmd) {

        String currentPath = CommonVariable.currentPath;
        List<String> args = CommandParser.parseCommand(command);
        if (args.size() != 2) {
            CommonVariable.outputAreaCommon.appendText(">> unknown command --help cmd for help\n");
            throw new IllegalArgumentException("unknown command");
        }

        if (!prefixCmd.equals(args.get(0))) {
            CommonVariable.outputAreaCommon.appendText(">> unknown command --help cmd for help\n");
            throw new IllegalArgumentException("command prefix don't match");
        }

        String dirName = args.get(1);
        CommonVariable.currentDirName = dirName;

        String tempPath = currentPath + File.separator + dirName;
        return new File(tempPath);
    }

    public static void readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line=reader.readLine())!=null){
            CommonVariable.outputAreaCommon.appendText(line + "\n");
        }
        reader.close();
    }
}
