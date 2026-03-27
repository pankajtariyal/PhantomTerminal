package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CatCommand implements Command{
    private boolean createFile = false;

    @Override
    public void execute(List<String> args) throws IOException {
        if(args.size()<2){
            CommonVariable.outputAreaCommon.appendText("cat: missing operand\n");
            return;
        }

        System.out.println("command: "+args);
        if(args.get(1).equals(">")){
            if(args.size()!=3){
                CommonVariable.outputAreaCommon.appendText("Usage cat > filename.\n");
                return;
            }
                String fileName = args.get(2);
                Path path = Paths.get(fileName);
                if(!path.isAbsolute()){
                    path = Paths.get(CommonVariable.currentPath+File.separator+fileName);
                }
                File file = path.toFile();
                if(file.exists()){
                    System.out.println("exists file");
                }else{
                    if(file.createNewFile()){
//                        CommonVariable.outputAreaCommon.appendText(">Write file "+ fileName+" and for exit type esc\n");
                        System.out.println(fileName+" file created successfully");
                    }else {
                        CommonVariable.outputAreaCommon.appendText("Failed to create file "+ fileName+"\n");
                        return;
                    }
                }
            return;
        }
        StringBuilder readValue = new StringBuilder();
        for(int i = 1;i<args.size();i++){
            String fileName = args.get(i);
            Path path = Paths.get(fileName);
            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath+File.separator+fileName);
            }
            File file = path.toFile();
            if(!file.exists()){
                CommonVariable.outputAreaCommon.appendText("file not exists "+ fileName+"\n");
                continue;
            }

            BufferedReader reader=  new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                readValue.append(line).append("\n");
            }

            reader.close();
        }
        CommonVariable.outputAreaCommon.appendText(readValue.toString());
        readValue.setLength(0);
    }
}
