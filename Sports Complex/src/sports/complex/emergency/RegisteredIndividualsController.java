/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.emergency;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RegisteredIndividualsController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField bloodGroup;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> genderCol;
    @FXML
    private TableColumn<?, ?> bloodGCol;
    @FXML
    private TableColumn<?, ?> allergiesCol;
    @FXML
    private TableColumn<?, ?> contactCol;
    @FXML
    private TableColumn<?, ?> emerContact;
    @FXML
    private TableView<?> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
