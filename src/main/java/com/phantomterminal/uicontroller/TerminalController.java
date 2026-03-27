package com.phantomterminal.uicontroller;
import com.phantomterminal.common.CommonVariable;
import com.phantomterminal.engine.CommandEngine;
import com.phantomterminal.engine.CommandRegistry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TerminalController implements Initializable {

    @FXML
    private TextArea outputArea;
    @FXML
    private TextField inputField;

    @FXML
    private void handleCommand() throws IOException {
        String command = inputField.getText().trim();
        if (command.isEmpty()) return;
        outputArea.appendText(CommonVariable.currentPath + "> " + command + "\n");
        CommandEngine.executeCommand(command);
        outputArea.appendText(CommonVariable.currentPath + "> " + command + "\n");
        inputField.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CommonVariable.currentPath = System.getProperty("user.dir");
        CommonVariable.outputAreaCommon = outputArea;
        outputArea.appendText(CommonVariable.currentPath + ">\n");
    }
}