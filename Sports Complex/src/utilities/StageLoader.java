package utilities;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sports.complex.SportsComplex;
import static sports.complex.SportsComplex.stage;
import sports.complex.alert.AlertboxController;
import sports.complex.registration.RegistrationController;

/**
 *
 * @author Hamna Rauf
 */
public class StageLoader {

    public static final String ICON_IMAGE_LOC = "/Images/icon.png";

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }

    public static void loadWindow(URL loc, String title, Stage parentStage, boolean resize) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            setStage(title, parent, parentStage, resize);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            setStage(title, parent, parentStage, true);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// height 369
    // width 485

    public static void setStage(String title, Parent parent, Stage parentStage, boolean resize) {
//        Stage stage = null;
        if (parentStage != null) {
            parentStage.close();
        }
        stage = new Stage(StageStyle.DECORATED);

        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.setResizable(resize);
        setStageIcon(stage);
        stage.show();

    }

    public static void setAlertStage(String title, AlertboxController controller) {
        Dialog dialog = new Dialog();
        dialog.setDialogPane(controller.dialogPane);
        dialog.setTitle(title);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        setStageIcon(stage);
        dialog.showAndWait();
    }

}
