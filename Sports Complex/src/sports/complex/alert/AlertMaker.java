package sports.complex.alert;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sports.complex.registration.RegistrationController;
import utilities.StageLoader;

/**
 *
 * @author Hamna Rauf
 */
public class AlertMaker {

    public static void showAlert(String title, String content) {

        try {
            AlertMaker alert = new AlertMaker();
            FXMLLoader loader = new FXMLLoader(alert.getClass().getResource("alertbox.fxml"));
            Parent parent = loader.load();

            AlertboxController controller = loader.getController();
            controller.setContent(content);
            controller.setTitle(title);

            StageLoader.setAlertStage(title, controller);

        } catch (IOException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
