package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RmdirCommand implements Command{
    @Override
    public void execute(List<String> args) throws IOException {
        if(args.size()<2){
            CommonVariable.outputAreaCommon.appendText("rmdir: missing operand\n");
            return;
        }
        for(int i=1;i<args.size();i++){
            String fileName = args.get(i);
            Path path = Paths.get(fileName);
            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath+ File.separator+fileName);
            }
            File file = path.toFile();
            if(!file.exists()){
                CommonVariable.outputAreaCommon.appendText(fileName+" directory does not exits\n");
                return;
            }
            if(file.delete()){
                System.out.println(fileName+ " delete successfuly");
            }else {
                CommonVariable.outputAreaCommon.appendText("Failed to delete Directory "+fileName+"\n");
                return;
            }
        }
    }
}
