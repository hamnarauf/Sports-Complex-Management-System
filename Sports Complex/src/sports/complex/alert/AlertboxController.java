package sports.complex.alert;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AlertboxController implements Initializable {

    @FXML
    private Label alertContent;
    @FXML
    private Button button2;
    @FXML
    private Label alertlabel;
    @FXML
    public DialogPane dialogPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setTitle(String title){
        alertlabel.setText(title);
    }
    
    public void setContent(String cont){
        alertContent.setText(cont);
    }

    @FXML
    private void handleOkBtn(ActionEvent event) {
        getStage().close();
    }
    
        
    private Stage getStage() {
        return (Stage) dialogPane.getScene().getWindow();
    }

    
}
