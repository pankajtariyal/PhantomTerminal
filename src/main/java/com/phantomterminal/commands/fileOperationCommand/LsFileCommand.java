package com.phantomterminal.commands.fileOperationCommand;

import com.phantomterminal.common.CommonVariable;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * The {@code LsFileCommand} class implements the {@link FileCommand} interface
 * and provides functionality similar to the Linux <b>ls</b> command.
 *
 * <p>This command lists files and directories present in a given path
 * or the current working directory.</p>
 *
 * <h3>Supported Flags</h3>
 * <ul>
 *     <li><b>-a</b> : Shows hidden files</li>
 *     <li><b>-l</b> : Enables long format listing (planned feature)</li>
 * </ul>
 *
 * <h3>Examples</h3>
 * <pre>
 * ls
 * ls -a
 * ls -l
 * ls -al
 * ls /home/user/Documents
 * </pre>
 *
 * <p>If no path is provided, the command lists files from
 * {@link CommonVariable#currentPath}.</p>
 *
 * <h3>Implementation Details</h3>
 * <ul>
 *     <li>Uses {@link Files#list(Path)} to read directory contents.</li>
 *     <li>Uses Java Stream API to filter and process files.</li>
 *     <li>Hidden files are filtered unless the <b>-a</b> flag is provided.</li>
 * </ul>
 *
 * @author Abhishek Tadiwal
 * @version 1.0
 */
public class LsFileCommand implements FileCommand {

    /** Flag to determine whether hidden files should be displayed */
    private boolean showHidden = false;

    /** Flag to determine whether files should be displayed in long format */
    private boolean isLongFormate = false;

    /** Path provided by the user */
    private String givenPath = "";

    /**
     * Executes the ls command with provided arguments.
     *
     * @param args command arguments where:
     *             <ul>
     *                 <li>args[0] → command name ("ls")</li>
     *                 <li>args[1..n] → flags or directory path</li>
     *             </ul>
     * @throws IOException if an I/O error occurs while reading the directory
     */
    @Override
    public void execute(List<String> args) throws IOException {

        for(int i = 1; i < args.size(); i++){
            String arg = args.get(i);

            // Handle flags such as -a or -l
            if(arg.startsWith("-")){
                for(int j = 1; j < arg.length(); j++){
                    char flag = arg.charAt(j);

                    switch (flag){
                        case 'a':
                            showHidden = true;
                            break;

                        case 'l':
                            isLongFormate = true;
                            break;

                        default:
                            CommonVariable.outputAreaCommon.appendText("> Unknown Flag\n");
                            break;
                    }
                }
            } else {
                // Path argument
                givenPath = arg;
            }
        }

        // If path provided → list that directory
        if(!givenPath.isEmpty()){
            listFile(givenPath);
        } else {
            // Otherwise list current directory
            listFile(CommonVariable.currentPath);
        }
    }

    /**
     * Lists files inside a given directory.
     *
     * <p>This method reads the directory contents using
     * {@link Files#list(Path)} and prints the file names
     * to the terminal output.</p>
     *
     * @param directoryPath the directory whose contents should be listed
     * @throws IOException if directory access fails
     */
    public void listFile(String directoryPath) throws IOException {

        Path path = Paths.get(directoryPath);

        try(Stream<Path> list = Files.list(path)){
            list
                    .filter(p -> showHidden || !p.toFile().isHidden())
                    .forEach(p ->{
                        if(isLongFormate){
                            try {
                                String permission = "r--r--";
                                String type = Files.isDirectory(p)?"d":"-";
                                if(FileSystems.getDefault().supportedFileAttributeViews().contains("posix")){
                                    Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(p);
                                    permission = PosixFilePermissions.toString(posixFilePermissions);
                                }else{

                                    // fake remaining permissions
                                    permission = (p.toFile().canRead() ? "r" : "-") +
                                            (p.toFile().canWrite() ? "w" : "-") +
                                            (p.toFile().canExecute() ? "x" : "-") +
                                            "r--r--";
                                }

                                String owner = Files.getOwner(p).getName();

                                long size = Files.size(p);
                                FileTime fileTime = Files.getLastModifiedTime(p);

                                String date = new SimpleDateFormat("MMM dd")
                                        .format(new Date(fileTime.toMillis()));
                                long countSubFiles = Files.isDirectory(p)?Files.list(p).count():1;
                                CommonVariable.outputAreaCommon.appendText(type + permission + " " +
                                        countSubFiles + " " + owner + " group " +
                                        size + " " + date + " " +
                                        p.toFile().getName() + "\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            CommonVariable.outputAreaCommon.appendText("-" + p.getFileName() + "\n");
                        }
                    });
        }
    }
}
