package sports.complex.finance.ReqFunds;

import Classes.*;
import Database.DbQuery;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ReqFundsController implements Initializable {

    @FXML
    private TableView<Repair> tableView;
    @FXML
    private TableColumn<Repair, String> purposeCol;
    @FXML
    private TableColumn<Repair, String> amountCol;
    @FXML
    private TableColumn<Repair, String> sportCol;

    ObservableList<Repair> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
    }

    private void initCol() {

        purposeCol.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        sportCol.setCellValueFactory(new PropertyValueFactory<>("sport"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    private void loadData() {

        ArrayList<Repair> allRepairs = new ArrayList<Repair>();
        for (Repair repair : allRepairs) {
            list.add(repair);
        }
        tableView.setItems(list);
    }

    private Repair getRow() {
        return tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleAllocateBtn(ActionEvent event) {
        Repair selectedRepair = getRow();

        if (selectedRepair == null) {
            AlertMaker.showErrorMessage("Error", "No Row selected");

        } else {
//               DbQuery.allocateFund();
             refresh();
        }

       

    }

    @FXML
    private void handleRejectBtn(ActionEvent event) {
                Repair selectedRepair = getRow();

        if (selectedRepair == null) {
            AlertMaker.showErrorMessage("Error", "No Row selected");

        } else {
//               DbQuery.refuseFund();
             refresh();
        }
    }

    private void refresh() {
        initCol();
        loadData();

    }

}
