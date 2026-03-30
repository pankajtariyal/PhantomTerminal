package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class MVCommand implements Command{
    @Override
    public void execute(List<String> args) throws IOException {
        if (args.size() < 3) {
            CommonVariable.outputAreaCommon.appendText("mv: missing operand\n");
            return;
        }

        String directoryToMoveInto = args.get(args.size() - 1);
        Path dirPath = Paths.get(directoryToMoveInto);
        if (!dirPath.isAbsolute()) {
            dirPath = Paths.get(CommonVariable.currentPath + File.separator + directoryToMoveInto);
        }


        if (args.size() > 3) {
            for (int i = 1; i < args.size() - 1; i++) {
                Path source = Paths.get(args.get(i));
                if (!source.isAbsolute()) {
                    source = Paths.get(CommonVariable.currentPath + File.separator + args.get(i));
                }
                if (!Files.exists(source) || !Files.isDirectory(dirPath)) {
                    CommonVariable.outputAreaCommon.appendText("mv: cannot stat '" + source.getFileName() + "'\n");
                    continue;
                }

                Path path = dirPath.resolve(source.getFileName());
                Files.move(path, dirPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }else {
//            Path source = Paths.get(args.get(1));
//            if(!source.isAbsolute()){
//                source = Paths.get(CommonVariable.currentPath, args.get(1));
//            }
//
//            if(!Files.exists(source)){
//                CommonVariable.outputAreaCommon.appendText("mv: cannot stat '" + source.getFileName() + "'\n");
//                return;
//            }
//
//            File file = dirPath.toFile();
//            if(file.exists()){
//                if(file.isDirectory()){
//                    Files.move(source, dirPath);
//                }else {
//                    Files.move(source,dirPath,StandardCopyOption.REPLACE_EXISTING);
//                }
//            }else{
//                Files.
//            }

            Path source = Paths.get(args.get(1));

            if(!source.isAbsolute()){
                source = Paths.get(CommonVariable.currentPath, args.get(1));
            }

            if(!Files.exists(source)){
                CommonVariable.outputAreaCommon.appendText("mv: cannot stat '" + source.getFileName() + "'\n");
                return;
            }
            Path target = dirPath.resolve(source.getFileName());

            Files.move(source, target);

        }
    }
}
