package sports.complex.emergency;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.InventoryItem;
import java.util.ArrayList;
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
        loadData();
    }
    
    private void initCol() {

        equipmentCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        inUseCol.setCellValueFactory(new PropertyValueFactory<>("inUse"));
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
    }

    private void loadData() {

        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
        for (InventoryItem item : items) {
            list.add(item);
        }
        tableView.setItems(list);
    }

}
