package sports.complex.registration.employees;

import Classes.CoachSchedule;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
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
        LocalTime loc = getLocalTime(new Time(9, 3, 4));
        System.out.println(isCorrectTime(loc));
    }

    @FXML
    private void handleSaveBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String coach_id = id.getText();

        if (coach_id.equals("")) {
            AlertMaker.showAlert("Try Again", "Please enter coach id");
        } else {
//            try {
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
                if (!isCorrectTime(getLocalTime(ms)) || !isCorrectTime(getLocalTime(tus)) || !isCorrectTime(getLocalTime(ws))
                        || !isCorrectTime(getLocalTime(fs)) || !isCorrectTime(getLocalTime(ths)) || !isCorrectTime(getLocalTime(se))
                        || !isCorrectTime(getLocalTime(ss)) || !isCorrectTime(getLocalTime(me)) || !isCorrectTime(getLocalTime(tue))
                        || !isCorrectTime(getLocalTime(we)) || !isCorrectTime(getLocalTime(the)) || !isCorrectTime(getLocalTime(fe))) {
                    AlertMaker.showAlert("Error", "Time should be between working hours of Sports Complex");
                } else {
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
                        AlertMaker.showAlert("Success", "Working hours have been set successfully.");
                        clearCache();
                    } else {
                        AlertMaker.showAlert("Invalid", "Invalid Coach Id");
                    }

                }

//            } catch (Exception e) {
//                AlertMaker.showAlert("Try Again", "Please enter all fields");
//            }
        }
    }

    private void clearCache() {
        monStart.setValue(null);
        tuesStart.setValue(null);
        wedStart.setValue(null);
        friStart.setValue(null);
        thursStart.setValue(null);
        satStart.setValue(null);
        monEnd.setValue(null);
        tuesEnd.setValue(null);
        wedEnd.setValue(null);
        thursEnd.setValue(null);
        friEnd.setValue(null);
        satEnd.setValue(null);

        id.setText("");

    }

    private LocalTime getLocalTime(Time time) {
        LocalTime loc = LocalTime.of(time.getHours(), time.getMinutes(), time.getSeconds());
        return loc;
    }

    private boolean isCorrectTime(LocalTime time) {
        LocalTime closeTime = LocalTime.of(21, 00, 00);
        LocalTime openTime = LocalTime.of(9, 00, 00);
        return (time.isBefore(closeTime) && time.isAfter(openTime));
    }
}
