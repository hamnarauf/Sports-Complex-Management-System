package sports.complex.registration.employees;

import Classes.*;
import Database.DbQuery;
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
public class RemoveCoachController implements Initializable {

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
    private Label domain;
    @FXML
    private JFXTextField coachId;

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
        domain.setText("");
    }

    @FXML
    private void updateFields(ActionEvent event) {
               clearCache();
        String id = coachId.getText();
//        if (id != null && DbQuery.isCoach(id)) {
//            Coach coach;
//            coach = DbQuery.getCoach(id);
//            name.setText(coach.getFname() + coach.getLname());
//            contact.setText(coach.getContact());
//            dob.setText(coach.getDob().toString());
//            email.setText(coach.getEmail());
//            address.setText(coach.getAddress());
//            domain.setText(coach.getDomain());
//
//        }
//        else {
//            domain.setText("Invalid Member ID");
//        }
    }

}
