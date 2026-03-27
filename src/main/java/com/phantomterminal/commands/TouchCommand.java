package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TouchCommand implements Command {
    @Override
    public void execute(List<String> args) throws IOException {
        if(args.size()<2){
            CommonVariable.outputAreaCommon.appendText("touch: missing operand\n");
            return;
        }

        for(int i=1;i<args.size();i++){

            String fileName = args.get(i);
            Path path = Paths.get(fileName);
            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath,fileName);
            }
            File file = path.toFile();
            if(file.exists()){
                CommonVariable.outputAreaCommon.appendText("File already exists: " + fileName + "\n");
                continue;
            }
            boolean newFile = file.createNewFile();
            if(newFile){
                System.out.println(fileName+" created successfully");
            }else {
                CommonVariable.outputAreaCommon.appendText("Failed to create file: " + fileName + "\n");
            }
        }
    }
}
