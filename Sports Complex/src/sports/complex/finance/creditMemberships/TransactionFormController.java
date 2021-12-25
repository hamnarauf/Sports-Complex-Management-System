package sports.complex.finance.creditMemberships;

import Classes.Member;
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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class TransactionFormController implements Initializable {

    @FXML
    private Label totalAmount;
    @FXML
    private Label date;
    @FXML
    private Label name;
    @FXML
    private Label formId;
    @FXML
    private Label fees;

    public static String id;
    @FXML
    private BorderPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Member mem = DbQuery.detailsTransForm(id);
            setLabels(mem);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransactionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setLabels(Member m) {
        formId.setText(m.getMember_id());
        name.setText(m.getFname() + " " + m.getLname());
        date.setText("08/01/2022");
        totalAmount.setText("Rs. " + Integer.toString(m.getAmount()));
        fees.setText("Rs. 4000");

    }

    @FXML
    private void handlePaidBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        DbQuery.creditMembership(id);
        AlertMaker.showAlert("Success", "Fees paid successfully.");
        getStage().close();
    }
    
    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

}
