package com.phantomterminal.uicontroller.Common;

import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Implementation of {@link UtilFactory} that provides
 * file system operations for the Phantom Terminal.
 * <p>
 * This class handles commands such as directory navigation,
 * file/directory creation and deletion, and listing contents
 * of directories. It interacts with the UI through {@link TextArea}
 * and uses {@link CommonVariable} to maintain the current state.
 * </p>
 *
 * <p>
 * Commands supported include:
 * <ul>
 *     <li>ls - list files</li>
 *     <li>ls -a - list hidden files</li>
 *     <li>mkdir - create directory</li>
 *     <li>rmdir - delete directory</li>
 *     <li>cd - change directory</li>
 *     <li>cd .. - move to parent directory</li>
 *     <li>touch - create file</li>
 *     <li>ioexits - check file existence</li>
 * </ul>
 * </p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class Utils implements UtilFactory {

    /**
     * Displays all available commands and their descriptions
     * in the output area.
     *
     * @param outputArea the TextArea where help content is displayed
     */
    @Override
    public void onHelpCommand(TextArea outputArea) {
        outputArea.appendText(">>File Operation:\n");
        outputArea.appendText(">> ls  -- List all file of current directory.\n");
        outputArea.appendText(">> ls -a  -- List all file including hidden file of current directory.\n");
        outputArea.appendText(">> mkdir <directory name>  -- create directory in current path.\n");
        outputArea.appendText(">> rmdir <delete directory or file>  -- delete file or directory in current path.\n");
        outputArea.appendText(">> cd <directory path or path> -- goes into directory from parent to child\n");
        outputArea.appendText(">> cd ..  -- into the parent directory.\n");
        outputArea.appendText(">> ioexits <File path> File exits -- Check for file exits or not.\n");
        outputArea.appendText(">> touch <file name> -- create file in current directory\n");
        outputArea.appendText(">> cat <file name> -- read file in current directory\n");
        outputArea.appendText(">> nano <file name> -w <write> -- Write in file in current directory\n");
    }

    /**
     * Lists all non-hidden files and directories in the current directory.
     * Skips system-specific files like "desktop.ini".
     *
     * @param outputArea the TextArea where file names are displayed
     */
    @Override
    public void listAllFileOfDirectory(TextArea outputArea) {
        String currentPath = CommonVariable.currentPath;
        File file = new File(currentPath);

        if (!file.isDirectory()) {
            outputArea.appendText(">> " + currentPath + " is not directory");
        }

        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            outputArea.appendText(">> No files found\n");
            return;
        }

        int count = 0;
        StringBuilder builder = new StringBuilder();

        for (File f : files) {
            if (f.isHidden()) continue;

            String fileName = f.getName().toLowerCase();
            if (fileName.equals("all users") || fileName.equals("default user") || fileName.equals("desktop.ini")) {
                continue;
            }

            builder.append(f.getName()).append("\t\t\t");
            count++;

            if (count == 4) {
                outputArea.appendText(builder.toString() + "\n");
                builder.setLength(0);
                count = 0;
            }
        }

        if (builder.length() > 0) {
            outputArea.appendText(builder.toString() + "\n");
        }
    }

    /**
     * Checks whether a file exists at the specified path.
     *
     * @param outputArea the TextArea where result is displayed
     * @param command the command containing the file path
     */
    @Override
    public void isFileExits(TextArea outputArea, String command) {
        String currentPath = CommonVariable.currentPath;
        String[] splitCommand = command.split(" ");

        if (splitCommand.length != 2) {
            outputArea.appendText(currentPath + ">> unknown command --help cmd for help\n");
            return;
        }

        if (!"ioexits".equals(splitCommand[0]) || splitCommand[1].isEmpty()) {
            outputArea.appendText(currentPath + ">> wrong cmd --help cmd for help\n");
            return;
        }

        File file = new File(splitCommand[1].trim());

        if (file.exists()) {
            outputArea.appendText(currentPath + ">> true\n");
        } else {
            outputArea.appendText(currentPath + ">> false\n");
        }
    }

    /**
     * Changes the current directory to the specified path.
     *
     * @param outputArea the TextArea where result is displayed
     * @param command the command containing directory name
     */
    @Override
    public void intoTheDirectory(TextArea outputArea, String command) {
        String currentPath = CommonVariable.currentPath;
        String[] splitCommand = command.split(" ");

        if (splitCommand.length != 2) {
            outputArea.appendText(currentPath + ">> unknown command --help cmd for help\n");
            return;
        }

        if (!"cd".equals(splitCommand[0]) || splitCommand[1].isEmpty()) {
            outputArea.appendText(currentPath + ">> wrong cmd --help cmd for help\n");
            return;
        }

        String temp = currentPath + File.separator + splitCommand[1];
        File file = new File(temp);

        if (!file.isDirectory()) {
            outputArea.appendText(currentPath + ">> " + temp + " is not a directory\n");
        } else {
            CommonVariable.currentPath = temp;
            outputArea.appendText(temp + ">>\n");
        }
    }

    /**
     * Moves the current directory to its parent directory.
     *
     * @param outputArea the TextArea where result is displayed
     */
    @Override
    public void intoParentDirectory(TextArea outputArea) {
        String currentPath = CommonVariable.currentPath;
        File file = new File(currentPath);

        String parentDirectoryPath = file.getParent();
        File parentDirectory = new File(parentDirectoryPath);

        if (!parentDirectory.isDirectory()) return;

        CommonVariable.currentPath = parentDirectoryPath;
        outputArea.appendText(parentDirectoryPath + ">>\n");
    }

    /**
     * Lists all files including hidden files in the current directory.
     */
    @Override
    public void listAllFileOfDirectoryHidden() {
        String currentPath = CommonVariable.currentPath;
        File file = new File(currentPath);

        if (!file.isDirectory()) {
            CommonVariable.outputAreaCommon.appendText(currentPath + ">> Not a directory");
            return;
        }

        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            CommonVariable.outputAreaCommon.appendText(currentPath + ">> No files found\n");
            return;
        }

        int count = 0;
        StringBuilder builder = new StringBuilder();

        for (File f : files) {
            builder.append(f.getName()).append("\t\t\t");
            count++;

            if (count == 4) {
                CommonVariable.outputAreaCommon.appendText(builder.toString() + "\n");
                builder.setLength(0);
                count = 0;
            }
        }

        if (builder.length() > 0) {
            CommonVariable.outputAreaCommon.appendText(builder.toString() + "\n");
        }
    }

    /**
     * Creates a new directory in the current path.
     *
     * @param command the command containing directory name
     */
    @Override
    public void createDirectory(String command) {
        String[] splitCommand = command.split(" ");
        String prefixCmd = splitCommand[0];

        File file = CommonMethods.twoWordCommand(command, prefixCmd);

        if (file.exists()) {
            CommonVariable.outputAreaCommon.appendText(">> mkdir: directory already exists\n");
            return;
        }

        if (!file.mkdir()) {
            CommonVariable.outputAreaCommon.appendText(">> mkdir: failed to create directory\n");
        }
    }

    /**
     * Deletes a file or directory from the current path.
     *
     * @param command the command containing directory/file name
     */
    @Override
    public void deleteDirectory(String command) {
        String[] splitCommand = command.split(" ");
        String prefixCmd = splitCommand[0];

        File file = CommonMethods.twoWordCommand(command, prefixCmd);
        String dirName = splitCommand[1];

        if (!file.exists()) {
            CommonVariable.outputAreaCommon.appendText(">> rmdir: No such file or directory\n");
            return;
        }

        if (!file.delete()) {
            CommonVariable.outputAreaCommon.appendText(">> rmdir: failed to remove\n");
        }
    }

    /**
     * Creates a new file in the current directory.
     *
     * @param command the command containing file name
     */
    @Override
    public void createFile(String command) {
        String[] splitCommand = command.split(" ");
        String prefixCmd = splitCommand[0];

        File file = CommonMethods.twoWordCommand(command, prefixCmd);
        String currentDir = CommonVariable.currentDirName;

        if (file.exists()) {
            CommonVariable.outputAreaCommon.appendText(">> touch: file already exists\n");
            return;
        }

        try {
            if (!file.createNewFile()) {
                CommonVariable.outputAreaCommon.appendText(">> touch: failed to create file\n");
            }
            CommonVariable.currentDirName = "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
