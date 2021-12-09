package sports.complex.manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.*;
import java.util.ArrayList;
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
    private TableView<MedicalLog> tableView;
    @FXML
    private TableColumn<MedicalLog, String> facilityCol;
    @FXML
    private TableColumn<MedicalLog, String> idCol;
    @FXML
    private TableColumn<MedicalLog, String> nameCol;
    @FXML
    private TableColumn<MedicalLog, String> probCol;
    @FXML
    private TableColumn<MedicalLog, String> resolvedCol;
    ObservableList<MedicalLog> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Membname"));
        facilityCol.setCellValueFactory(new PropertyValueFactory<>("item"));
        probCol.setCellValueFactory(new PropertyValueFactory<>("problem"));
        resolvedCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadData() {

        ArrayList<MedicalLog> items = new ArrayList<MedicalLog>();
        for (MedicalLog item : items) {
            list.add(item);
        }
        tableView.setItems(list);
    }

}
