package com.phantomterminal.uicontroller;

import com.phantomterminal.uicontroller.Common.CommonVariable;
import com.phantomterminal.uicontroller.Common.UtilFactory;
import com.phantomterminal.uicontroller.Common.Utils;
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

    private final UtilFactory utilFactory = new Utils();


    @FXML
    private void handleCommand() {
        String command = inputField.getText().trim();

        if (command.isEmpty()) return;

        outputArea.appendText(CommonVariable.currentPath + ">> " + command + "\n");

        String[] parts = command.split("\\s+");
        String cmd = parts[0].toLowerCase();

        switch (cmd) {
            case "exit":
                outputArea.appendText("Goodbye!\n");
                break;

            case "close":
                outputArea.appendText("exiting.\n");
                Stage stage = (Stage) outputArea.getScene().getWindow();
                stage.close();
                break;

            case "help":
                utilFactory.onHelpCommand(outputArea);
                break;

            case "ls":
                if (command.equals("ls -a")) {
                    utilFactory.listAllFileOfDirectoryHidden();
                } else {
                    utilFactory.listAllFileOfDirectory(outputArea);
                }
                break;

            case "cd":
                if (parts.length > 1 && parts[1].equals("..")) {
                    utilFactory.intoParentDirectory(outputArea);
                } else {
                    utilFactory.intoTheDirectory(outputArea, command);
                }
                break;

            case "mkdir":
                utilFactory.createDirectory(command);
                break;

            case "rmdir":
                utilFactory.deleteDirectory(command);
                break;

            case "touch":
                utilFactory.createFile(command);
                break;

            case "ioexits":
                utilFactory.isFileExits(outputArea, command);
                break;

            case "clear":
                outputArea.clear();
                break;
            default:
                outputArea.appendText("Unknown command\n");
        }

        inputField.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CommonVariable.currentPath = System.getProperty("user.home");
        CommonVariable.outputAreaCommon = outputArea;
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("banner.txt"))));){
//            String str = "";
//            while ((str=br.readLine())!=null){
//                sb.append(str).append("\n");
//                outputArea.appendText(str+"\n");
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}