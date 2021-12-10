package Classes;

import java.util.*;

public class Member extends Person {
    private int member_id;
    
    public Member(String fname, String lname, gender gen, Date dob, String cnic, String address, 
                  String contactNo, String emerContact, String email, String bloodGrp, int member_id){
        super(fname, lname, gen, dob, cnic, address, contactNo, emerContact, email, bloodGrp);
        this.member_id = member_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
}

