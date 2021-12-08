/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.inventory;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
    private JFXComboBox<?> item;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadIssuedItems(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("issuedItems.fxml"), "Transaction", null);

    }

    @FXML
    private void loadAvailableItems(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("availableItems.fxml"), "Transaction", null);

    }

    @FXML
    private void loadViewHistory(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("history.fxml"), "Transaction", null);

    }

    @FXML
    private void loadAddItem(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("addItem.fxml"), "Transaction", null);

    }

    @FXML
    private void loadDeleteItem(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("delItem.fxml"), "Transaction", null);

    }

    @FXML
    private void menuChangePassword(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/changePassword.fxml"), "Change Password", null);

    }

    @FXML
    private void menuEditProfile(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/editProfile.fxml"), "Edit Profile", null);

    }

    @FXML
    private void menuViewNotice(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/menu/viewNotice/viewNotice.fxml"), "Notices", null);

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
    private void handleIssueBtn(ActionEvent event) {

    }
}
