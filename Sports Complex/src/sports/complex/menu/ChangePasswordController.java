package sports.complex.menu;

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
    
    @FXML
    private void handleConfirmBtn(ActionEvent event) {
        String cPass = currentPass.getText();
        String nPass = newPass.getText();
        String retypePass = retypeNewPass.getText();
        
        if (cPass == null || nPass == null || retypePass == null) {
            AlertMaker.showAlert("Empty fields", "Please enter all feilds");
        } else if (!cPass.equals(retypePass)) {
            
            AlertMaker.showAlert("Try Again", "Retyped password does not match");
            
        } else {
//            DbQuery.changePass();
            AlertMaker.showAlert("Success", "Password changed successfuly");
            
        }
        
    }
    
}
