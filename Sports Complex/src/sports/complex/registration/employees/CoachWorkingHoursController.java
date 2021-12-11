package sports.complex.registration.employees;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSaveBtn(ActionEvent event) {
    }
    
}
