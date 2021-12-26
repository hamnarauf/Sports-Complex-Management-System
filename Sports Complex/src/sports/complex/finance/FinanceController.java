package sports.complex.finance;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.StageLoader;
import Database.DbQuery;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sports.complex.menu.ChangePasswordController;
import sports.complex.menu.EditProfileController;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class FinanceController implements Initializable {

    @FXML
    private FontAwesomeIconView requestedFundsIcon;
    @FXML
    private JFXButton requestedFundsLabel;
    @FXML
    private AnchorPane rootPane;
    public static String emp_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (DbQuery.hasReqRepairs()) {
                requestedFundsIcon.getStyleClass().add("notify");

            }
        } catch (SQLException ex) {
            Logger.getLogger(FinanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setId(String id) {
        emp_id = id;
    }

    public static String getId() {
        return emp_id;
    }

    @FXML
    private void menuChangePassword(ActionEvent event) {
        ChangePasswordController.setId(emp_id);
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/changePassword.fxml"), "Change Password", null);

    }

    @FXML
    private void menuEditProfile(ActionEvent event) {
        EditProfileController.setId(emp_id);
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/editProfile.fxml"), "Edit Profile", null);

    }

    @FXML
    private void menuViewNotice(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/viewNotice.fxml"), "Notices", null);

    }

    @FXML
    private void menuComplaint(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/registerComplaint.fxml"), "Complaint Box", null);

    }

    @FXML
    private void menusuggestion(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/registerSuggestion.fxml"), "Suggestion Box", null);

    }

    @FXML
    private void menuExit(ActionEvent event) {
        getStage().close();
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void loadMembersTrans(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("membership.fxml"), "Transaction", null);

    }

    @FXML
    private void loadEmployeeTrans(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("employees.fxml"), "Transaction", null);

    }

    @FXML
    private void loadFundsTrans(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("funds.fxml"), "Transaction", null);

    }

    @FXML
    private void loadExtrasTrans(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("extras.fxml"), "Transaction", null);

    }

    @FXML
    private void loadBillsTrans(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("bills.fxml"), "Transaction", null);

    }

    @FXML
    private void loadCreditMemberships(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/finance/creditMemberships/memberId.fxml"), "Credit Membership", null);

    }

    @FXML
    private void loadRequestedFunds(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/finance/ReqFunds/reqFunds.fxml"), "Requested Funds", null);

    }

    @FXML
    private void loadTransSummary(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("summary.fxml"), "Transaction", null);

    }

    @FXML
    private void menuLogout(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/login/login.fxml"), "Login", getStage(), false);

    }

}
