package sports.complex.menu;

import Classes.Employee;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
    private JFXTextField id;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField gender;
    @FXML
    private JFXTextField role;
    @FXML
    private JFXTextField bloodGroup;
    @FXML
    private JFXTextField allergies;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField cnic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        name.setText();
//        dob.setText();
//        contact.setText();
//        contactEmer.setText();
//        address.setText();
//        dept.setText();
//        id.setText();
//        email.setText();
//        gender.setText();
//        role.setText();
//        bloodGroup.setText();
//        allergies.setText();
//        salary.setText();
//        cnic.setText();
    }

    @FXML
    private void handleEditBtn(ActionEvent event) {
        Employee emp = new Employee();
        emp.setEmail(email.getText());
        emp.setContactNo(contact.getText());
        emp.setEmerContact(contactEmer.getText());
        emp.setAllergy(allergies.getText());
        emp.setAddress(address.getText());
        
//        DbQuery.editProfile(emp);

    }

}
