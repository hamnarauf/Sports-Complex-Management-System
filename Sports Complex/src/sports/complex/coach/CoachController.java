/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class CoachController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private TableColumn<?, ?> scheduleDayCol;
    @FXML
    private TableColumn<?, ?> scheduleDateCol;
    @FXML
    private TableColumn<?, ?> scheduleStartCol;
    @FXML
    private TableColumn<?, ?> scheduleEndCol;
    @FXML
    private TableColumn<?, ?> scheduleattendeesCol;
    @FXML
    private TableColumn<?, ?> scheduleDomainCol;
    @FXML
    private TableColumn<?, ?> traineeIdCol;
    @FXML
    private TableColumn<?, ?> traineeNameCol;
    @FXML
    private TableColumn<?, ?> traineeDomainCol;
    @FXML
    private TableColumn<?, ?> traineeTournamentCol;
    @FXML
    private TableView<?> scheduleTable;
    @FXML
    private Tab traineesTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
