package sports.complex.finance;

import Classes.InventoryItem;
import Classes.Member;
import Classes.Utility;
import Database.DbQuery;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class MembershiptransactionsController implements Initializable {

    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> totalCol;
    @FXML
    private TableColumn<Member, Date> dateCol;
    @FXML
    private Label totalLabel;

    ObservableList<Member> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
            totalLabel.setText("Rs. " + (DbQuery.getMemTransTotal()));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MembershiptransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MembershiptransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleExportPdf(ActionEvent event) {
        List<List> printData = new ArrayList<>();
        String[] headers = {"         id         ", "        Name       ", "        Total      ", "       Date       "};
        printData.add(Arrays.asList(headers));
        for (Member trans : list) {
            List<String> row = new ArrayList<>();
            row.add(trans.getMember_id());
            row.add(trans.getFname());
            row.add(Integer.toString(trans.getAmount()));
            row.add(trans.getDuedate().toString());

            printData.add(row);
        }
        Utility.initPDFExprot(getStage(), printData);
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("duedate"));

    }

    private void loadData() throws ClassNotFoundException, SQLException {

        ArrayList<Member> mems = new ArrayList<Member>();
        mems = DbQuery.viewMemTrans();
        for (Member m : mems) {
            list.add(m);
        }
        tableView.setItems(list);
    }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

}
