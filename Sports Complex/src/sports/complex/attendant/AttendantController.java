/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.attendant;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utilities.StageLoader;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AttendantController implements Initializable {

    ObservableList<Attendance> list = FXCollections.observableArrayList();

    @FXML
    private BorderPane rootPane;
    @FXML
    private TableColumn<Attendance, String> idCol;
    @FXML
    private TableColumn<Attendance, String> nameCol;
    @FXML
    private TableColumn<Attendance, String> deptCol;
    @FXML
    private TableColumn<Attendance, String> roleCol;
    @FXML
    private TableColumn<Attendance, ToggleButton> attendanceCol;
    @FXML
    private TableView<Attendance> tableView;
    @FXML
    private JFXComboBox<?> filterBy;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        attendanceCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));

    }

    private void loadData() {
        list.add(new Attendance("54678", "Ahmed Ali", "Finance", "Manager"));
        tableView.setItems(list);
    }


    public class Attendance {

        private final String id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty dept;
        private final SimpleStringProperty role;
        private final ToggleButton attendance;

        public Attendance(String id, String name, String dept, String role) {
            this.id = (id);
            this.name = new SimpleStringProperty(name);
            this.dept = new SimpleStringProperty(dept);
            this.role = new SimpleStringProperty(role);
            this.attendance = new JFXToggleButton();
            attendance.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    System.out.println("Hello World!");
                    ToggleButton btn = (ToggleButton) event.getSource();
                    Attendance t = findByButton(list, btn);
                    System.out.println(t.getId());
                }

            });
        }

        public Attendance findByButton(ObservableList<Attendance> list, ToggleButton btn) {
            return list.stream().filter(individual -> btn.equals(individual.getAttendance()))
                    .findFirst().orElse(null);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name.get();
        }

        public String getDept() {
            return dept.get();
        }

        public String getRole() {
            return role.get();
        }

        public ToggleButton getAttendance() {
            return attendance;
        }
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
