package sports.complex.manager;

import Database.DbQuery;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
public class IssueNoticeController implements Initializable {

    @FXML
    private JFXTextField heading;
    @FXML
    private JFXTextArea info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleIssueBtn(ActionEvent event) {
        if (heading.getText() == null || info.getText() == null) {
            AlertMaker.showAlert("Empty fields", "Please enter both fields");
        } else {
//            Notice n = new Notice(heading.getText(), info.getText());
//            DbQuery.issueNotice();
            AlertMaker.showAlert("Success", "Notice issued succesfully");
            clearCache();
        }
    }
    
    private void clearCache(){
        heading.setText("");
        info.setText("");
    
    }

}
