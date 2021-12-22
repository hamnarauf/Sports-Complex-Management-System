package sports.complex.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sports.complex.SportsComplex;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utilities.StageLoader;
import sports.complex.alert.AlertMaker;
import Database.DbQuery;
import Classes.User;
import java.sql.SQLException;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sports.complex.attendant.AttendantController;
import sports.complex.coach.CoachController;
import sports.complex.emergency.EmergencyController;
import sports.complex.finance.FinanceController;
import sports.complex.inventory.InventoryController;
import sports.complex.maintenance.MaintenanceController;
import sports.complex.manager.ManagerController;
import sports.complex.registration.RegistrationController;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField username;

    @FXML
    private JFXPasswordField pass_hidden;
    @FXML
    private JFXTextField pass_text;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private void handleLoginButton(ActionEvent event) throws ClassNotFoundException, SQLException {

        //fetching the entered data by user
        String uname = username.getText();
        String pass = pass_hidden.getText();

        //if user has not entered username or password
        if (uname.equals("") || pass.equals("")) {
            AlertMaker.showAlert("Try Again", "Please Enter username and password");
            clearPass();

        } else {

            User user = DbQuery.checkLoginDetails(uname, pass);
            if (user != null) {
                switch (DbQuery.getDeptName(user.getDept_id())) {
                    case ("registration"):
                        RegistrationController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/registration.fxml"), "Registration", getStage());
                        break;
                    case ("attendant"):
                        AttendantController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/attendant/attendant.fxml"), "Attendant", getStage());
                        break;
                    case ("coach"):
                        CoachController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/coach/coach.fxml"), "Coach", getStage());
                        break;
                    case ("emergency"):
                        EmergencyController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/emergency/emergency.fxml"), "Emergency", getStage());
                        break;
                    case ("finance"):
                        FinanceController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/finance/finance.fxml"), "Finance", getStage());
                        break;
                    case ("inventory"):
                        InventoryController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/inventory/inventory.fxml"), "Inventory", getStage());
                        break;
                    case ("maintenance"):
                        MaintenanceController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/maintenance/maintenance.fxml"), "Maintenance", getStage());
                        break;
                    case ("manager"):
                        ManagerController.setId(user.getEmp_id());
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/manager/manager.fxml"), "Manager", getStage());
                        break;
                }
            } else {
                AlertMaker.showAlert("Try Again", "Invalid username or password");
                clearDetails();
            }
        }
    }

    @FXML
    private void forgetPassword(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("enterUsername.fxml"), "Password Recovery", null);

    }

    @FXML
    private void passwordShow(MouseEvent event) {
        if (pass_hidden.isVisible()) {
            pass_text.setText(pass_hidden.getText());
            pass_text.setVisible(true);
            pass_hidden.setVisible(false);
            return;
        }
        pass_hidden.setText(pass_text.getText());
        pass_hidden.setVisible(true);
        pass_text.setVisible(false);
    }

    private void clearDetails() {
        username.setText("");
        clearPass();
    }

    private void clearPass() {
        pass_hidden.setText("");
        pass_text.setText("");
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
