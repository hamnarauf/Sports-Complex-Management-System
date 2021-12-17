package sports.complex.registration.members;

import Classes.*;
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
public class RemoveMemberController implements Initializable {

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
    private JFXTextField memId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRemoveBtn(ActionEvent event) throws SQLException {
        DbQuery.removeMember(memId.getText());
        AlertMaker.showAlert("Success", "Member Removed Successfully");

    }

    public void clearCache() {
        name.setText("");
        contact.setText("");
        dob.setText("");
        email.setText("");
        address.setText("");
    }

    @FXML
    private void updateFields(ActionEvent event) throws SQLException {
        clearCache();
        String id = memId.getText();
        if (id != null && DbQuery.isMember(DbQuery.getMemberCnic(id))) {
            Person mem;
            mem = DbQuery.removeMemberDetails(id);
            name.setText(mem.getFname() + " " + mem.getLname());
            contact.setText(mem.getContactNo());
            dob.setText(mem.getDob().toString());
            email.setText(mem.getEmail());
            address.setText(mem.getAddress());
        } else {
            contact.setText("Invalid Member ID");
        }
    }

}
