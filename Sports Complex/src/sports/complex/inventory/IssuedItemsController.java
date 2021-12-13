package sports.complex.inventory;

import Classes.Attendance;
import Classes.InventoryLog;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
public class IssuedItemsController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private TableView<InventoryLog> tableView;
    @FXML
    private TableColumn<InventoryLog, String> idCol;
    @FXML
    private TableColumn<InventoryLog, String> nameCol;
    @FXML
    private TableColumn<InventoryLog, String> itemName;
    @FXML
    private TableColumn<InventoryLog, String> quantityCol;
    @FXML
    private TableColumn<InventoryLog, String> timeCol;

    ObservableList<InventoryLog> list = FXCollections.observableArrayList();
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }
    
    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("memName"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("issueTime"));

    }

    private void loadData() {

        ArrayList<InventoryLog> items = new ArrayList<InventoryLog>();
        for (InventoryLog item : items) {
            list.add(item);
        }
        tableView.setItems(list);
    }
    
        private void filterByName() {
//        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<InventoryLog> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<InventoryLog> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);

    }
    
}
