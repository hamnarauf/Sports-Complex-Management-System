package sports.complex.registration.employees;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Database.DbQuery;
import Classes.Coach;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AllCoachesController implements Initializable {

    @FXML
    private TableView<Coach> tableView;
    @FXML
    private TableColumn<Coach, String> idCol;
    @FXML
    private TableColumn<Coach, String> fnameCol;
    @FXML
    private TableColumn<Coach, String> lnameCol;
    @FXML
    private TableColumn<Coach, String> cnicCol;
    @FXML
    private TableColumn<Coach, String> genderCol;
    @FXML
    private TableColumn<Coach, Date> dobCol;
    @FXML
    private TableColumn<Coach, String> contactCol;
    @FXML
    private TableColumn<Coach, String> emailCol;
    @FXML
    private TableColumn<Coach, String> domainCol;

    ObservableList<Coach> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(AllCoachesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllCoachesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("coach_id"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        cnicCol.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gen"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        domainCol.setCellValueFactory(new PropertyValueFactory<>("sportName"));

    }

    public void loadData() throws SQLException, ClassNotFoundException {
        ArrayList<Coach> allCoaches = new ArrayList<Coach>();
        allCoaches = DbQuery.displayCoachList();
        for (Coach coach : allCoaches) {
            list.add(coach);
        }
        tableView.setItems(list);
    }

}
