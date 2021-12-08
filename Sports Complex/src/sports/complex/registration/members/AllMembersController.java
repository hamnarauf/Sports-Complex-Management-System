/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private TableColumn<Member, String> nameCol;
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
        loadData();
    }

    private void initCol() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cnicCol.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData() {
        list.add(new Member("54678", "Ahmed Ali", "7897654312657", "M", "5/12/2000", "0334897654" , "ali@gmail.com"));
        tableView.setItems(list);
    }

    public class Member {

        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty cnic;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty dob;
        private final SimpleStringProperty contact;
        private final SimpleStringProperty email;

        public Member(String id, String name, String cnic, String gender, String dob, String contact, String email) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.cnic = new SimpleStringProperty(cnic);
            this.gender = new SimpleStringProperty(gender);
            this.dob = new SimpleStringProperty(dob);
            this.contact = new SimpleStringProperty(contact);
            this.email = new SimpleStringProperty(email);
        }

        public String getId() {
            return id.get();
        }

        public String getName() {
            return name.get();
        }

        public String getCnic() {
            return cnic.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getDob() {
            return dob.get();
        }

        public String getContact() {
            return contact.get();
        }
        public String getEmail() {
            return email.get();
        }
    }

}
