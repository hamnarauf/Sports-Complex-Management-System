package sports.complex.emergency;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.InventoryItem;
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
public class MedicalEquipmentController  implements Initializable {

    @FXML
    private TableView<InventoryItem> tableView;
    @FXML
    private TableColumn<InventoryItem, String> equipmentCol;
    @FXML
    private TableColumn<InventoryItem, String> totalCol;
    @FXML
    private TableColumn<InventoryItem, String> inUseCol;
    @FXML
    private TableColumn<InventoryItem, String> availableCol;

    ObservableList<InventoryItem> list = FXCollections.observableArrayList();

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedicalEquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MedicalEquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initCol() {

        equipmentCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        inUseCol.setCellValueFactory(new PropertyValueFactory<>("useQuantity"));
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
    }

    private void loadData() throws ClassNotFoundException, SQLException {

        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
        items = DbQuery.getMedicalEquipment();
        for (InventoryItem item : items) {
            list.add(item);
        }
        tableView.setItems(list);
    }

}
