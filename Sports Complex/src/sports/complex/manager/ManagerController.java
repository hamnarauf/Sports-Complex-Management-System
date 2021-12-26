package sports.complex.manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sports.complex.menu.ChangePasswordController;
import sports.complex.menu.EditProfileController;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ManagerController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    public static String emp_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static void setId(String id) {
        emp_id = id;
    }

    public static String getId() {
        return emp_id;
    }

    @FXML
    private void loadComplSugg(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("comp_sugg.fxml"), "Complaints and Suggestions", null);

    }

    @FXML
    private void loadAttendance(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("attendance.fxml"), "Attendance", null);

    }

    @FXML
    private void loadRepairs(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("repairs.fxml"), "Repairs", null);

    }

    @FXML
    private void loadYearlySchedule(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("schedule.fxml"), "Schedule", null);

    }

    @FXML
    private void loadMedicalEmergency(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("emergencies.fxml"), "Emergencies", null);

    }

    @FXML
    private void loadIssueNotice(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("issueNotice.fxml"), "Issue Notice", null);

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

    @FXML
    private void loadInsertedEmp(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("insertedEmp.fxml"), "Inserted Employees", null);

    }

    @FXML
    private void loadDelEmp(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("removeEmp.fxml"), "Removed Employees", null);

        
    }

}
