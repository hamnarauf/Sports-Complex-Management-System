package sports.complex.registration;

import Classes.Employee;
import Classes.Member;
import Classes.Team;
import Classes.User;
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
import static Database.DbQuery.getSportID;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.input.MouseEvent;
import sports.complex.alert.AlertMaker;
import sports.complex.menu.ChangePasswordController;
import sports.complex.menu.EditProfileController;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RegistrationController implements Initializable {

    public static String emp_id;
    @FXML
    private Label fileLabel;
    @FXML
    private BorderPane rootPane;
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
    @FXML
    private JFXComboBox<String> regTeamDay;
    @FXML
    private RadioButton memGenM;
    @FXML
    private RadioButton memGenF;
    @FXML
    private RadioButton empGenM;
    @FXML
    private RadioButton empGenF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setId(String id) {
        emp_id = id;
    }

    public static String getId() {
        return emp_id;
    }

    public String getSelectedRadio(ToggleGroup toggle) {
        String toogleGroupValue = "";
        try {
            RadioButton selectedRadioButton = (RadioButton) toggle.getSelectedToggle();
            toogleGroupValue = selectedRadioButton.getText();
        } catch (Exception e) {
            System.out.println("no radio btn selected");
        }
        return toogleGroupValue;
    }

    void populateComboBox() throws SQLException, ClassNotFoundException {
        populateSportsCombo();
        populateDeptCombo();
        populateCoachCombo();
        populatePackageCombo();
        populateQuesCombo();
        populateDaysCombo();

    }

    void populateSportsCombo() throws SQLException, ClassNotFoundException {
        ArrayList<String> sports = new ArrayList<String>();
        sports = DbQuery.getSportsList();
        for (String sport : sports) {
            regTeamSport.getItems().add(sport);
            sports1.getItems().add(sport);
            sports2.getItems().add(sport);
        }
    }

    void populateDeptCombo() throws SQLException, ClassNotFoundException {
        ArrayList<String> depts = new ArrayList<String>();
        depts = DbQuery.getDeptList();
        for (String dept : depts) {
            regEmpDept.getItems().add(dept);
        }
    }

    void populateCoachCombo() throws SQLException, ClassNotFoundException {
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

    void populateQuesCombo() throws SQLException, ClassNotFoundException {
        ArrayList<String> ques = new ArrayList<String>();
        ques = DbQuery.getQsList();
        for (String q : ques) {
            SecurityQues.getItems().add(q);
        }
    }

    void populateTimeCombo(String day, String sport) throws SQLException, ClassNotFoundException {
        ArrayList<Time> times = new ArrayList<Time>();
        times = DbQuery.getTime(sport, day);

        regTeamTime.getSelectionModel().clearSelection();
        regTeamTime.getItems().clear();
        for (Time t : times) {
            regTeamTime.getItems().add(t);
        }

//        regTeamTime.getItems().add(new Time(9, 0, 0));
//        regTeamTime.getItems().add(new Time(10, 0, 0));
//        regTeamTime.getItems().add(new Time(13, 0, 0));
    }

    void populateDaysCombo() {
        regTeamDay.getSelectionModel().clearSelection();
        regTeamDay.getItems().clear();
        regTeamDay.getItems().add("Monday");
        regTeamDay.getItems().add("Tuesday");
        regTeamDay.getItems().add("Wednesday");
        regTeamDay.getItems().add("Thursday");
        regTeamDay.getItems().add("Friday");
        regTeamDay.getItems().add("Saturday");
        regTeamDay.getItems().add("Sunday");
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

    private void clearMemberCache() {
        regMemFN.setText("");
        regMemLN.setText("");
        regMemDOB.setValue(null);
        regMemContact.setText("");
        regMemContactEmer.setText("");
        regMemEmail.setText("");
        regMemBloodGroup.setText("");
        regMemAllergies.setText("");
        regMemCnic.setText("");
        regMemAddress.setText("");

        memGenM.setSelected(false);
        memGenF.setSelected(false);
    }

    @FXML
    private void handleRegMemBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
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

        if (fname.equals("") || lname.equals("") || memGender.equals("") || localDob.equals("") || cnic.equals("")
                || address.equals("") || contact.equals("") || email.equals("") || BloodGrp.equals("")) {
            AlertMaker.showAlert("Try Again", "One or more feild is empty.");

        } else {
            Date dob = getDate(localDob);
            gender gen;
            if (memGender.equals("Male")) {
                gen = gender.M;
            } else {
                gen = gender.F;

            }

            Member mem = new Member(fname, lname, gen, dob, cnic, address,
                    contact, emerContact, email, BloodGrp, allergies, "");
            try {
                DbQuery.registerMember(mem);
                AlertMaker.showAlert("Success", "Registeration successfull");
                clearMemberCache();
            } catch (Exception ex) {
                AlertMaker.showAlert("Error", "Invalid details");
            }
        }
    }

    private void clearTeamCache() {
        regTeamSport.setValue(null);
        regTeamMembers.setText("");
        regTeamPackage.setValue(null);
        regTeamTime.setValue(null);
        regTeamDay.setValue(null);

    }

    @FXML
    private void handleRegTeamBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String sport = regTeamSport.getValue();
        String mem = regTeamMembers.getText();
        String pkg = regTeamPackage.getValue();
        Time time = regTeamTime.getValue();
        String day = regTeamDay.getValue();

        if (sport.equals("") || pkg.equals("")
                || (pkg.equals("") && time.equals("") && day.equals(""))) {
            AlertMaker.showAlert("Try again", "Please enter all required feilds");
        } else {

//            try {
            if (pkg.equals("Training")) {
                Team t;
                t = new Team("", sport, 0, pkg, time);
                DbQuery.registerTeamforTraining(t);
            } else {
                Team t = new Team("", sport, 0, pkg);
                DbQuery.registerTeam(t);
            }

            AlertMaker.showAlert("Succes", "Team Registered succesfully");
            clearTeamCache();

//            } catch (Exception e) {
//                AlertMaker.showAlert("Error", "Invalid Details.");
//            }
        }
    }

    private void clearEmpCache() {
        regEmpFN.setText("");
        regEmpLN.setText("");
        regEmpDOB.setValue(null);
        regEmpCnic.setText("");
        regEmpAddress.setText("");
        regEmpContact.setText("");
        regEmpContactEmer.setText("");
        regEmpEmail.setText("");
        regEmpDept.setValue(null);
        regEmpDomain.setValue(null);
        regEmpBloodGroup.setText("");
        regEmpAllergies.setText("");
        SecurityQues.setValue(null);
        regEmpSecurityAns.setText("");
        fileLabel.setText("");

        empGenM.setSelected(false);
        empGenF.setSelected(false);
    }

    @FXML
    private void handleRegEmpBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
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

        if (fname.equals("") || lname.equals("") || empGender.equals("") || localDob.equals("") || cnic.equals("")
                || address.equals("") || contact.equals("") || email.equals("") || bloodgrp.equals("") || dept.equals("")) {
            AlertMaker.showAlert("Try Again", "One or more feild is empty.");

        } else {
            Date dob = getDate(localDob);
            gender gen;

            if (empGender.equals("Male")) {
                gen = gender.M;
            } else {
                gen = gender.F;

            }

            Employee emp = new Employee(fname, lname, gen, dob, cnic, contact,
                    emerContact, email, address, bloodgrp, allergy, "", DbQuery.getDeptID(dept));
            try {
                if (domain == null || domain.equals("")) {
                    if (ans == null || ans.equals("")) {
                        DbQuery.registerEmployee(emp, false);
                    } else {
                        User user = new User(emp, ques, ans);
                        System.out.println("reg user");
                        DbQuery.registerUser(user, false);
                    }

                } else {
                    emp.setSportName(domain);
                    if (ans == null || ans.equals("")) {
                        DbQuery.registerEmployee(emp, true);
                    } else {
                        User user = new User(emp, ques, ans);

                        DbQuery.registerUser(user, true);
                    }
                }

                AlertMaker.showAlert("Success", "Registeration successful.");
                clearEmpCache();

            } catch (Exception ex) {
                AlertMaker.showAlert("Error", "Invalid data.");
            }

        }
    }

    @FXML
    private void handleTourMemBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = tourMemId.getText();
        String sport = sports1.getValue();

        if (id.equals("") || sport.equals("")) {
            AlertMaker.showAlert("Try Again", "Please enter all fields");
        } else {
            if (!DbQuery.isMember(DbQuery.getMemberCnic(id))) {
                AlertMaker.showAlert("Error", "Invalid member id.");

            } else {
                AlertMaker.showAlert("Success", "Registeration successfull");
                tourMemId.setText("");
                sports1.setValue("");
            }
        }

    }

    @FXML
    private void handleTourTeamBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = tourTeamId.getText();
        String sport = sports2.getValue();

        if (id.equals("") || sport.equals("")) {
            AlertMaker.showAlert("Try Again", "Please enter all fields");
        } else {
            if (!DbQuery.isTeam(id)) {
                AlertMaker.showAlert("Error", "Invalid Team id");

            } else {
                AlertMaker.showAlert("Success", "Registeration successfull");
                tourTeamId.setText("");
                sports2.setValue("");
            }
        }
    }

    @FXML
    private void handlePkgTeam(ActionEvent event) {
        if (regTeamPackage.getValue() != null) {
            if (regTeamPackage.getValue().equals("Training")) {
                populateDaysCombo();
            } else {
                regTeamDay.getSelectionModel().clearSelection();
                regTeamDay.getItems().clear();
            }
        }

    }

    @FXML
    private void handleDayTeam(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (regTeamSport.getValue() != null) {
            populateTimeCombo(regTeamDay.getValue(), regTeamSport.getValue());
        }
    }

    @FXML
    private void handleRegTeamSport(ActionEvent event) throws SQLException, ClassNotFoundException {
        regTeamMembers.setText(DbQuery.getSportMembers(regTeamSport.getValue()));
//        regTeamMembers.setText("2");

    }

    @FXML
    private void menuLogout(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/login/login.fxml"), "Login", getStage(), false);

    }

    @FXML
    private void handleTeamTime(MouseEvent event) {
    }

}
