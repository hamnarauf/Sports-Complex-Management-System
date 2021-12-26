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
import javafx.scene.control.ComboBox;
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
    private ComboBox<Time> timeCombo;
    @FXML
    private ComboBox<String> dayCombo;

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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterTraineeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void populateSportsCombo() throws SQLException, ClassNotFoundException {
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

    void populateTimeCombo(String sport, String day) throws SQLException, ClassNotFoundException {
        ArrayList<Time> times = new ArrayList<Time>();

        timeCombo.getSelectionModel().clearSelection();
        timeCombo.getItems().clear();
//        timeCombo.getItems().add(new Time(9, 0, 0));
//        timeCombo.getItems().add(new Time(10, 0, 0));
//        timeCombo.getItems().add(new Time(13, 0, 0));
        times = DbQuery.getTime(day, sport);
        for (Time time : times) {

            timeCombo.getItems().add(time);
        }
    }

    @FXML
    private void handleRegisterBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String tId = id.getText();
        String cnic = DbQuery.getMemberCnic(tId);
        String sport = sportCombo.getValue();
        Time time = timeCombo.getValue();
        String day = dayCombo.getValue();

        if (tId.equals("") || sport.equals("") || time.equals("")) {
            AlertMaker.showAlert("Try Again", "Please Enter all feilds");
        } else {
            if (DbQuery.isMember(cnic)) {
                Trainee t = new Trainee(tId, sport, time, day);
                try{
                DbQuery.registerTrainee(t);
                AlertMaker.showAlert("Registeration successfull", "Success");
                 clearCache();
                }
                catch(Exception e){
                       AlertMaker.showAlert("Error", "Member already registered for this class.");
                }

            } else {
                AlertMaker.showAlert("Try Again", "Member id does not exists");
            }
        }

    }

    @FXML
    private void handleDay(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (sportCombo.getValue() != null) {
            populateTimeCombo(dayCombo.getValue(), sportCombo.getValue());
        }
    }

    @FXML
    private void handleTime(MouseEvent event) {
    }

    private void clearCache() {
        dayCombo.setValue(null);
        sportCombo.setValue(null);
        timeCombo.getSelectionModel().clearSelection();
        timeCombo.getItems().clear();
        id.setText("");

    }
}
