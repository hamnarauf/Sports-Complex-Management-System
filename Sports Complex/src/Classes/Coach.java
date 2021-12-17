package Classes;

import java.util.*;

/**
 *
 * @author Sana Zehra
 */

public class Coach extends Person{

    private String coach_id;
    private String sportName;

    public Coach(String fname, String lname, gender gen, Date dob, String cnic, String contactNo,
    String emerContact, String email, String address, String coach_id, String sportName){
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address);
        this.setCoach_id(coach_id);
        this.setSportName(sportName);
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(String coach_id) {
        this.coach_id = coach_id;
    }
     
}