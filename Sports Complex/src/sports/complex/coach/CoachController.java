package sports.complex.coach;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utilities.StageLoader;
import Classes.CoachSchedule;
import Classes.Trainee;
import Database.DbQuery;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class CoachController implements Initializable {

    @FXML
    private BorderPane rootPane;

    // Coach schedule table
    @FXML
    private TableView<CoachSchedule> scheduleTable;
    @FXML
    private TableColumn<CoachSchedule, String> scheduleDayCol;
    @FXML
    private TableColumn<CoachSchedule, Time> scheduleStartCol;
    @FXML
    private TableColumn<CoachSchedule, Time> scheduleEndCol;
    @FXML
    private TableColumn<CoachSchedule, String> scheduleattendeesCol;
    @FXML
    private TableColumn<CoachSchedule, String> scheduleDomainCol;
    
    ObservableList<CoachSchedule> scheduleList = FXCollections.observableArrayList();

    // trainee table
    @FXML
    private TableView<Trainee> traineeTable;
    @FXML
    private TableColumn<Trainee, String> traineeDomainCol;
    @FXML
    private TableColumn<Trainee, String> traineeFNameCol;
    @FXML
    private TableColumn<Trainee, String> traineeLNameCol;
    @FXML
    private TableColumn<Trainee, String> traineeIdCol;
    
    ObservableList<Trainee> traineeList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initScheduleCol();
        initTraineeCol();
        loadScheduleData();
        loadTraineeData();
    }

    private void initScheduleCol() {
        scheduleDayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        scheduleStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        scheduleEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        scheduleattendeesCol.setCellValueFactory(new PropertyValueFactory<>("totalAttendees"));
        scheduleDomainCol.setCellValueFactory(new PropertyValueFactory<>("domain"));
    }

    private void initTraineeCol() {
        traineeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        traineeFNameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        traineeLNameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        traineeDomainCol.setCellValueFactory(new PropertyValueFactory<>("domain"));
    }

    private void loadScheduleData() {

        ArrayList<CoachSchedule> schedules = new ArrayList<CoachSchedule>();
//        schedules = DbQuery.getCoachSchedule(coach_id);
        for (CoachSchedule schedule : schedules) {
            scheduleList.add(schedule);
        }
        scheduleTable.setItems(scheduleList);
    }
    
    private void loadTraineeData() {

        ArrayList<Trainee> trainees = new ArrayList<Trainee>();
//        trainees = DbQuery.viewTrainees(coach_id);
        for (Trainee trainee : trainees) {
            traineeList.add(trainee);
        }
        traineeTable.setItems(traineeList);
    }

    @FXML
    private void menuChangePassword(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/changePassword.fxml"), "Change Password", null);

    }

    @FXML
    private void menuEditProfile(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/menu/editProfile.fxml"), "Edit Profile", null);

    }

    private void menuViewNotice(ActionEvent event) {
        StageLoader.loadWindow(getClass().getResource("/sports/complex/registration/menu/viewNotice/viewNotice.fxml"), "Notices", null);

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

}
