package sports.complex.emergency;

import Classes.Emergency;
import Classes.InventoryItem;
import Database.DbQuery;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
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
public class EmergencyController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXCheckBox checkbox;
    @FXML
    private JFXTextField patientId;
    @FXML
    private JFXTextField problem;
    @FXML
    private JFXComboBox<String> facilitiesUsed;
    public static String emp_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateFacility();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmergencyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmergencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setId(String id) {
        emp_id = id;
    }

    public static String getId() {
        return emp_id;
    }

    public void populateFacility() throws ClassNotFoundException, SQLException {
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
        items = DbQuery.getMedicalEquipment();
        for (InventoryItem i : items) {
            facilitiesUsed.getItems().add(i.getItemName());
        }

    }

    @FXML
    private void handleRegisterBtn(ActionEvent event) throws ClassNotFoundException, SQLException {
        String id = patientId.getText();
        String p = problem.getText();
        String facility = facilitiesUsed.getValue();

        if (id.equals("") || p.equals("") || facility.equals("")) {
            AlertMaker.showAlert("Try Again", "Please enter all feilds.");

        } else {
            String status;

            if (checkbox.isSelected()) {
                status = "Resolved";
            } else {
                status = "Unresolved";
            }
            Emergency e = new Emergency(id, "", p, facility, status);
            try {
                DbQuery.registerPatient(e);
                AlertMaker.showAlert("Sucess", "Patient registered sucessfully");
                clearCache();
            } catch (Exception ex) {
                AlertMaker.showAlert("Error", "Invalid Member id");
            }
        }
    }

    private void clearCache() {
        patientId.setText("");
        problem.setText("");
        facilitiesUsed.setValue(null);

    }

    @FXML
    private void loadMedicalEquipment(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("medicalEquipment.fxml"), "Medical Equipment", null);

    }

    @FXML
    private void loadRegisteredIndividuals(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("registeredIndividuals.fxml"), "Registered", null);

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
