package sports.complex.emergency;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.*;
import Database.DbQuery;
import com.jfoenix.controls.JFXComboBox;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class RegisteredIndividualsController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXComboBox<String> bloodGroup;

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> genderCol;
    @FXML
    private TableColumn<Person, String> bloodGCol;
    @FXML
    private TableColumn<Person, ArrayList<String>> allergiesCol;
    @FXML
    private TableColumn<Person, String> contactCol;
    @FXML
    private TableColumn<Person, String> emerContact;
    @FXML
    private TableColumn<Person, String> fnameCol;
    @FXML
    private TableColumn<Person, String> lnameCol;

    ObservableList<Person> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Person, String> cnicCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        populateBloodCombo();
        try {
            loadData();
            filterByBloodG();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisteredIndividualsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredIndividualsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void populateBloodCombo() {
        ArrayList<String> bloodGroups = new ArrayList<String>();

        bloodGroup.getItems().add("A+");
        bloodGroup.getItems().add("B+");
        bloodGroup.getItems().add("A-");
        bloodGroup.getItems().add("B-");
        bloodGroup.getItems().add("O-");
        bloodGroup.getItems().add("O+");
        bloodGroup.getItems().add("AB+");
        bloodGroup.getItems().add("AB-");

    }

    private void initCol() {

        cnicCol.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gen"));
        bloodGCol.setCellValueFactory(new PropertyValueFactory<>("bloodGrp"));
        allergiesCol.setCellValueFactory(new PropertyValueFactory<>("allergy"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emerContact.setCellValueFactory(new PropertyValueFactory<>("emerContact"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));

    }

    private void loadData() throws ClassNotFoundException, SQLException {

        ArrayList<Person> allPersons = new ArrayList<Person>();
        allPersons = DbQuery.getMedicalDetails();
        for (Person person : allPersons) {
            list.add(person);
        }
        tableView.setItems(list);
    }

    @FXML
    private void filterByName() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Person> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        }
        );

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Person> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty()
                .bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }

    @FXML
    private void filterByBloodG() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Person> filteredData = new FilteredList<>(list, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        bloodGroup.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getBloodGrp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        }
        );

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Person> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty()
                .bind(tableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }

}
