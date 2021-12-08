/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.finance.creditMemberships;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class TransactionFormController implements Initializable {

    @FXML
    private Label totalAmount;
    @FXML
    private Label date;
    @FXML
    private Label name;
    @FXML
    private Label formId;
    @FXML
    private Label fees;
    @FXML
    private Label fine;
    @FXML
    private Label extras;
    @FXML
    private Label overdue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlePaidBtn(ActionEvent event) {
    }
    
}
