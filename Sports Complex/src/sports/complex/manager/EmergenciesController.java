package sports.complex.manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.*;
import Database.DbQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class EmergenciesController implements Initializable {

    @FXML
    private TableView<Emergency> tableView;
    @FXML
    private TableColumn<Emergency, String> facilityCol;
    @FXML
    private TableColumn<Emergency, String> idCol;
    @FXML
    private TableColumn<Emergency, String> nameCol;
    @FXML
    private TableColumn<Emergency, String> probCol;
    @FXML
    private TableColumn<Emergency, String> resolvedCol;
    
    ObservableList<Emergency> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmergenciesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        facilityCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        probCol.setCellValueFactory(new PropertyValueFactory<>("problem"));
        resolvedCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadData() throws ClassNotFoundException {

        ArrayList<Emergency> items = new ArrayList<Emergency>();
        try {
            items = DbQuery.displayEmergency();
        } catch (SQLException ex) {
            Logger.getLogger(EmergenciesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Emergency item : items) {
            list.add(item);
        }
        tableView.setItems(list);
    }

}
