package com.phantomterminal.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code CommandParser} class is responsible for parsing raw command
 * input entered by the user in the PhantomTerminal.
 *
 * <p>This parser converts the input string into a list of tokens
 * (command name and arguments). It supports both normal words and
 * quoted strings, allowing users to pass arguments containing spaces.</p>
 *
 * <h3>Parsing Rules</h3>
 * <ul>
 *     <li>Words separated by spaces are treated as individual tokens.</li>
 *     <li>Text enclosed in double quotes (<code>" "</code>) is treated as a single token.</li>
 * </ul>
 *
 * <h3>Examples</h3>
 *
 * <pre>
 * Input:  ls -a
 * Output: ["ls", "-a"]
 *
 * Input:  mkdir testFolder
 * Output: ["mkdir", "testFolder"]
 *
 * Input:  touch "my file.txt"
 * Output: ["touch", "my file.txt"]
 *
 * Input:  cat "hello world.txt"
 * Output: ["cat", "hello world.txt"]
 * </pre>
 *
 * <p>The parser uses Java Regular Expressions via {@link Pattern}
 * and {@link Matcher} to identify tokens in the command string.</p>
 *
 * @author Abhishek
 * @version 1.0
 */
public class CommandParser {

    /**
     * Parses the input command string into individual tokens.
     *
     * <p>This method supports both normal space-separated words
     * and quoted arguments that may contain spaces.</p>
     *
     * @param input the raw command string entered by the user
     * @return a list of tokens where:
     *         <ul>
     *             <li>index 0 → command name</li>
     *             <li>index 1..n → command arguments</li>
     *         </ul>
     */
    public static List<String> parseCommand(String input) {

        List<String> tokens = new ArrayList<>();

        // Regex pattern that matches words or quoted strings
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(input);

        while (matcher.find()) {

            // Group 1 → quoted text
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1));
            }
            // Group 2 → normal words
            else {
                tokens.add(matcher.group(2));
            }
        }

        return tokens;
    }
}
