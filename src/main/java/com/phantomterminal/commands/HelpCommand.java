package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.IOException;
import java.util.List;

public class HelpCommand implements Command{
    @Override
    public void execute(List<String> args) throws IOException {
        CommonVariable.outputAreaCommon.appendText(">File Operation:\n");
        CommonVariable.outputAreaCommon.appendText("> ls  -- List all file of current directory.\n");
        CommonVariable.outputAreaCommon.appendText("> ls <path> -- List all file of given path.\n");
        CommonVariable.outputAreaCommon.appendText("> ls -a  -- List all file including hidden file of current directory.\n");
        CommonVariable.outputAreaCommon.appendText("> mkdir <directory name[]>  -- create directory in current path.\n");
        CommonVariable.outputAreaCommon.appendText("> rmdir <delete directory or file>  -- delete file or directory in current path.\n");
        CommonVariable.outputAreaCommon.appendText("> cd <directory path (absolute or relative)> -- move to child directory from parent directory\n");
        CommonVariable.outputAreaCommon.appendText("> cd ..  -- move to parent directory.\n");
        CommonVariable.outputAreaCommon.appendText("> ioexits <File path> File exits -- Check for file exits or not.\n");
        CommonVariable.outputAreaCommon.appendText("> touch <file name[]> -- create file in current directory\n");
        CommonVariable.outputAreaCommon.appendText("> cat <file name> -- read file in current directory\n");
    }
}
