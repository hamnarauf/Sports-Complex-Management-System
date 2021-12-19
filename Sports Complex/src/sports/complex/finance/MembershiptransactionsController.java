package sports.complex.finance;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class MembershiptransactionsController implements Initializable {

    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> feesCol;
    @FXML
    private TableColumn<?, ?> fineCol;
    @FXML
    private TableColumn<?, ?> extrasCol;
    @FXML
    private TableColumn<?, ?> overdueCol;
    @FXML
    private TableColumn<?, ?> totalCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private Label totalLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleExportPdf(ActionEvent event) {
    }
    
}
