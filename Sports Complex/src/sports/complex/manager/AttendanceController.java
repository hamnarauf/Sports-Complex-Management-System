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
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AttendanceController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXComboBox<?> filter;
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
    private TableColumn<Attendance, String> roleCol;
    @FXML
    private TableColumn<Attendance, Date> dateCol;
    @FXML
    private TableColumn<Attendance, String> attendanceCol;

    ObservableList<Attendance> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        attendanceCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));

    }

    private void loadData() {

        ArrayList<Attendance> employees = new ArrayList<Attendance>();
        for (Attendance employee : employees) {
            list.add(employee);
        }
        tableView.setItems(list);
    }

}
