/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.maintenance;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
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
import sports.complex.attendant.AttendantController;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class MaintenanceController implements Initializable {

    ObservableList<MaintenanceActivity> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<MaintenanceActivity, String> nComp1;
    @FXML
    private TableColumn<MaintenanceActivity, String> partComp1;
    @FXML
    private TableColumn<MaintenanceActivity, String> fullyComp1;
    @FXML
    private TableView<MaintenanceActivity> table1;
    @FXML
    private TableColumn<MaintenanceActivity, String> table1Activity;
    @FXML
    private TableView<?> table2;
    @FXML
    private TableColumn<?, ?> table2Activity;
    @FXML
    private TableColumn<?, ?> nComp2;
    @FXML
    private TableColumn<?, ?> partComp2;
    @FXML
    private TableColumn<?, ?> fullyComp2;
    @FXML
    private TableView<?> table3;
    @FXML
    private TableColumn<?, ?> table3Activity;
    @FXML
    private TableColumn<?, ?> nComp3;
    @FXML
    private TableColumn<?, ?> partComp3;
    @FXML
    private TableColumn<?, ?> fullyComp3;
    @FXML
    private TableView<?> table4;
    @FXML
    private TableColumn<?, ?> table4Activity;
    @FXML
    private TableColumn<?, ?> nComp4;
    @FXML
    private TableColumn<?, ?> partComp4;
    @FXML
    private TableColumn<?, ?> fullyComp4;
    @FXML
    private JFXTextField repairRequired;
    @FXML
    private JFXComboBox<?> dept;
    @FXML
    private JFXTextField expenditure;
    @FXML
    private JFXButton regRepairBtn;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {

        table1Activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        nComp1.setCellValueFactory(new PropertyValueFactory<>("nComp"));
        partComp1.setCellValueFactory(new PropertyValueFactory<>("pComp"));
        fullyComp1.setCellValueFactory(new PropertyValueFactory<>("fComp"));
    }

    private void loadData() {
        list.add(new MaintenanceActivity("Leveling"));
        table1.setItems(list);
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
                    MaintenanceActivity activity = findByButton(list, selectedBtn);
                    if (selectedBtn.equals(nComp)){
                        System.out.println("nComp");
                    }
                    
                    else if(selectedBtn.equals(pComp)){
                     System.out.println("pComp");
                    }
                    else if(selectedBtn.equals(fComp)){
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
    private void handleRegRepairBtn(ActionEvent event) {
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
