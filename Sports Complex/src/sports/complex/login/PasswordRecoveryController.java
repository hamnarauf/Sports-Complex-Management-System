/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.login;

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

    private String username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            securityQues.setText(DbQuery.getSecurityQs(username));
        } catch (SQLException ex) {
            Logger.getLogger(PasswordRecoveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUsername(String uname) {
        username = uname;
    }

    public String getUsername() {
        return username;
    }

    @FXML
    private void handleConfirmBtn(ActionEvent event) throws SQLException {

        if (answer.getText() == null || newPass.getText() == null || retypeNewPass.getText() == null) {
            AlertMaker.showSimpleAlert("Try Again", "Please enter all data feilds");

        } else if (!newPass.getText().equals(retypeNewPass.getText())) {
            AlertMaker.showSimpleAlert("Try Again", "Please re-type same password");
        } else {
            if (DbQuery.checkSecAns(username, answer.getText())) {
                DbQuery.passwordRecovery(username, newPass.getText());
            } else {
                AlertMaker.showSimpleAlert("Try Again", "Answer does not match with the records.");
            }
        }
    }
}
