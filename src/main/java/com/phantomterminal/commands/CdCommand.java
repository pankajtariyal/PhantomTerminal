package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CdCommand implements Command {
    @Override
    public void execute(List<String> args) {

        if(args.size()<2){
            CommonVariable.outputAreaCommon.appendText("cd: missing operand\n");
            return;
        }
        String filePath = args.get(1);
        Path path = Paths.get(filePath);
        if(!path.isAbsolute()){
            path = Paths.get(CommonVariable.currentPath+ File.separator+filePath);
        }
        path = path.normalize();
        File file = path.toFile();
        if(!file.exists()){
            CommonVariable.outputAreaCommon.appendText("cd: directory not exits\n");
            return;
        }

        if(!file.isDirectory()){
            CommonVariable.outputAreaCommon.appendText("cd: not a directory\n");
            return;
        }
        CommonVariable.currentPath = file.getAbsolutePath();
    }
}
