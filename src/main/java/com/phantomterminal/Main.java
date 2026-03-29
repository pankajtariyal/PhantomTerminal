package com.phantomterminal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Entry point of the PhantomTerminal application.
 *
 * <p>This class extends {@link Application} from JavaFX and is responsible
 * for launching the GUI-based terminal interface. It loads the FXML layout
 * file and initializes the primary stage of the application.</p>
 *
 * <p>The UI structure is defined in the <b>PhantomTerminal.fxml</b> file
 * located inside the <b>/fxml</b> resources directory.</p>
 *
 * <p>When the application starts, it creates a window with a fixed size
 * of 800x600 pixels and sets the title of the stage to <b>"Mini Terminal"</b>.</p>
 *
 * @author Abhishek
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Initializes and starts the JavaFX application.
     *
     * <p>This method loads the FXML file that defines the UI layout,
     * creates a {@link Scene}, and sets it on the provided {@link Stage}.
     * The stage represents the main window of the application.</p>
     *
     * @param stage the primary stage provided by the JavaFX runtime
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PhantomTerminal.fxml"));
        Scene scene = new Scene(loader.load(), 1000, 800);
        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logo/phantomlogo.png")));
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setTitle("Phantom Terminal");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method that launches the JavaFX application.
     *
     * <p>This method calls {@link Application#launch(String...)} which
     * starts the JavaFX runtime and invokes the {@link #start(Stage)} method.</p>
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        launch();
    }
}
