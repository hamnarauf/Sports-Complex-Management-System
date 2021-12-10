package sports.complex.login;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sports.complex.alert.AlertMaker;
import Database.DbQuery;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class EnterUsernameController implements Initializable {

    @FXML
    private JFXTextField username;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleNextBtn(ActionEvent event) throws SQLException, IOException {
        boolean valid;
        String uname = username.getText();

        if (username.getText().equals("")) {
            AlertMaker.showSimpleAlert("Try Again", "Please enter username");
        } else {
            valid = DbQuery.isCorrectUsername(username.getText());

            if (valid) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("passwordRecovery.fxml"));
                Parent parent = loader.load();

                PasswordRecoveryController controller = loader.getController();
                controller.setUsername(uname);

                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Password Recovery");
                stage.setScene(new Scene(parent));
                stage.show();

            } else {
                AlertMaker.showSimpleAlert("Try Again", "Username does not exists");

            }
        }
    }

}
