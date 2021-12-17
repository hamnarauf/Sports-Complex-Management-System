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
    

    
}
