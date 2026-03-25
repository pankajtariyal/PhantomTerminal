package com.phantomterminal.uicontroller.Common;

import javafx.scene.control.TextArea;

/**
 * Utility interface that defines common file system operations
 * used by the Phantom Terminal UI controller.
 * <p>
 * This interface provides method declarations for handling commands
 * such as navigation, file/directory creation, deletion, and listing.
 * Implementing classes should define the actual behavior of these operations.
 * </p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public interface UtilFactory {

    /**
     * Displays help information for available commands.
     *
     * @param outputArea the TextArea where help content will be displayed
     */
    void onHelpCommand(TextArea outputArea);

    /**
     * Lists all files and directories in the current directory.
     *
     * @param outputArea the TextArea where the file list will be displayed
     */
    void listAllFileOfDirectory(TextArea outputArea);

    /**
     * Lists all hidden files and directories in the current directory.
     */
    void listAllFileOfDirectoryHidden();

    /**
     * Checks whether a file exists based on the given command.
     *
     * @param outputArea the TextArea where the result will be displayed
     * @param command the command containing the file name/path
     */
    void isFileExits(TextArea outputArea, String command);

    /**
     * Navigates into a specified directory.
     *
     * @param outputArea the TextArea where navigation result will be displayed
     * @param command the command containing the target directory
     */
    void intoTheDirectory(TextArea outputArea, String command);

    /**
     * Navigates to the parent directory of the current path.
     *
     * @param outputArea the TextArea where navigation result will be displayed
     */
    void intoParentDirectory(TextArea outputArea);

    /**
     * Creates a new directory.
     *
     * @param command the command containing the directory name
     */
    void createDirectory(String command);

    /**
     * Deletes an existing directory.
     *
     * @param command the command containing the directory name
     */
    void deleteDirectory(String command);

    /**
     * Creates a new file.
     *
     * @param command the command containing the file name
     */
    void createFile(String command);
}

