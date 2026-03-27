package com.phantomterminal.engine;

import com.phantomterminal.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("ls", new LsCommand());
        commands.put("cd", new CdCommand());
        commands.put("mkdir", new MkdirCommand());
        commands.put("rmdir", new RmdirCommand());
        commands.put("touch", new TouchCommand());
        commands.put("clear",new ClearCommand());
        commands.put("help", new HelpCommand());
        commands.put("cat", new CatCommand());
    }

    public static Command getCommand(String name){
        System.out.println(name);
        System.out.println(commands);
        return commands.get(name);
    }
}
