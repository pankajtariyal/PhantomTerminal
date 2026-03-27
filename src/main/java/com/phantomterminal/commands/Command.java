package com.phantomterminal.commands;

import java.io.IOException;
import java.util.List;

public interface Command {
    public void execute(List<String>args) throws IOException;
}
