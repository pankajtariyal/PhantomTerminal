package com.phantomterminal.commands.fileOperationCommand;

import com.phantomterminal.common.CommonVariable;

import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * Implements the <b>cat</b> command for the PhantomTerminal application.
 *
 * <p>The cat command is used for file content operations similar to the
 * Linux/Unix terminal. It supports reading the contents of one or more
 * files and displaying them in the terminal output.</p>
 *
 * <p>This command also supports file creation using output redirection syntax.</p>
 *
 * <h2>Supported Operations</h2>
 *
 * <ul>
 *     <li><b>cat &lt;file&gt;</b> – Displays the contents of the specified file.</li>
 *     <li><b>cat &lt;file1&gt; &lt;file2&gt;</b> – Displays the contents of multiple files sequentially.</li>
 *     <li><b>cat &gt; filename</b> – Creates a new file if it does not exist.</li>
 * </ul>
 *
 * <h2>Behavior</h2>
 * <ul>
 *     <li>If the file path is not absolute, it is resolved relative to the current working directory.</li>
 *     <li>If a file does not exist while attempting to read, an error message is displayed.</li>
 *     <li>When using <code>cat &gt; filename</code>, the command creates the file if it does not exist.</li>
 * </ul>
 *
 * <p>The output is written to the terminal UI using
 * {@link com.phantomterminal.common.CommonVariable#outputAreaCommon}.</p>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class CatFileCommand implements FileCommand {

    /**
     * Flag used for future implementation of file write mode.
     */
    private boolean createFile = false;

    /**
     * Executes the cat command.
     *
     * <p>This method processes command arguments to determine whether the user
     * wants to read file contents or create a new file using redirection syntax.</p>
     *
     * <h3>Execution Flow</h3>
     * <ul>
     *     <li>Validates command arguments.</li>
     *     <li>Handles file creation when <code>cat &gt; filename</code> syntax is used.</li>
     *     <li>Reads and prints contents of specified files.</li>
     * </ul>
     *
     * @param args the list of command arguments passed by the terminal input
     * @throws IOException if an error occurs during file reading or creation
     */
    @Override
    public void execute(List<String> args) throws IOException {

        if(args.size() < 2){
            CommonVariable.outputAreaCommon.appendText("cat: missing operand\n");
            return;
        }

        System.out.println("command: " + args);

        // Handle file creation using "cat > filename"
        if(args.get(1).equals(">")){

            if(args.size() != 4){
                CommonVariable.outputAreaCommon.appendText("Usage cat > filename \"text\"\n");
                return;
            }

            String fileName = args.get(2);
            Path path = Paths.get(fileName);

            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath + File.separator + fileName);
            }

            File file = path.toFile();
            String writeValue = args.get(3) + "\n";
            if(!Files.exists(path)){
                if(file.createNewFile()){
                    System.out.println(fileName + " file created successfully");
                }else{
                    CommonVariable.outputAreaCommon.appendText(
                            "Failed to create file " + fileName + "\n");
                    return;
                }
                System.out.println("text written successfully");
            }
            System.out.println("write value " + writeValue);
            Files.write(path,writeValue.getBytes(),StandardOpenOption.APPEND);
            System.out.println("text written successfully and created");
            return;
        }

        // Read file contents
        StringBuilder readValue = new StringBuilder();

        for(int i = 1; i < args.size(); i++){

            String fileName = args.get(i);
            Path path = Paths.get(fileName);

            if(!path.isAbsolute()){
                path = Paths.get(CommonVariable.currentPath + File.separator + fileName);
            }

            File file = path.toFile();

            if(!file.exists()){
                CommonVariable.outputAreaCommon.appendText(
                        "file not exists " + fileName + "\n");
                continue;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            while((line = reader.readLine()) != null){
                readValue.append(line).append("\n");
            }

            reader.close();
        }

        CommonVariable.outputAreaCommon.appendText(readValue.toString());
        readValue.setLength(0);
    }
}
