package sports.complex.inventory;

import com.jfoenix.controls.JFXTextField;
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
public class AddItemController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField quantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleAddBtn(ActionEvent event) {
        if (name.getText() == null || quantity.getText() == null) {
            AlertMaker.showAlert("Empty fields", "Please fill all fields");

        } else {
            
        }

    }
}
