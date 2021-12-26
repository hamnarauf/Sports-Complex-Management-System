package sports.complex.finance;

import Classes.Transaction;
import Classes.Utility;
import Database.DbQuery;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ExtrasController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private Label totalLabel;

    @FXML
    private TableView<Transaction> tableView;
    @FXML
    private TableColumn<Transaction, String> idCol;
    @FXML
    private TableColumn<Transaction, String> typeCol;
    @FXML
    private TableColumn<Transaction, String> paymentCol;
    ObservableList<Transaction> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
            updateLabel();
        } catch (SQLException ex) {
            Logger.getLogger(ExtrasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExtrasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    private void updateLabel() throws SQLException, ClassNotFoundException {
        String total = DbQuery.getExtraTransTotal();
        totalLabel.setText("Rs. " + total);
    }

    private void loadData() throws SQLException, ClassNotFoundException {

        ArrayList<Transaction> allTrans = new ArrayList<Transaction>();
        allTrans = DbQuery.viewTransExtras();
        for (Transaction trans : allTrans) {
            list.add(trans);
        }
        tableView.setItems(list);
    }

    @FXML
    private void handleExportPdf(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"         id         ", "        Type       ", "       Payment       "};
        printData.add(Arrays.asList(headers));
        for (Transaction trans : list) {
            List<String> row = new ArrayList<>();
            row.add(trans.getId());
            row.add(trans.getType());
            row.add(trans.getAmount());
            printData.add(row);
        }
        Utility.initPDFExprot(getStage(), printData);
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

}
