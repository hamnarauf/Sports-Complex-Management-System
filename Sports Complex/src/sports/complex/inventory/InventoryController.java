package sports.complex.inventory;

import Classes.AvailableItem;
import Classes.InventoryItem;
import Database.DbQuery;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sports.complex.alert.AlertMaker;
import sports.complex.menu.ChangePasswordController;
import sports.complex.menu.EditProfileController;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class InventoryController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private Button issuebtn;
    @FXML
    private JFXTextField MemberId;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXComboBox<String> item;
    public static String emp_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateItemCombo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateItemCombo() throws ClassNotFoundException, SQLException {
        ArrayList<AvailableItem> items = new ArrayList<AvailableItem>();
        items = DbQuery.displayAvailableItems();
        for (AvailableItem i : items) {
            item.getItems().add(i.getItemName());
        }

    }

    @FXML
    private void handleIssueBtn(ActionEvent event) {
        String id = MemberId.getText();
        String quant = quantity.getText();
        String items = item.getValue();

        if (id.equals("") || quant.equals("") || items.equals("")) {
            AlertMaker.showAlert("Empty fields", "Please fill all fields");

        } else {
            try {
                int q = new Integer(quantity.getText());
                InventoryItem i = new InventoryItem(id, "", items, q, Time.valueOf(LocalTime.now()));

                try {
                    DbQuery.issueItem(i);
                    AlertMaker.showAlert("Success", "Item Issue successfully");
                    MemberId.setText("");
                    quantity.setText("");
                    item.setValue(null);
                } catch (Exception e) {
                    AlertMaker.showAlert("Error", "Invalid Member ID.");
                }

            } catch (Exception e) {
                AlertMaker.showAlert("Error", "Quanity can only be an integer");
            }

        }

    }

    public static void setId(String id) {
        emp_id = id;
    }

    public static String getId() {
        return emp_id;
    }

    @FXML
    private void loadIssuedItems(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("issuedItems.fxml"), "Issued Items", null);

    }

    @FXML
    private void loadAvailableItems(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("availableItems.fxml"), "Available Items", null);

    }

    @FXML
    private void loadViewHistory(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("history.fxml"), "History", null);

    }

    @FXML
    private void loadAddItem(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("addItem.fxml"), "Add Item", null);

    }

    @FXML
    private void loadDeleteItem(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("delItem.fxml"), "Delete Item", null);

    }

    @FXML
    private void menuChangePassword(ActionEvent event) {
        ChangePasswordController.setId(emp_id);
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/changePassword.fxml"), "Change Password", null);

    }

    @FXML
    private void menuEditProfile(ActionEvent event) {
        EditProfileController.setId(emp_id);
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/editProfile.fxml"), "Edit Profile", null);

    }

    @FXML
    private void menuViewNotice(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/viewNotice.fxml"), "Notices", null);

    }

    @FXML
    private void menuComplaint(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/registerComplaint.fxml"), "Complaint Box", null);

    }

    @FXML
    private void menusuggestion(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/registerSuggestion.fxml"), "Suggestion Box", null);

    }

    @FXML
    private void menuExit(ActionEvent event) {
        getStage().close();
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void menuLogout(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/login/login.fxml"), "Login", getStage(), false);

    }

}
