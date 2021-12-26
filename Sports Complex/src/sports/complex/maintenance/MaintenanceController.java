package sports.complex.maintenance;

import Classes.Member;
import Classes.Repair;
import Database.DbQuery;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sports.complex.alert.AlertMaker;
import sports.complex.attendant.AttendantController;
import sports.complex.menu.ChangePasswordController;
import sports.complex.menu.EditProfileController;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class MaintenanceController implements Initializable {

    ObservableList<MaintenanceActivity> list1 = FXCollections.observableArrayList();
    ObservableList<MaintenanceActivity> list2 = FXCollections.observableArrayList();
    ObservableList<MaintenanceActivity> list3 = FXCollections.observableArrayList();
    ObservableList<MaintenanceActivity> list4 = FXCollections.observableArrayList();

    @FXML
    private TableView<MaintenanceActivity> table1;
    @FXML
    private TableColumn<MaintenanceActivity, String> nComp1;
    @FXML
    private TableColumn<MaintenanceActivity, String> partComp1;
    @FXML
    private TableColumn<MaintenanceActivity, String> fullyComp1;

    @FXML
    private TableColumn<MaintenanceActivity, String> table1Activity;
    @FXML
    private TableView<MaintenanceActivity> table2;
    @FXML
    private TableColumn<MaintenanceActivity, String> table2Activity;
    @FXML
    private TableColumn<MaintenanceActivity, String> nComp2;
    @FXML
    private TableColumn<MaintenanceActivity, String> partComp2;
    @FXML
    private TableColumn<MaintenanceActivity, String> fullyComp2;

    @FXML
    private TableView<MaintenanceActivity> table3;
    @FXML
    private TableColumn<MaintenanceActivity, String> table3Activity;
    @FXML
    private TableColumn<MaintenanceActivity, String> nComp3;
    @FXML
    private TableColumn<MaintenanceActivity, String> partComp3;
    @FXML
    private TableColumn<MaintenanceActivity, String> fullyComp3;
    @FXML
    private TableView<MaintenanceActivity> table4;
    @FXML
    private TableColumn<MaintenanceActivity, String> table4Activity;
    @FXML
    private TableColumn<MaintenanceActivity, String> nComp4;
    @FXML
    private TableColumn<MaintenanceActivity, String> partComp4;
    @FXML
    private TableColumn<MaintenanceActivity, String> fullyComp4;

    @FXML
    private JFXTextField repairRequired;
    @FXML
    private JFXTextField expenditure;
    @FXML
    private JFXButton regRepairBtn;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXComboBox<String> sportCombo;
    public static String emp_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
        try {
            populateSportsCombo();
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setId(String id) {
        emp_id = id;
    }

    public static String getId() {
        return emp_id;
    }

    void populateSportsCombo() throws SQLException, ClassNotFoundException {
        ArrayList<String> sports = new ArrayList<String>();
        sports = DbQuery.getSportsList();
        for (String sport : sports) {
            sportCombo.getItems().add(sport);
        }
    }

    private void initCol() {

        table1Activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        nComp1.setCellValueFactory(new PropertyValueFactory<>("nComp"));
        partComp1.setCellValueFactory(new PropertyValueFactory<>("pComp"));
        fullyComp1.setCellValueFactory(new PropertyValueFactory<>("fComp"));

        table2Activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        nComp2.setCellValueFactory(new PropertyValueFactory<>("nComp"));
        partComp2.setCellValueFactory(new PropertyValueFactory<>("pComp"));
        fullyComp2.setCellValueFactory(new PropertyValueFactory<>("fComp"));

        table3Activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        nComp3.setCellValueFactory(new PropertyValueFactory<>("nComp"));
        partComp3.setCellValueFactory(new PropertyValueFactory<>("pComp"));
        fullyComp3.setCellValueFactory(new PropertyValueFactory<>("fComp"));

        table4Activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        nComp4.setCellValueFactory(new PropertyValueFactory<>("nComp"));
        partComp4.setCellValueFactory(new PropertyValueFactory<>("pComp"));
        fullyComp4.setCellValueFactory(new PropertyValueFactory<>("fComp"));
    }

    private void loadData() {

        ArrayList<MaintenanceActivity> activities1 = new ArrayList<MaintenanceActivity>();
        list1.add(new MaintenanceActivity("Padding"));
        list1.add(new MaintenanceActivity("Surfacing"));
        list2.add(new MaintenanceActivity("Machinery"));
        list2.add(new MaintenanceActivity("Drag & Mats"));
        list3.add(new MaintenanceActivity("Chemical in swimming pool"));
//        activities = DbQuery.displayActivities();
//        for (MaintenanceActivity act : activities1) {
//            list1.add(act);
//
//        }

        table1.setItems(list1);
        table2.setItems(list2);
        table3.setItems(list3);

    }

    @FXML
    private void menuLogout(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/login/login.fxml"), "Login", getStage(), false);

    }

    public class MaintenanceActivity {

        private final SimpleStringProperty activity;
        private final RadioButton nComp;
        private final RadioButton pComp;
        private final RadioButton fComp;
        private final ToggleGroup radioGroup;

        public MaintenanceActivity(String activity) {
            this.activity = new SimpleStringProperty(activity);

            this.nComp = new RadioButton();
            this.pComp = new RadioButton();
            this.fComp = new RadioButton();

            radioGroup = new ToggleGroup();
            nComp.setToggleGroup(radioGroup);
            pComp.setToggleGroup(radioGroup);
            fComp.setToggleGroup(radioGroup);
            radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                    RadioButton selectedBtn = (RadioButton) radioGroup.getSelectedToggle();
                    MaintenanceActivity activity = findByButton(list1, selectedBtn);
                    if (selectedBtn.equals(nComp)) {
                        System.out.println("nComp");
                    } else if (selectedBtn.equals(pComp)) {
                        System.out.println("pComp");
                    } else if (selectedBtn.equals(fComp)) {
                        System.out.println("fComp");
                    }
                }

            }
            );
        }

        public MaintenanceActivity findByButton(ObservableList<MaintenanceActivity> list, RadioButton btn) {
            return list.stream().filter(activity -> btn.equals(activity.getRadioGroup().getSelectedToggle()))
                    .findFirst().orElse(null);
        }

        public String getActivity() {
            return activity.get();
        }

        public RadioButton getNComp() {
            return nComp;
        }

        public RadioButton getPComp() {
            return pComp;
        }

        public RadioButton getFComp() {
            return fComp;
        }

        public ToggleGroup getRadioGroup() {
            return radioGroup;
        }

//
//        private EventHandler<ActionEvent> handleToggle() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
    }

    @FXML
    private void handleRegRepairBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String purpose = repairRequired.getText();
        String sport = sportCombo.getValue();
        String amount = expenditure.getText();
        if (purpose.equals("") || sport.equals("") || amount.equals("")) {
            AlertMaker.showAlert("Empty Fields", "Please enter all fields");

        } else {
            try {
                int a = new Integer(amount);
                Repair repair = new Repair(purpose, sport, amount, "");
                DbQuery.registerRepair(repair);

                AlertMaker.showAlert("Success", "Succesfully registered repair");
                clearCache();

            } catch (Exception e) {
                AlertMaker.showAlert("Error", "Amount can only be numeric.");
            }
        }
    }

    private void clearCache() {
        repairRequired.setText("");
        sportCombo.setValue(null);
        expenditure.setText("");

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

}
