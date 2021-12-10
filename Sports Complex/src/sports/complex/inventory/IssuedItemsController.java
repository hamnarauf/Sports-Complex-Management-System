/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.inventory;

import Classes.InventoryLog;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    
}
