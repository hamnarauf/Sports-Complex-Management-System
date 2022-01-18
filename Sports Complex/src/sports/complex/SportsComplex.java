package sports.complex;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Hamna Rauf
 */
public class SportsComplex extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        setStage("/sports/complex/login/login.fxml", false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void setStage(String fxml, boolean resize) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.getIcons().add(new Image(("/Images/icon.png")));
            stage.setTitle("Sports Complex");
            stage.setResizable(resize);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(SportsComplex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setStage(String fxml) {
        setStage(fxml, true);

    }

}
