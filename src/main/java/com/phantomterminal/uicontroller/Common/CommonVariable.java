package com.phantomterminal.uicontroller.Common;

import javafx.scene.control.TextArea;

/**
 * A utility class that holds shared global variables
 * used across the Phantom Terminal application.
 * <p>
 * This class acts as a central state holder for maintaining
 * the current working directory, output display component,
 * and temporary directory/file names used during command execution.
 * </p>
 *
 * <p><b>Note:</b> All variables are static, meaning they are shared
 * across the entire application. Proper care should be taken when
 * modifying these values to avoid unintended side effects.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class CommonVariable {

    /**
     * Stores the current working directory path
     * of the terminal.
     */
    public static String currentPath;

    /**
     * Shared TextArea used for displaying output
     * across different utility methods.
     */
    public static TextArea outputAreaCommon;

    /**
     * Temporarily holds the current directory or file name
     * used during command execution (e.g., for file creation).
     */
    public static String currentDirName = "";
}

