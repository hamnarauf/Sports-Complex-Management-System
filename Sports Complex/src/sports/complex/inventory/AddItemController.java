package sports.complex.inventory;

import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
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
    private void handleAddBtn(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (name.getText().equals("") || quantity.getText().equals("")) {
            AlertMaker.showAlert("Empty fields", "Please fill all fields");

        } else {
            try {
                int q = new Integer(quantity.getText());
                DbQuery.addItem(name.getText(), q);
                AlertMaker.showAlert("Success", "Item added successfully.");
                clearCache();
            } catch (Exception e) {
                AlertMaker.showAlert("Error", "Quanity can only be an integer");
            }
        }

    }
    
    private void clearCache(){
        name.setText("");
        quantity.setText("");
    
    }
}
