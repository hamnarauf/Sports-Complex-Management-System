package sports.complex.registration.members;

import Classes.Trainee;
import Database.DbQuery;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RegisterTraineeController implements Initializable {

    @FXML
    private JFXTextField id;
    @FXML
    private JFXComboBox<String> sportCombo;
    @FXML
    private JFXComboBox<Time> timeCombo;
    @FXML
    private JFXComboBox<String> dayCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateDaysCombo();
            populateSportsCombo();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTraineeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void populateSportsCombo() throws SQLException {
        ArrayList<String> sports = new ArrayList<String>();
        sports = DbQuery.getSportsList();
        for (String sport : sports) {
            sportCombo.getItems().add(sport);
        }

    }

    void populateDaysCombo() {

        dayCombo.getItems().add("Monday");
        dayCombo.getItems().add("Tuesday");
        dayCombo.getItems().add("Wednesday");
        dayCombo.getItems().add("Thursday");
        dayCombo.getItems().add("Friday");
        dayCombo.getItems().add("Saturday");
        dayCombo.getItems().add("Sunday");

    }

    void populateTimeCombo() throws SQLException {
        ArrayList<Time> times = new ArrayList<Time>();
        String sport = sportCombo.getValue();
        String day = dayCombo.getValue();

        if (sport != null && day != null) {
            times = DbQuery.getTime(sport, day);
            for (Time time : times) {
                timeCombo.getItems().add(time);
            }
        }
    }

    @FXML
    private void handleRegisterBtn(ActionEvent event) throws SQLException {
        String tId = id.getText();
        String sport = sportCombo.getValue();
        Time time = timeCombo.getValue();
        String day = dayCombo.getValue();

        if (tId == null || sport == null || time == null) {
            AlertMaker.showAlert("Try Again", "Please Enter all feilds");
        } else {
            if (DbQuery.isMember(tId)) {
                Trainee t = new Trainee(tId, sport, time, day);
                DbQuery.registerTrainee(t);
                AlertMaker.showAlert("Registeration successfull", "Success");

            } else {
                AlertMaker.showAlert("Try Again", "Member id does not exists");
            }
        }

    }

    @FXML
    private void handleTime(MouseEvent event) throws SQLException {
        populateTimeCombo();
    }

}
