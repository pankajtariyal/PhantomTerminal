package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LsCommand implements Command {
    private boolean showHidden = false;
    private boolean isLongFormate = false;
    private String givenPath = "";
    @Override
    public void execute(List<String> args) throws IOException {

        System.out.println(args);
        for(int i=1;i<args.size();i++){
            System.out.println("outer loop");
            String arg = args.get(i);
            if(arg.startsWith("-")){
                for(int j = 1; j<arg.length(); j++){
                    char flag = arg.charAt(j);
                    switch (flag){
                        case 'a':
                            showHidden = true;
                            break;
                        case 'l':
                            isLongFormate = true;
                            break;
                        default:
                            CommonVariable.outputAreaCommon.appendText("> Unknown Flag\n");
                            break;
                    }
                }
            }else{
                givenPath = arg;
            }
        }

        if(!givenPath.isEmpty()){
            System.out.println("given path is empty");
            listFile(givenPath);
        }else{
            listFile(CommonVariable.currentPath);
        }
    }

    public void listFile(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        try(Stream<Path> list = Files.list(path);){
            list.filter(p-> showHidden || !p.toFile().isHidden())
                    .forEach(file->CommonVariable.outputAreaCommon.appendText("-"+file.getFileName()+"\n"));
        }
    }
//
//    public void listAllWithHiddenFile(String directoryPath) throws IOException {
//        Path path = Paths.get(directoryPath);
//        try(Stream<Path> list = Files.list(path);){
//            list.forEach(file->CommonVariable.outputAreaCommon.appendText("-"+file.getFileName()+"\n"));
//        }
//    }
}
