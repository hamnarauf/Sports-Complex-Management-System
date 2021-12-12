/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class RegisterSuggestionController implements Initializable {

    @FXML
    private JFXTextArea suggestion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSuggOkBtn(ActionEvent event) {
        if(suggestion.getText()==null){
            AlertMaker.showAlert("Empty Field", "Please type your suggestion");
        }
        
        else{
//            DbQuery.regComplaint(complaint.getText());
            AlertMaker.showAlert("Success", "Suggestion recorded succesfully");
            suggestion.setText("");
        }
    }
    
}
