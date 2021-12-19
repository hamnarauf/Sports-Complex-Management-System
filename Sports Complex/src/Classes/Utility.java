package Classes;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Classes.*;
import Database.DbQuery;
import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import sports.complex.alert.AlertMaker;

/**
 *
 * @author Hamna Rauf
 */
public class Utility {

    public static void initPDFExprot(Stage stage, List<List> data) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as PDF");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File saveLoc = fileChooser.showSaveDialog(stage);
        ListToPdf ltp = new ListToPdf();
        boolean flag = ltp.doPrintToPdf(data, saveLoc, ListToPdf.Orientation.LANDSCAPE);
        JFXButton okayBtn = new JFXButton("Okay");
        JFXButton openBtn = new JFXButton("View File");
        openBtn.setOnAction((ActionEvent event1) -> {
            try {
                Desktop.getDesktop().open(saveLoc);
            } catch (Exception exp) {
                AlertMaker.showAlert("Could not load file", "Cant load file");
            }
        });
        if (flag) {
            AlertMaker.showAlert("Completed", "Member data has been exported.");
        }
    }

    public static Object getRow(TableView<Object> tableView) {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public static boolean passConstraints(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        if (!hasUppercase) {
            return false;
        }
        boolean hasLowercase = !password.equals(password.toUpperCase());
        if (!hasLowercase) {
            return false;
        }
        boolean isAtLeast8 = password.length() >= 8;//Checks for at least 8 characters
        if (!isAtLeast8) {
            return false;
        }
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");//Checks at least one char is not alpha numeric
        if (!hasSpecial) {
            return false;
        }
        boolean hasNumeric = !password.matches(".*\\d.*"); // checks at one digit
        if (hasNumeric) {
            return false;
        }

        return true;
    }

}
