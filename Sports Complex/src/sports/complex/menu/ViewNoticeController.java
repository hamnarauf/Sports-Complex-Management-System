package sports.complex.menu;

import Classes.Notice;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Hamna Rauf
 */
public class ViewNoticeController implements Initializable {

    @FXML
    private Label notice1;
    @FXML
    private Label notice1heading;
    @FXML
    private Label notice1info;
    @FXML
    private Label notice2;
    @FXML
    private Label notice2info;
    @FXML
    private Label notice2heading;
    @FXML
    private Label notice3;
    @FXML
    private Label notice3info;
    @FXML
    private Label notice3heading;
    
    ArrayList<Notice> notices = new ArrayList<Notice>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        notices = DbQuery.getNotices();
            updateNotice(notice1, notice1heading,  notice1info);
            updateNotice(notice2, notice2heading,  notice2info);
            updateNotice(notice3, notice3heading,  notice3info);

    }
    
    private void updateNotice(Label outerHead, Label innerHead, Label text){
        Notice note;
        note = notices.remove(0);
        
//        outerHead.setText(note.getTitle());
//        innerHead.setText(note.getTitle());
//        text.setText(note.getText());
        
    
    
    }

}
