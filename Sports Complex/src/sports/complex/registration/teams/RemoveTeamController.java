package sports.complex.registration.teams;

import Classes.*;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sports.complex.alert.AlertMaker;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RemoveTeamController implements Initializable {

    @FXML
    private Label pack;
    @FXML
    private JFXTextField teamId;
    @FXML
    private Label sport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRemoveBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        DbQuery.removeTeam(teamId.getText());
        AlertMaker.showAlert("Success", "Team removed successfully");
        clearCache();

    }

    private void clearCache() {
        sport.setText("");
        pack.setText("");
        teamId.setText("");
    }

    @FXML
    private void updateFields(ActionEvent event) throws SQLException, ClassNotFoundException {
//        clearCache();
        String id = teamId.getText();

        if (!id.equals("")&& DbQuery.isTeam(id)) {
            Team team;
            team = DbQuery.removeTeamDetails(id);
            sport.setText(team.getSport());
            pack.setText(team.getPack());

        } else {
            pack.setText("Invalid Team ID");
        }
    }

}
