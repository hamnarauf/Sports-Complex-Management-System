/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.complex;

import sports.complex.alert.Alertbox;
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
//        setStage("/sports/complex/emergency/emergency.fxml");
//        setStage("/sports/complex/login/login.fxml");
//        setStage("/sports/complex/finance/finance.fxml");
//        setStage("/sports/complex/attendant/attendant.fxml");
//        setStage("/sports/complex/registration/menu/viewNotice/viewNotice.fxml");
//        setStage("/sports/complex/maintenance/maintenance.fxml");
//        setStage("/sports/complex/manager/manager.fxml");
//        setStage("/sports/complex/coach/coach.fxml");
//        setStage("/sports/complex/inventory/inventory.fxml");
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
