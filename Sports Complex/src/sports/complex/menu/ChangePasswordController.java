/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.menu;

import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ChangePasswordController implements Initializable {

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
        // TODO
    }    

    @FXML
    private void handleConfirmBtn(ActionEvent event) {
    }
    
}
