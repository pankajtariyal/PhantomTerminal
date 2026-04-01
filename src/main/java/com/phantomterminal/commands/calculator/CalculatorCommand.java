package com.phantomterminal.commands.calculator;

import com.phantomterminal.commands.Command;
import com.phantomterminal.common.calculatorUtil.CalculatorUtil;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface CalculatorCommand extends Command {
    void execute(List<String> args)throws IOException;
}
