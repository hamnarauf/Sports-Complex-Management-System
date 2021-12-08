/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.registration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RegistrationController implements Initializable {

    @FXML
    private Label fileLabel;
    @FXML
    private BorderPane rootPane;
    @FXML
    private RadioButton memberGender;
    @FXML
    private JFXTextField regMemSecurityAns;
    @FXML
    private JFXTextField regMemFN;
    @FXML
    private JFXTextField regMemAddress;
    @FXML
    private JFXTextField regMemLN;
    @FXML
    private JFXTextField regMemContactEmer;
    @FXML
    private JFXTextField regMemBloodGroup;
    @FXML
    private JFXDatePicker regMemDOB;
    @FXML
    private ToggleGroup regMemGender;
    @FXML
    private JFXTextField regMemContact;
    @FXML
    private JFXTextField regMemEmail;
    @FXML
    private JFXTextField regMemAllergies;
    @FXML
    private JFXTextField regMemCnic;
    @FXML
    private JFXCheckBox regMemTrainee;
    @FXML
    private JFXComboBox<?> regTeamSport;
    @FXML
    private JFXTextField regTeamCoach;
    @FXML
    private JFXTextField regTeamPackage;
    @FXML
    private JFXTextField regTeamMembers;
    @FXML
    private JFXTextField regEmpSecurityAns;
    @FXML
    private JFXTextField regEmpFN;
    @FXML
    private JFXTextField regEmpAddress;
    @FXML
    private JFXTextField regEmpLN;
    @FXML
    private JFXTextField regEmpContactEmer;
    @FXML
    private JFXTextField regEmpDomain;
    @FXML
    private JFXDatePicker regEmpDOB;
    @FXML
    private ToggleGroup regEmpGender;
    @FXML
    private JFXTextField regEmpContact;
    @FXML
    private JFXTextField regEmpEmail;
    @FXML
    private JFXTextField regEmpBloodGroup;
    @FXML
    private JFXComboBox<?> regEmpDept;
    @FXML
    private JFXComboBox<?> regEmpRole;
    @FXML
    private JFXTextField regEmpAllergies;
    @FXML
    private JFXTextField regEmpCnic;
    @FXML
    private JFXTextField tourMemId;
    @FXML
    private JFXButton tourMemBtn;
    @FXML
    private JFXComboBox<?> tournament1;
    @FXML
    private JFXTextField tourTeamId;
    @FXML
    private JFXComboBox<?> tournament2;
    @FXML
    private JFXButton tourTeamBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        combox.getitems().add():
    }

    @FXML
    private void loadTraineeWindow(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/members/registerTrainee.fxml"), "Register Trainee", null);

    }

    @FXML
    private void loadAllMember(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/members/allMembers.fxml"), "Registered Members", null);

    }

    @FXML
    private void loadGuestWindow(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/members/registerGuest.fxml"), "Add New Guest", null);
    }

    @FXML
    private void loadRemoveMember(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/members/removeMember.fxml"), "Remove Member", null);

    }

    @FXML
    private void loadRemoveTeam(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/teams/removeTeam.fxml"), "Remove Team", null);

    }

    @FXML
    private void loadAllTeams(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/teams/allTeams.fxml"), "Registered Team", null);

    }

    @FXML
    private void loadAllCoaches(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/employees/allCoaches.fxml"), "Registered Coaches", null);

    }

    @FXML
    private void loadAllEmployee(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/employees/allEmployees.fxml"), "Registered Employees", null);

    }

    @FXML
    private void loadWorkingHours(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/employees/coachWorkingHours.fxml"), "Working Hours", null);

    }

    @FXML
    private void loadRemoveEmployee(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/employees/removeEmployee.fxml"), "Remove Coach", null);

    }

    @FXML
    private void loadRemoveCoach(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/employees/removeCoach.fxml"), "Remove Coach", null);

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

    @FXML
    void uploadFile(ActionEvent event) {
        FileChooser fo = new FileChooser();
        List<File> files = fo.showOpenMultipleDialog(null);
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
            fileLabel.setText(f.getName());
        }
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void handleRegMemBtn(ActionEvent event) {
    }

    @FXML
    private void handleRegTeamBtn(ActionEvent event) {
    }

    @FXML
    private void handleRegEmpBtn(ActionEvent event) {
    }

    @FXML
    private void handleTourMemBtn(ActionEvent event) {
    }

    @FXML
    private void handleTourTeamBtn(ActionEvent event) {
    }

}
