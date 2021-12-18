package sports.complex.attendant;

import Database.DbQuery;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
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
    private JFXComboBox<String> filterBy;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
        try {
            try {
                populateDeptCombo();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AttendantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateDeptCombo() throws SQLException, ClassNotFoundException {
        ArrayList<String> depts = new ArrayList<String>();
        depts = DbQuery.getDeptList();
        for (String d : depts) {
            filterBy.getItems().add(d);
        }

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
    
        private void filterById() {
//        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Attendance> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Attendance> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);

    }


    @FXML
    private void filterByDept(MouseEvent event) {
     // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Attendance> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterBy.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getDept().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Attendance> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }
}
