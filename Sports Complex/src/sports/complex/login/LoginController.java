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

        } else {

            User user = DbQuery.checkLoginDetails(uname, pass);
            if (user != null) {
                System.out.println("login");

                switch (DbQuery.getDeptName(user.getDept_id())) {
                    case ("registration"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/registration.fxml"), "Registration", null);
                        break;
                    case ("attendant"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/attendant/attendant.fxml"), "Attendant", null);
                        break;
                    case ("coach"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/coach/coach.fxml"), "Coach", null);
                        break;
                    case ("emergency"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/emergency/emergency.fxml"), "Emergency", null);
                        break;
                    case ("finance"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/finance/finance.fxml"), "Finance", null);
                        break;
                    case ("inventory"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/inventory/inventory.fxml"), "Inventory", null);
                        break;
                    case ("maintenance"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/maintenance/maintenance.fxml"), "Maintenance", null);
                        break;
                    case ("manager"):
                        StageLoader.loadWindow(getClass().getResource("/sports/complex/manager/manager.fxml"), "Manager", null);
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
        pass_hidden.setText("");
        pass_text.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
