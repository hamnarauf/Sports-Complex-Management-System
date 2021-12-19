package sports.complex.registration.employees;

import Classes.CoachSchedule;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class CoachWorkingHoursController implements Initializable {

    @FXML
    private JFXTextField id;
    @FXML
    private JFXTimePicker monStart;
    @FXML
    private JFXTimePicker tuesStart;
    @FXML
    private JFXTimePicker wedStart;
    @FXML
    private JFXTimePicker friStart;
    @FXML
    private JFXTimePicker thursStart;
    @FXML
    private JFXTimePicker satStart;
    @FXML
    private JFXTimePicker monEnd;
    @FXML
    private JFXTimePicker tuesEnd;
    @FXML
    private JFXTimePicker wedEnd;
    @FXML
    private JFXTimePicker thursEnd;
    @FXML
    private JFXTimePicker friEnd;
    @FXML
    private JFXTimePicker satEnd;

    CoachSchedule[] cs = new CoachSchedule[6];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) {
        String coach_id = id.getText();

        if (coach_id.equals("")) {
            AlertMaker.showAlert("Try Again", "Please enter coach id");
        } else {
            try {
                Time ms = Time.valueOf(monStart.getValue());
                Time tus = Time.valueOf(tuesStart.getValue());
                Time ws = Time.valueOf(wedStart.getValue());
                Time fs = Time.valueOf(friStart.getValue());
                Time ths = Time.valueOf(thursStart.getValue());
                Time ss = Time.valueOf(satStart.getValue());
                Time me = Time.valueOf(monEnd.getValue());
                Time tue = Time.valueOf(tuesEnd.getValue());
                Time we = Time.valueOf(wedEnd.getValue());
                Time the = Time.valueOf(thursEnd.getValue());
                Time fe = Time.valueOf(friEnd.getValue());
                Time se = Time.valueOf(satEnd.getValue());

                if (DbQuery.isEmployee(coach_id)) {
                    CoachSchedule mon = new CoachSchedule(coach_id, "Monday", ms, me);
                    CoachSchedule tues = new CoachSchedule(coach_id, "Tuesday", tus, tue);
                    CoachSchedule wed = new CoachSchedule(coach_id, "Wednesday", ws, we);
                    CoachSchedule thurs = new CoachSchedule(coach_id, "Thursday", ths, the);
                    CoachSchedule fri = new CoachSchedule(coach_id, "Friday", fs, fe);
                    CoachSchedule sat = new CoachSchedule(coach_id, "Saturday", ss, se);

                    cs[0] = mon;
                    cs[1] = tues;
                    cs[2] = wed;
                    cs[3] = thurs;
                    cs[4] = fri;
                    cs[5] = sat;
                    DbQuery.editWorkingHrs(cs);
                } else {
                    AlertMaker.showAlert("Invalid", "Invalid Coach Id");
                }
            } catch (Exception e) {
                AlertMaker.showAlert("Try Again", "Please enter all fields");
            }
        }
    }
}
