/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex.registration.teams;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.Team;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AllTeamsController implements Initializable {

    @FXML
    private TableView<Team> tableView;
    @FXML
    private TableColumn<Team, String> idCol;
    @FXML
    private TableColumn<Team, String> nameCol;
    @FXML
    private TableColumn<Team, String> packageCol;
    @FXML
    private TableColumn<Team, String> coachCol;
    @FXML
    private TableColumn<Team, String> totalMembersCol;

    ObservableList<Team> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        packageCol.setCellValueFactory(new PropertyValueFactory<>("package"));
        coachCol.setCellValueFactory(new PropertyValueFactory<>("totalMem"));
        totalMembersCol.setCellValueFactory(new PropertyValueFactory<>("gen"));
    }

    private void loadData() {

        ArrayList<Team> allTeam = new ArrayList<Team>();
        for (Team team : allTeam) {
            list.add(team);
        }
        tableView.setItems(list);
    }

}
