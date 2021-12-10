/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.registration.employees;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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
    private void handleRemoveBtn(ActionEvent event) {


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
    private void updateFields(ActionEvent event) {
            clearCache();
        String id = empId.getText();
//        if (id != null && DbQuery.isEmployee(id)) {
//            Employee emp;
//            emp = DbQuery.getEmployee(id);
//            name.setText(emp.getFname() + emp.getLname());
//            contact.setText(emp.getContact());
//            dob.setText(emp.getDob().toString());
//            email.setText(emp.getEmail());
//            address.setText(emp.getAddress());
//            domain.setText(emp.getDept());
//
//        }
//        else {
//            dept.setText("Invalid Member ID");
//        }
    }
    
}
