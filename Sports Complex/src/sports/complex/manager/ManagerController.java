/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ManagerController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void loadFacilities(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("facilities.fxml"), "Facilities", null);

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

}
