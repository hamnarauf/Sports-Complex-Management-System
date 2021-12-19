package Classes;

import java.util.*;

/**
 *
 * @author Sana Zehra
 */

public class Member extends Person {
    private String member_id;
    
    public Member(){}

    public Member(String fname, String lname, gender gen, Date dob, String cnic, String address, 
                  String contactNo, String emerContact, String email, String bloodGrp, 
                  String allergy, String member_id){
        
        super(fname, lname, gen, dob, cnic, address, contactNo, emerContact, email, bloodGrp, allergy);
        this.member_id = member_id;
    }

    public Member(String fname, String lname, gender gen, Date dob, String cnic, String contactNo,
        String email, String member_id){
            super(fname, lname, gen, dob, cnic, contactNo, email);
            this.member_id = member_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
}