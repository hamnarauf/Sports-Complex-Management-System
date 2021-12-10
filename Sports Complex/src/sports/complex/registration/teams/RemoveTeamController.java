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

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RemoveTeamController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label coach;
    @FXML
    private Label pack;
    @FXML
    private JFXTextField teamId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleRemoveBtn(ActionEvent event) {

    }

    private void clearCache() {
        name.setText("");
        coach.setText("");
        pack.setText("");

    }

    @FXML
    private void updateFields(ActionEvent event) throws SQLException {
        clearCache();
        String id = teamId.getText();

        if (id != null && DbQuery.isTeam(id)) {
            Team team;
//            team = DbQuery.getTeam(id);
//            name.setText(team.getName());
//            coach.setText(team.getCoach());
//            pack.setText(team.getPackage());

        } else {
            pack.setText("Invalid Member ID");
        }
    }

}
