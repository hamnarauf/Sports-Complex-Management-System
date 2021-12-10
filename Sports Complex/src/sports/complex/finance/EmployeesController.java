/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> deptCol;
    @FXML
    private TableColumn<Employee, String> salaryCol;
    ObservableList<Employee> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Employee, String> idCol;

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
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

    }

    private void loadData() {

        ArrayList<Employee> allEmployees = new ArrayList<Employee>();
        for (Employee emp: allEmployees) {
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
//            row.add(emp.getId());
//            row.add(emp.getFname()));
//            row.add(emp.getDept());
//            row.add(emp.getSalary());
            printData.add(row);
        }
        Utility.initPDFExprot(getStage(), printData);
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }


}
