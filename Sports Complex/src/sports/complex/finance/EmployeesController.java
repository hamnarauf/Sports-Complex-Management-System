package sports.complex.finance;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import Classes.*;
import Database.DbQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class EmployeesController implements Initializable {

    @FXML
    private Label totalLabel;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> deptCol;
    @FXML
    private TableColumn<Employee, String> salaryCol;
    ObservableList<Employee> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Employee, String> idCol;
    @FXML
    private TableColumn<Employee, String> fnameCol;
    @FXML
    private TableColumn<Employee, String> lnameCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
            updateLabel();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateLabel() throws SQLException, ClassNotFoundException {
        String total = DbQuery.getEmpTransTotal();
        totalLabel.setText("Rs. " + total);
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

    }

    private void loadData() throws SQLException, ClassNotFoundException {

        ArrayList<Employee> allEmployees = new ArrayList<Employee>();
        allEmployees = DbQuery.viewTransEmp();
        for (Employee emp : allEmployees) {
            list.add(emp);
        }
        tableView.setItems(list);
    }

    @FXML
    private void handleExportPdf(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"   id    ", "    Name   ", "   Department   ", "    Salary   "};
        printData.add(Arrays.asList(headers));
        for (Employee emp : list) {
            List<String> row = new ArrayList<>();
            row.add(emp.getEmp_id());
            row.add(emp.getFname());
            row.add(emp.getDeptName());
            row.add(emp.getSalary());
            printData.add(row);
        }
        Utility.initPDFExprot(getStage(), printData);
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

}
