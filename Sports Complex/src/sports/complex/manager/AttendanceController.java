package sports.complex.manager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.*;
import Database.DbQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AttendanceController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private TableView<Attendance> tableView;
    @FXML
    private TableColumn<Attendance, String> idCol;
    @FXML
    private TableColumn<Attendance, String> nameCol;
    @FXML
    private TableColumn<Attendance, String> contactCol;
    @FXML
    private TableColumn<Attendance, String> emailCol;
    @FXML
    private TableColumn<Attendance, String> deptCol;
    @FXML
    private TableColumn<Attendance, Date> dateCol;
    @FXML
    private TableColumn<Attendance, String> attendanceCol;

    ObservableList<Attendance> list = FXCollections.observableArrayList();
    @FXML
    private JFXComboBox<String> filterDept;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            initCol();
            loadData();
            populateDeptCombo();
            filterById();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateDeptCombo() throws SQLException, ClassNotFoundException {
        ArrayList<String> depts = new ArrayList<String>();
        depts = DbQuery.getDeptList();
        for (String dept : depts) {
            filterDept.getItems().add(dept);
        }
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        attendanceCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));

    }

    private void loadData() throws ClassNotFoundException, SQLException {

        ArrayList<Attendance> employees = new ArrayList<Attendance>();
        employees = DbQuery.displayAttendance();
        for (Attendance employee : employees) {
            list.add(employee);
        }
        tableView.setItems(list);
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

                if (employee.getEmp_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
        filterDept.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getDeptName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
