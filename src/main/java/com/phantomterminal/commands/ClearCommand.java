package com.phantomterminal.commands;

import com.phantomterminal.common.CommonVariable;

import java.io.IOException;
import java.util.List;

public class ClearCommand implements Command{
    @Override
    public void execute(List<String> args) throws IOException {
        CommonVariable.outputAreaCommon.clear();
    }
}
