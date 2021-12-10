package sports.complex.alert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sports.complex.SportsComplex;
import static sports.complex.SportsComplex.stage;
import sports.complex.registration.RegistrationController;

public class AlertboxController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button1;
    @FXML
    private Button button2;

    URL path = getClass().getResource("alertbox.fxml");
    public static final String ICON_IMAGE_LOC = "/Images/icon.png";

    public AlertboxController(String message, String btn1Label) {
        label.setText(message);
        button1.setText(btn1Label);
        button2.setVisible(false);

    }

    public AlertboxController(String message, String btn1Label, String btn2Label) {
        this(message, btn1Label);
        button2.setVisible(true);
        button2.setText(btn2Label);

    }

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }

    public  void loadWindow(Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(path);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle("Alert");
            stage.setScene(new Scene(parent));
            stage.show();
            setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return controller;
//        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
