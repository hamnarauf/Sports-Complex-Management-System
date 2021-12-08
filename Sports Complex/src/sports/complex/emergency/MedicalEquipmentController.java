/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.emergency;

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
public class MedicalEquipmentController implements Initializable {

    @FXML
    private TableColumn<?, ?> totalCol;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> equipmentCol;
    @FXML
    private TableColumn<?, ?> inUseCol;
    @FXML
    private TableColumn<?, ?> availableCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
