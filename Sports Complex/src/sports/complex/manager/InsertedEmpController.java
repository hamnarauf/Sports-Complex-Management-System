package sports.complex.manager;

import Classes.Employee;
import Database.DbQuery;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class InsertedEmpController implements Initializable {

    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> idCol;
    @FXML
    private TableColumn<Employee, String> cnicCol;
    @FXML
    private TableColumn<Employee, String> deptCol;
    @FXML
    private TableColumn<Employee, String> timeCol;

    ObservableList<Employee> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();

        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(InsertedEmpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertedEmpController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        cnicCol.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    public void loadData() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<Employee>();
        allEmployee = DbQuery.displayInsertedEmp();
        for (Employee employee : allEmployee) {
            list.add(employee);
        }
        tableView.setItems(list);
    }

}
