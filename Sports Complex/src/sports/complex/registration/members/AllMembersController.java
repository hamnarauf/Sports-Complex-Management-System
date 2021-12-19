package sports.complex.registration.members;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Classes.Member;
import java.util.ArrayList;
import java.util.Date;
import Classes.gender;
import Database.DbQuery;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class AllMembersController implements Initializable {

    ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> fnameCol;
    @FXML
    private TableColumn<Member, String> lnameCol;
    @FXML
    private TableColumn<Member, String> cnicCol;
    @FXML
    private TableColumn<Member, String> genderCol;
    @FXML
    private TableColumn<Member, String> dobCol;
    @FXML
    private TableColumn<Member, String> contactCol;
    @FXML
    private TableColumn<Member, String> emailCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(AllMembersController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllMembersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        cnicCol.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gen"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData() throws SQLException, ClassNotFoundException {

        ArrayList<Member> allMember = new ArrayList<Member>();
        allMember = DbQuery.displayMembers();

        for (Member member : allMember) {
            list.add(member);
        }
        tableView.setItems(list);
    }

}
