package sports.complex.manager;

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
public class RepairsController implements Initializable {

    @FXML
    private TableView<Repair> tableView;
    @FXML
    private TableColumn<Repair, String> purposeCol;
    @FXML
    private TableColumn<Repair, String> deptCol;
    @FXML
    private TableColumn<Repair, String> expenditureCol;

    ObservableList<Repair> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        refresh();
        initCol();
        try {
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RepairsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RepairsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {

        purposeCol.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("sport"));
        expenditureCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    private void loadData() throws ClassNotFoundException, SQLException {

        ArrayList<Repair> allRepairs = new ArrayList<Repair>();
        allRepairs = DbQuery.displayRepairs();
        for (Repair repair : allRepairs) {
            list.add(repair);
        }
        tableView.setItems(list);
    }

}
