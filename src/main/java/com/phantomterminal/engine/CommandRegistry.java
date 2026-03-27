package com.phantomterminal.engine;

import com.phantomterminal.commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CommandRegistry} class maintains a registry of all
 * available terminal commands in the PhantomTerminal.
 *
 * <p>It maps command names (such as <b>ls</b>, <b>cd</b>, <b>mkdir</b>)
 * to their corresponding {@link Command} implementations.</p>
 *
 * <p>This registry allows the {@link CommandEngine} to dynamically
 * retrieve and execute commands based on the user input.</p>
 *
 * <h3>Architecture Role</h3>
 * <ul>
 *     <li>Stores all supported commands in a {@link Map}</li>
 *     <li>Acts as a lookup table for command execution</li>
 *     <li>Supports easy addition of new commands</li>
 * </ul>
 *
 * <h3>Example</h3>
 * <pre>
 * User Input: ls -a
 *
 * CommandEngine → asks CommandRegistry for "ls"
 * CommandRegistry → returns LsCommand instance
 * CommandEngine → executes LsCommand.execute()
 * </pre>
 *
 * <h3>Registered Commands</h3>
 * <ul>
 *     <li>ls → {@link LsCommand}</li>
 *     <li>cd → {@link CdCommand}</li>
 *     <li>mkdir → {@link MkdirCommand}</li>
 *     <li>rmdir → {@link RmdirCommand}</li>
 *     <li>touch → {@link TouchCommand}</li>
 *     <li>clear → {@link ClearCommand}</li>
 *     <li>help → {@link HelpCommand}</li>
 *     <li>cat → {@link CatCommand}</li>
 * </ul>
 *
 * <p>The commands are initialized in a static block so that they are
 * available when the application starts.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class CommandRegistry {

    /**
     * Stores the mapping between command names and their implementations.
     */
    private static final Map<String, Command> commands = new HashMap<>();

    /**
     * Static initialization block used to register all available commands.
     * This block executes once when the class is loaded.
     */
    static {
        commands.put("ls", new LsCommand());
        commands.put("cd", new CdCommand());
        commands.put("mkdir", new MkdirCommand());
        commands.put("rmdir", new RmdirCommand());
        commands.put("touch", new TouchCommand());
        commands.put("clear", new ClearCommand());
        commands.put("help", new HelpCommand());
        commands.put("cat", new CatCommand());
    }

    /**
     * Retrieves a command implementation based on the command name.
     *
     * @param name the command name entered by the user
     * @return the corresponding {@link Command} implementation,
     *         or {@code null} if the command is not registered
     */
    public static Command getCommand(String name){
        System.out.println(name);
        System.out.println(commands);
        return commands.get(name);
    }
}
