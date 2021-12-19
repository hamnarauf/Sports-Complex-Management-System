package sports.complex.registration.teams;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.Team;
import Database.DbQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private TableColumn<Team, String> packageCol;
    @FXML
    private TableColumn<Team, String> coachCol;
    @FXML
    private TableColumn<Team, String> totalMembersCol;

    ObservableList<Team> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(AllTeamsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllTeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("team_id"));
        packageCol.setCellValueFactory(new PropertyValueFactory<>("pack"));
        coachCol.setCellValueFactory(new PropertyValueFactory<>("coachName"));
        totalMembersCol.setCellValueFactory(new PropertyValueFactory<>("members"));
    }

    private void loadData() throws SQLException, ClassNotFoundException {

        ArrayList<Team> allTeams = new ArrayList<Team>();
        allTeams = DbQuery.displayTeamList();
        for (Team team : allTeams) {
            list.add(team);
        }
        tableView.setItems(list);
    }

}
