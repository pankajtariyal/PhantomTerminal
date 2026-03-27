package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MkdirCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if(args.size()<2){
            CommonVariable.outputAreaCommon.appendText("mkdir: missing operand\n");
            return;
        }
        String directory = "";
        for(int i=1;i<args.size();i++){
            directory = args.get(i);
            String directoryPath = CommonVariable.currentPath + File.separator + directory;
            Path path = Paths.get(directory);
            File file = path.toFile();
            // If relative path → append currentPath
            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath, directory);
            }
            if(file.exists() && file.isDirectory()){
                CommonVariable.outputAreaCommon.appendText(directory+" directory is already exits\n");
                return;
            }

            boolean mkdir = file.mkdirs();
            if(!mkdir){
                CommonVariable.outputAreaCommon.appendText("Failed to create "+ directory + " Directory\n");
                return;
            }else {
                System.out.println("created successfully directory of: " + directory);
            }
        }

    }
}
