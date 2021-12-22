package sports.complex.login;

import Classes.Utility;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sports.complex.alert.AlertMaker;
import Database.DbQuery;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class PasswordRecoveryController implements Initializable {

    @FXML
    private Label securityQues;
    @FXML
    private JFXTextField answer;
    @FXML
    private JFXPasswordField newPass;
    @FXML
    private JFXPasswordField retypeNewPass;

    private static String username;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            securityQues.setText(DbQuery.getSecurityQs(username));
        } catch (SQLException ex) {
            Logger.getLogger(PasswordRecoveryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PasswordRecoveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setUsername(String uname) {
        username = uname;
    }

    public String getUsername() {
        return username;
    }

    @FXML
    private void handleConfirmBtn(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (answer.getText().equals("") || newPass.getText().equals("") || retypeNewPass.getText().equals("")) {
            AlertMaker.showAlert("Try Again", "Please enter all data feilds");

        } else if (!newPass.getText().equals(retypeNewPass.getText())) {
            AlertMaker.showAlert("Try Again", "Please re-type same password");
        } else {

            if (!DbQuery.checkSecAns(username, answer.getText())) {
                AlertMaker.showAlert("Try Again", "Answer does not match with the records.");
            } else {
                if (Utility.passConstraints(newPass.getText())) {
                    DbQuery.passwordNew(username, newPass.getText());
                    AlertMaker.showAlert("Success", "Password updated successfully.");
                    getStage().close();
                } else {
                    AlertMaker.showAlert("Try Again", "Password should be of minimum 8 length, contains upper and lowercase, digit, special character.");
                }
            }

        }

    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

}
