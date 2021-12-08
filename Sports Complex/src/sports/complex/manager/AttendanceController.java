/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.manager;

import com.jfoenix.controls.JFXComboBox;
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
public class AttendanceController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXComboBox<?> filter;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> contactCol;
    @FXML
    private TableColumn<?, ?> emailCol;
    @FXML
    private TableColumn<?, ?> deptCol;
    @FXML
    private TableColumn<?, ?> roleCol;
    @FXML
    private TableColumn<?, ?> attendanceCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
