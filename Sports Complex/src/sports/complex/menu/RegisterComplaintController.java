package sports.complex.menu;

import com.jfoenix.controls.JFXTextArea;
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
public class RegisterComplaintController implements Initializable {

    @FXML
    private JFXTextArea complaint;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleRegOkBtn(ActionEvent event) {
        if(complaint.getText().equals("")){
            AlertMaker.showAlert("Empty Field", "Please type your complaint");
        }
        
        else{
//            DbQuery.regComplaint(complaint.getText());
            AlertMaker.showAlert("Success", "Complaint registered succesfully");
            complaint.setText("");
        }
        
    }
    
}
