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

    private PasswordField password;
    @FXML
    private JFXPasswordField pass_hidden;
    @FXML
    private JFXTextField pass_text;

    @FXML
    private void handleLoginButton(ActionEvent event) {

        //fetching the entered data by user
        String uname = username.getText();
        String pass = password.getText();

        //if user has not entered username or password
        if (uname.equals("") || pass.equals("")) {
//            AlertBox alertBox = new AlertBox("Username and password required", "Empty fields");

        } else {

////            Boolean success = Database.login(uname, pass);
//            if (success) {
//                SportsComplex app = new SportsComplex();
//                app.setStage("adminMain.fxml");
//
//            } else {
////                AlertBox alertBox = new AlertBox("Invalid Username or password.", "Invalid");
//                username.setText("");
//                password.setText("");
//
//            }
        }//end else
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void forgetPassword(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("passwordRecovery.fxml"), "Password Recovery", null);

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

}
