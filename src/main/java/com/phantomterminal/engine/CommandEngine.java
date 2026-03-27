package com.phantomterminal.engine;

import com.phantomterminal.commands.*;
import com.phantomterminal.common.CommonVariable;

import java.io.IOException;
import java.util.List;

public class CommandEngine {

    public static void executeCommand(String input) throws IOException {
        List<String> inputSplit = CommandParser.parseCommand(input);
        System.out.println("command "+inputSplit);
        if(inputSplit.isEmpty()){
            CommonVariable.outputAreaCommon.appendText(">>\n");
            return;
        }
        String commandName = inputSplit.get(0);
        System.out.println(commandName+ "name");
        Command command = CommandRegistry.getCommand(commandName);
        if(command == null){
            CommonVariable.outputAreaCommon.appendText(">> Unknown command: " + commandName + "\n");
            return;
        }
        command.execute(inputSplit);
    }
}
