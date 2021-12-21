package Classes;

import java.util.*;

/**
 *
 * @author Sana Zehra
 */

public class Member extends Person {
    private String member_id;
    private Date duedate;
    private int amount;
    
    public Member(){}

    public Member(String member_id, String fname, String lname, Date duedate, int amount){
        this.member_id = member_id;
        this.setFname(fname);
        this.setLname(lname);
        this.setDuedate(duedate);
        this.setAmount(amount);
    }

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
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }
}