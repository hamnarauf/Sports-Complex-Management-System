/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.manager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ScheduleController implements Initializable {

    @FXML
    private TableView<Schedule> tableView;
    @FXML
    private TableColumn<Schedule, String> idCol;
    @FXML
    private TableColumn<Schedule, Date> dateCol;
    @FXML
    private TableColumn<Schedule, Time> timeCol;
    @FXML
    private TableColumn<Schedule, String> venueCol;
    @FXML
    private TableColumn<Schedule, String> nameCol;

    
    ObservableList<Schedule> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));

    }

    private void loadData() {

        ArrayList<Schedule> events = new ArrayList<Schedule>();
        for (Schedule event : events) {
            list.add(event);
        }
        tableView.setItems(list);
    }

    
}
