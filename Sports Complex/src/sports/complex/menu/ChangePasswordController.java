package sports.complex.menu;

import Classes.Utility;
import Database.DbQuery;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ChangePasswordController implements Initializable {

    public static String emp_id;

    @FXML
    private JFXPasswordField currentPass;
    @FXML
    private JFXPasswordField newPass;
    @FXML
    private JFXPasswordField retypeNewPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public static void setId(String id) {
        emp_id = id;
    }

    public static String getId() {
        return emp_id;
    }

    @FXML
    private void handleConfirmBtn(ActionEvent event) {
        String cPass = currentPass.getText();
        String nPass = newPass.getText();
        String retypePass = retypeNewPass.getText();

        if (cPass.equals("") || nPass.equals("") || retypePass.equals("")) {
            AlertMaker.showAlert("Empty fields", "Please enter all feilds");
        } else if (!cPass.equals(retypePass)) {

            AlertMaker.showAlert("Try Again", "Retyped password does not match");

        } else {
            if (!Utility.passConstraints(newPass.getText())) {
                AlertMaker.showAlert("Try Again", "Password should be of minimum 8 length, contains upper and lowercase, digit, special character");
            } else {

//                    DbQuery.passwordNew(DbQuery.getusername(emp_id), newPass.getText());
            }

        }

    }
}
