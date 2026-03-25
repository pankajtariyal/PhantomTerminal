package com.phantomterminal.uicontroller.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    public static List<String> parseCommand(String input) {
        List<String> tokens = new ArrayList<>();

        // Matches words OR quoted strings
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1)); // quoted text
            } else {
                tokens.add(matcher.group(2)); // normal word
            }
        }

        return tokens;
    }
}
