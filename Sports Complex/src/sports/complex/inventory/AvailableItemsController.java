package sports.complex.inventory;

import Classes.AvailableItem;
import Classes.InventoryItem;
import Database.DbQuery;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AvailableItemsController implements Initializable {

    @FXML
    private TableView<AvailableItem> tableView;
    @FXML
    private TableColumn<AvailableItem, String> itemCol;
    @FXML
    private TableColumn<AvailableItem, String> quantityCol;
    ObservableList<AvailableItem> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvailableItemsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AvailableItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {

        itemCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadData() throws ClassNotFoundException, SQLException {

        ArrayList<AvailableItem> items = new ArrayList<AvailableItem>();
        items = DbQuery.displayAvailableItems();
        for (AvailableItem item : items) {
            list.add(item);
        }
        tableView.setItems(list);
    }

}
