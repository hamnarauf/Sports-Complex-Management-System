package sports.complex.menu;

import Database.DbQuery;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.SQLException;
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
    private void handleSuggOkBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(suggestion.getText().equals("")){
            AlertMaker.showAlert("Empty Field", "Please type your suggestion");
        }
        
        else{
            DbQuery.registerSuggestion(suggestion.getText());
            AlertMaker.showAlert("Success", "Suggestion recorded succesfully");
            suggestion.setText("");
        }
    }
    
}
