package sports.complex.inventory;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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
        // TODO
    }

    @FXML
    private void handleDeleteBtn(ActionEvent event) {
    }

    @FXML
    private void updateFields(ActionEvent event) {
        clearCache();
//        String name = itemName.getText();
//
//        if (!name.equals("") && DbQuery.isItem(name)) {
//            InventoryItem item;
//            item = DbQuery.getItem(name);
//            name.setText(item.getName());
//            coach.setText(item.getCoach());
//            pack.setText(item.getPackage());
//
//        } else {
//            sportName.setText("Invalid Member ID");
//        }
    }

    public void clearCache() {
        quantity.setText("");
        sportName.setText("");
    }

}
