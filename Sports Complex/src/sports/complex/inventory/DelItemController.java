package sports.complex.inventory;

import Classes.InventoryItem;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class DelItemController implements Initializable {

    @FXML
    private JFXTextField itemName;
    @FXML
    private Label quantity;
    @FXML
    private Label sportName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearCache();
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) throws ClassNotFoundException, SQLException {
        String name = itemName.getText();
        if(!name.equals("") && !DbQuery.isItem(name).equals("")){
            DbQuery.deleteItem(name);
            AlertMaker.showAlert("Success", "Item deleted successfuly");
            itemName.setText("");
            clearCache();
        }
        else{
            AlertMaker.showAlert("Invalid", "Please Try again.");
        }
    }

    @FXML
    private void updateFields(ActionEvent event) throws ClassNotFoundException, SQLException {
        clearCache();
        String name = itemName.getText();

        if (!name.equals("") && !DbQuery.isItem(name).equals("")) {
            InventoryItem item;
            item = DbQuery.showDeleteItemDetails(name);
            quantity.setText(Integer.toString(item.getQuantity()));
            sportName.setText(item.getSportName());

        } else {
            sportName.setText("Item does not exist.");
        }
    }

    public void clearCache() {
        quantity.setText("");
        sportName.setText("");
    }

}
