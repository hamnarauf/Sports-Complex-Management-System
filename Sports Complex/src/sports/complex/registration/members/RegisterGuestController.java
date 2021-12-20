package sports.complex.registration.members;

import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sports.complex.alert.AlertMaker;
import Classes.*;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RegisterGuestController implements Initializable {

    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField cnic;
    @FXML
    private JFXTextField memberId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRegisterBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String fname = firstName.getText();
        String lname = lastName.getText();
        String CNIC = cnic.getText();
        String id = memberId.getText();

        if (fname.equals("") || lname.equals("") || CNIC.equals("") || id.equals("")) {
            AlertMaker.showAlert("Try Again", "Please Enter all feilds");
        } else {
            if (DbQuery.isMember(DbQuery.getMemberCnic(id))) {
                Guest g = new Guest(CNIC, id, fname, lname);
                try {
                    DbQuery.registerGuest(g);
                    AlertMaker.showAlert("Success", "Registeration Successfull.");
                    clearCache() ;
                } catch (Exception e) {
                    AlertMaker.showAlert("Error", "Invalid Cnic");
                }

            } else {
                AlertMaker.showAlert("Try Again", "Invalid Member id");
            }
        }
    }

    private void clearCache() {
        firstName.setText("");
        lastName.setText("");
        cnic.setText("");
        memberId.setText("");

    }

}
