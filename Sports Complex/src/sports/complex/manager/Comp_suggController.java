/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.ArrayList;
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

    ObservableList<Report> complaintlist = FXCollections.observableArrayList();
    ObservableList<Report> suggList = FXCollections.observableArrayList();

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

    private void loadData() {

        ArrayList<Report> complaints = new ArrayList<Report>();
        for (Report complaint : complaints) {
            complaintlist.add(complaint);
        }
        complaintTable.setItems(complaintlist);

        ArrayList<Report> suggs = new ArrayList<Report>();
        for (Report sugg : suggs) {
            suggList.add(sugg);
        }
        suggTable.setItems(suggList);
    }

    private void refresh() {
        initCol();
        loadData();

    }

    @FXML
    private void handleAddressedComp(ActionEvent event) {
        Report selectedRepair = (Report) Utility.getRow((TableView<Object>) (Object) complaintTable);
        if (selectedRepair == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
//               DbQuery.addressReport();
            refresh();
        }
    }

    @FXML
    private void handleAddressedSugg(ActionEvent event) {
        Report selectedRepair = (Report) Utility.getRow((TableView<Object>) (Object) suggTable);
        if (selectedRepair == null) {
            AlertMaker.showAlert("Error", "No Row selected");

        } else {
//               DbQuery.addressReport();
            refresh();
        }
    }

}
