package sports.complex.finance.ReqFunds;

import Classes.*;
import Database.DbQuery;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refresh();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReqFundsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {
        purposeCol.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        sportCol.setCellValueFactory(new PropertyValueFactory<>("sport"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        ObservableList<Repair> list = FXCollections.observableArrayList();
        ArrayList<Repair> allRepairs = new ArrayList<Repair>();
        allRepairs = DbQuery.getRepairs();
        for (Repair repair : allRepairs) {
            list.add(repair);
        }
        tableView.setItems(list);
    }

//    private Repair getRow() {
//        return tableView.getSelectionModel().getSelectedItem();
//    }
    @FXML
    private void handleAllocateBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Repair selectedRepair = (Repair) Utility.getRow((TableView<Object>) (Object) tableView);

        if (selectedRepair == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
            DbQuery.allocateFunds(selectedRepair);
            refresh();
        }
    }

    @FXML
    private void handleRejectBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Repair selectedRepair = (Repair) Utility.getRow((TableView<Object>) (Object) tableView);

        if (selectedRepair == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
            DbQuery.refuseFunds(selectedRepair);
            refresh();
        }
    }

    private void refresh() throws SQLException, ClassNotFoundException {
        initCol();
        loadData();
    }

}
