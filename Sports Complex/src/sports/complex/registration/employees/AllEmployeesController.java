/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.registration.employees;

import Classes.Employee;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AllEmployeesController implements Initializable {

    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> idCol;
    @FXML
    private TableColumn<Employee, String> fnameCol;
    @FXML
    private TableColumn<Employee, String> lnameCol;
    @FXML
    private TableColumn<Employee, String> cnicCol;
    @FXML
    private TableColumn<Employee, String> genderCol;
    @FXML
    private TableColumn<Employee, Date> dobCol;
    @FXML
    private TableColumn<Employee, String> contactCol;
    @FXML
    private TableColumn<Employee, String> emailCol;
    @FXML
    private TableColumn<Employee, String> deptCol;

    ObservableList<Employee> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    public void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        cnicCol.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));
    }

    public void loadData() {
        ArrayList<Employee> allEmployee = new ArrayList<Employee>();
        for (Employee employee : allEmployee) {
            list.add(employee);
        }
        tableView.setItems(list);
    }

}
