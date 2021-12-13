package sports.complex.registration;

import Classes.Employee;
import Classes.Member;
import Classes.Team;
import Classes.gender;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import Database.DbQuery;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import sports.complex.alert.AlertMaker;

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
    private JFXComboBox<String> regTeamSport;
    private JFXComboBox<String> regTeamCoach;
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
    private JFXComboBox<String> regEmpDomain;
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
    private JFXComboBox<String> regEmpDept;
    @FXML
    private JFXTextField regEmpAllergies;
    @FXML
    private JFXTextField regEmpCnic;
    @FXML
    private JFXTextField tourMemId;
    @FXML
    private JFXButton tourMemBtn;
    @FXML
    private JFXTextField tourTeamId;
    @FXML
    private JFXButton tourTeamBtn;
    @FXML
    private JFXComboBox<String> sports1;
    @FXML
    private JFXComboBox<String> sports2;
    @FXML
    private JFXComboBox<String> regTeamPackage;
    @FXML
    private JFXComboBox<String> SecurityQues;
    @FXML
    private JFXComboBox<Time> regTeamTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSelectedRadio(ToggleGroup toggle) {

        RadioButton selectedRadioButton = (RadioButton) toggle.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        return toogleGroupValue;
    }

    void populateComboBox() throws SQLException {
        populateSportsCombo();
        populateDeptCombo();
        populateCoachCombo();
        populatePackageCombo();
        populateQuesCombo();

    }

    void populateSportsCombo() throws SQLException {
        ArrayList<String> sports = new ArrayList<String>();
        sports = DbQuery.getSportsList();
        for (String sport : sports) {
            regTeamSport.getItems().add(sport);
            sports1.getItems().add(sport);
            sports2.getItems().add(sport);
        }
    }

    void populateDeptCombo() throws SQLException {
        ArrayList<String> depts = new ArrayList<String>();
        depts = DbQuery.getDeptList();
        for (String dept : depts) {
            regEmpDept.getItems().add(dept);
        }
    }

    void populateCoachCombo() throws SQLException {
        ArrayList<String> sports = new ArrayList<String>();
        sports = DbQuery.getSportsList();
        for (String s : sports) {
            regEmpDomain.getItems().add(s);
        }
    }

    void populatePackageCombo() throws SQLException {

        regTeamPackage.getItems().add("Training");
        regTeamPackage.getItems().add("Non-Training");
    }

    void populateQuesCombo() throws SQLException {
        ArrayList<String> ques = new ArrayList<String>();
        ques = DbQuery.getQsList();
        for (String q : ques) {
            SecurityQues.getItems().add(q);
        }
    }
    
    void populateTimeCombo() throws SQLException {
        ArrayList<Time> times = new ArrayList<Time>();
//        ques = DbQuery.getTime();
        for (Time t : times) {
            regTeamTime.getItems().add(t);
        }
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

//    @FXML
//    private void loadRemoveCoach(ActionEvent event) {
//        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/employees/removeCoach.fxml"), "Remove Coach", null);
//
//    }
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

    public Date getDate(LocalDate local) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(local.atStartOfDay(defaultZoneId).toInstant());

        return date;

    }

    @FXML
    private void handleRegMemBtn(ActionEvent event) throws SQLException {
        String fname = regMemFN.getText();
        String lname = regMemLN.getText();
        String memGender = getSelectedRadio(regMemGender);
        LocalDate localDob = regMemDOB.getValue();
        String cnic = regMemCnic.getText();
        String address = regMemAddress.getText();
        String contact = regMemContact.getText();
        String emerContact = regMemContactEmer.getText();
        String email = regMemEmail.getText();
        String BloodGrp = regMemBloodGroup.getText();
        String allergies = regMemAllergies.getText();

        if (fname == null || lname == null || memGender == null || localDob == null || cnic == null || address == null
                || contact == null || email == null || BloodGrp == null) {
            AlertMaker.showAlert("Tryagain", "One or more feild is empty.");

        } else {
            Date dob = getDate(localDob);
            gender gen;
            if (memGender.equals("Male")) {
                gen = gender.m;
            } else {
                gen = gender.f;

            }

            Member mem = new Member(fname, lname, gen, dob, cnic, address,
                    contact, emerContact, email, BloodGrp, allergies, "");
            DbQuery.registerMember(mem);

            AlertMaker.showAlert("Registeration successfull", "Success");

        }
    }

    @FXML
    private void handleRegTeamBtn(ActionEvent event) throws SQLException {
        String sport = regTeamSport.getValue();
        String mem = regTeamMembers.getText();
        String pkg = regTeamPackage.getValue();
        Time time = regTeamTime.getValue();

        if (sport == null || mem == null || pkg == null || (pkg == "Training" && time == null)) {
            AlertMaker.showAlert("Try again", "Please enter all required feilds");
        } else {
            if (pkg.equals("Training")) {
                Team t;
                t = new Team("", sport, Integer.parseInt(mem), pkg, time);
                DbQuery.registerTeamforTraining(t);
            } else {
                Team t = new Team("", sport, Integer.parseInt(mem), pkg);
                DbQuery.registerTeam(t);
            }

            AlertMaker.showAlert("Succes", "Team Registered succesfully");

        }

    }

    @FXML
    private void handleRegEmpBtn(ActionEvent event) throws SQLException {
        String fname = regEmpFN.getText();
        String lname = regEmpLN.getText();
        String empGender = getSelectedRadio(regEmpGender);
        LocalDate localDob = regEmpDOB.getValue();
        String cnic = regEmpCnic.getText();
        String address = regEmpAddress.getText();
        String contact = regEmpContact.getText();
        String emerContact = regEmpContactEmer.getText();
        String email = regEmpEmail.getText();
        String dept = regEmpDept.getValue();
        String domain = regEmpDomain.getValue();
        String bloodgrp = regEmpBloodGroup.getText();
        String allergy = regEmpAllergies.getText();
        String ques = SecurityQues.getValue();
        String ans = regEmpSecurityAns.getText();

        if (fname == null || lname == null || empGender == null || localDob == null || cnic == null || address == null
                || contact == null || email == null || bloodgrp == null || dept == null
                || domain == null || ques == null || ans == null) {
            AlertMaker.showAlert("Try Again", "One or more feild is empty.");

        } else {
            Date dob = getDate(localDob);
            gender gen;

            if (empGender.equals("Male")) {
                gen = gender.m;
            } else {
                gen = gender.f;

            }

            Employee emp = new Employee(fname, lname, gen, dob, cnic, contact,
                    emerContact, email, address, bloodgrp, allergy, "", Integer.toString(DbQuery.getDeptID(dept)));
            DbQuery.registerEmployee(emp);

            AlertMaker.showAlert("Registeration successfull", "Success");

        }
    }

    @FXML
    private void handleTourMemBtn(ActionEvent event) {
    }

    @FXML
    private void handleTourTeamBtn(ActionEvent event) {
    }

}
