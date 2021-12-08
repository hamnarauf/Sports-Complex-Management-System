/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.registration.employees;

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
public class AllCoachesController implements Initializable {

    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> cnicCol;
    @FXML
    private TableColumn<?, ?> genderCol;
    @FXML
    private TableColumn<?, ?> dobCol;
    @FXML
    private TableColumn<?, ?> contactCol;
    @FXML
    private TableColumn<?, ?> emailCol;
    @FXML
    private TableColumn<?, ?> domainCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
