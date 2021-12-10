package sports.complex.emergency;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Classes.*;
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
public class RegisteredIndividualsController implements Initializable {

    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField bloodGroup;

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> idCol;
    @FXML
    private TableColumn<Person, String> genderCol;
    @FXML
    private TableColumn<Person, String> bloodGCol;
    @FXML
    private TableColumn<Person, ArrayList<String> >allergiesCol;
    @FXML
    private TableColumn<Person, String> contactCol;
    @FXML
    private TableColumn<Person, String> emerContact;
    @FXML
    private TableColumn<Person, String> fnameCol;
    @FXML
    private TableColumn<Person, String> lnameCol;

    ObservableList<Person> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void initCol() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gen"));
        bloodGCol.setCellValueFactory(new PropertyValueFactory<>("bloodGrp"));
        allergiesCol.setCellValueFactory(new PropertyValueFactory<>("allergy"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emerContact.setCellValueFactory(new PropertyValueFactory<>("emerContact"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));

    }

    private void loadData() {

        ArrayList<Person> allPersons = new ArrayList<Person>();
        ArrayList<String> allergies = new ArrayList<String>();
        allergies.add("ABC");
        allergies.add("DEF");
        allergies.add("GHi");
        Person person1 = new Person("fname", "lname", gender.f, new Date() , "cnic", "address", 
                  "contactNo", "emerContact", "email", "bloodGrp", allergies);
        allPersons.add(person1);
        
        for (Person person : allPersons) {
            list.add(person);
        }
        tableView.setItems(list);
    }

}
