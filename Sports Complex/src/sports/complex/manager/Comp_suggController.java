package sports.complex.manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.*;
import Database.DbQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class Comp_suggController implements Initializable {

    @FXML
    private TableView<Report> complaintTable;
    @FXML
    private TableView<Report> suggTable;
    @FXML
    private TableColumn<Report, String> complaintCol;
    @FXML
    private TableColumn<Report, String> suggCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
    }

    private void initCol() {
        complaintCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        suggCol.setCellValueFactory(new PropertyValueFactory<>("details"));

    }

    private void loadData() throws ClassNotFoundException, SQLException {
        ObservableList<Report> complaintlist = FXCollections.observableArrayList();
        ObservableList<Report> suggList = FXCollections.observableArrayList();
        ArrayList<Report> complaints = new ArrayList<Report>();
        complaints = DbQuery.displayComplaints();
        for (Report complaint : complaints) {
            complaintlist.add(complaint);
        }
        complaintTable.setItems(complaintlist);

        ArrayList<Report> suggs = new ArrayList<Report>();
        suggs = DbQuery.displaySuggestions();
        for (Report sugg : suggs) {
            suggList.add(sugg);
        }
        suggTable.setItems(suggList);
    }

    private void refresh() {
        try {
            initCol();
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Comp_suggController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Comp_suggController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleAddressedComp(ActionEvent event) throws ClassNotFoundException, SQLException {
        Report selectedReport = (Report) Utility.getRow((TableView<Object>) (Object) complaintTable);
        if (selectedReport == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
            DbQuery.addressReport(DbQuery.getReportID(selectedReport.getDetails()));
            refresh();
        }
    }

    @FXML
    private void handleAddressedSugg(ActionEvent event) throws ClassNotFoundException, SQLException {
        Report selectedReport = (Report) Utility.getRow((TableView<Object>) (Object) suggTable);
        if (selectedReport == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
            DbQuery.addressReport(DbQuery.getReportID(selectedReport.getDetails()));
            refresh();
        }
    }

    @FXML
    private void handleRemoveComp(ActionEvent event) throws ClassNotFoundException, SQLException {
        Report selectedReport = (Report) Utility.getRow((TableView<Object>) (Object) suggTable);
        if (selectedReport == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
            DbQuery.deleteReport(DbQuery.getReportID(selectedReport.getDetails()));
            refresh();
        }
    }

    @FXML
    private void handleRemoveSugg(ActionEvent event) throws ClassNotFoundException, SQLException {
        Report selectedReport = (Report) Utility.getRow((TableView<Object>) (Object) suggTable);
        if (selectedReport == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
            DbQuery.deleteReport(DbQuery.getReportID(selectedReport.getDetails()));
            refresh();
        }
    }

}
