package sports.complex.registration.employees;

import Classes.Employee;
import Classes.Person;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RemoveEmployeeController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label contact;
    @FXML
    private Label dob;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label dept;
    @FXML
    private JFXTextField empId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRemoveBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (DbQuery.isEmployee(empId.getText())) {
            DbQuery.removeEmp(empId.getText());
        }
        AlertMaker.showAlert("Success", "Employee removed succesfully.");
        clearCache();

    }

    public void clearCache() {
        name.setText("");
        contact.setText("");
        dob.setText("");
        email.setText("");
        address.setText("");
        dept.setText("");
    }

    @FXML
    private void updateFields(ActionEvent event) throws SQLException, ClassNotFoundException {
        clearCache();
        String id = empId.getText();
        if (id != null && DbQuery.isEmployee(id)) {
            Person emp;
            emp = DbQuery.removeEmployeeDetails(DbQuery.getEmpCnic(id));
            name.setText(emp.getFname() + emp.getLname());
            contact.setText(emp.getContactNo());
//            dept.setText(emp.getDeptName());
            dob.setText(emp.getDob().toString());
            email.setText(emp.getEmail());
            address.setText(emp.getAddress());

        } else {
            dept.setText("Invalid Employee ID");
        }
    }

}
