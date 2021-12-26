package sports.complex.menu;

import Classes.Employee;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class EditProfileController implements Initializable {
    
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField dob;
    @FXML
    private JFXTextField contact;
    @FXML
    private JFXTextField contactEmer;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField dept;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField gender;
    @FXML
    private JFXTextField bloodGroup;
    @FXML
    private JFXTextField allergies;
    @FXML
    private JFXTextField cnic;
    
    public static String emp_id;
    @FXML
    private JFXTextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Employee e = DbQuery.getEmployee(emp_id);
            name.setText(e.getFname() + " " + e.getLname());
            dob.setText(e.getDob().toString());
            contact.setText(e.getContactNo());
            contactEmer.setText(e.getEmerContact());
            address.setText(e.getAddress());
            dept.setText(e.getDeptName());
            email.setText(e.getEmail());
            gender.setText(e.getGen().toString());
            bloodGroup.setText(e.getBloodGrp());
            allergies.setText(e.getAllergy());
            cnic.setText(e.getCnic());
            id.setText(emp_id);
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setId(String id) {
        emp_id = id;
    }
    
    public static String getId() {
        return emp_id;
    }
    
    @FXML
    private void handleEditBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Employee emp = new Employee();
        emp.setEmp_id(emp_id);
        emp.setEmail(email.getText());
        emp.setContactNo(contact.getText());
        emp.setEmerContact(contactEmer.getText());
        emp.setAllergy(allergies.getText());
        emp.setAddress(address.getText());
        
        try {
            DbQuery.editProfile(emp);
            AlertMaker.showAlert("Success", "Profile Updated Successfully");
        } catch (Exception e) {
            AlertMaker.showAlert("Error", "Invalid Data.");
        }
        
    }
    
}
