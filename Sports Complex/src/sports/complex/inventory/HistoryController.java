package sports.complex.inventory;

import Classes.*;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
public class HistoryController implements Initializable{

    @FXML
    private JFXTextField search;
    @FXML
    private TableView<InventoryItem> tableView;
    @FXML
    private TableColumn<InventoryItem, String> idCol;
    @FXML
    private TableColumn<InventoryItem, String> nameCol;
    @FXML
    private TableColumn<InventoryItem, String> itemName;
    @FXML
    private TableColumn<InventoryItem, String> quantityCol;
    @FXML
    private TableColumn<InventoryItem, Time> issueTimeCol;
    @FXML
    private TableColumn<InventoryItem, Time> returnedTimeCol;
    @FXML
    private TableColumn<InventoryItem, Boolean> damageCol;

    ObservableList<InventoryItem> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
            filterByName();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        issueTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        returnedTimeCol.setCellValueFactory(new PropertyValueFactory<>("returnedTime"));
        damageCol.setCellValueFactory(new PropertyValueFactory<>("damaged"));
    }

    private void loadData() throws ClassNotFoundException, SQLException {

        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
        items = DbQuery.displayHistory();
        for (InventoryItem item : items) {
            list.add(item);
        }
        tableView.setItems(list);
    }
    
       private void filterByName() {
//        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<InventoryItem> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<InventoryItem> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);

    }

}
