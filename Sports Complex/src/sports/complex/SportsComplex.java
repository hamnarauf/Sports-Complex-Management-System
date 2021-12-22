package sports.complex;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Hamna Rauf
 */
public class SportsComplex extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        setStage("/sports/complex/registration/registration.fxml");
//        setStage("/sports/complex/registration/members/allMembers.fxml");
//        setStage("/sports/complex/registration/teams/allTeams.fxml");
//        setStage("/sports/complex/registration/members/registerGuest.fxml");
//        setStage("/sports/complex/registration/members/registerTrainee.fxml");
//        setStage("/sports/complex/registration/members/removeMember.fxml");
//        setStage("/sports/complex/registration/employees/allCoaches.fxml");
//        setStage("/sports/complex/registration/employees/allEmployees.fxml");
//        setStage("/sports/complex/registration/employees/removeEmployee.fxml");
//        setStage("/sports/complex/registration/employees/coachWorkingHours.fxml");
//        setStage("/sports/complex/registration/teams/allTeams.fxml");
//        setStage("/sports/complex/registration/teams/removeTeam.fxml");
//        setStage("/sports/complex/emergency/emergency.fxml");
//        setStage("/sports/complex/login/login.fxml");
//        setStage("/sports/complex/alert/alertbox.fxml");
//        setStage("/sports/complex/finance/finance.fxml");
//        setStage("/sports/complex/attendant/attendant.fxml");
//        setStage("/sports/complex/menu/viewNotice.fxml");
//        setStage("/sports/complex/editProfile.fxml");
//        setStage("/sports/complex/maintenance/maintenance.fxml");
//        setStage("/sports/complex/manager/manager.fxml");
//        setStage("/sports/complex/manager/comp_sugg.fxml");
//        setStage("/sports/complex/manager/repairs.fxml");
//        setStage("/sports/complex/manager/emergencies.fxml");
//        setStage("/sports/complex/manager/attendance.fxml");
//        setStage("/sports/complex/coach/coach.fxml");
//        setStage("/sports/complex/inventory/inventory.fxml");
//        setStage("/sports/complex/inventory/issuedItems.fxml");
//        setStage("/sports/complex/inventory/delItem.fxml");
//        setStage("/sports/complex/inventory/history.fxml");
//        setStage("/sports/complex/inventory/availableItems.fxml");
//        setStage("/sports/complex/finance/bills.fxml");
//        setStage("/sports/complex/emergency/registeredIndividuals.fxml");
//        setStage("/sports/complex/finance/ReqFunds/reqFunds.fxml");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void setStage(String fxml) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml));

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.getIcons().add(new Image(("/Images/icon.png")));
            stage.setTitle("Sports Complex");
//            stage.setMaximized(true);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(SportsComplex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
