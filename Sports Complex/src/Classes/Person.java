package Classes;

import java.util.*;

/**
 *
 * @author Sana Zehra
 */

public class Person {

    private String fname;
    private String lname;
    private gender gen;
    private Date dob;
    private String cnic;
    private String address;
    private String contactNo;
    private String emerContact;
    private String email;
    private String bloodGrp;
    private String allergy;
    
    public Person(){}

    public Person(String fname, String lname, gender gen, Date dob, String cnic,
            String contactNo, String email) {

        this.fname = fname;
        this.lname = lname;
        this.gen = gen;
        this.dob = dob;
        this.cnic = cnic;
        this.contactNo = contactNo;
        this.email = email;
    }

    public Person(String fname, String lname, gender gen, Date dob, String cnic, String contactNo,
            String emerContact, String email, String address) {

        this(fname, lname, gen, dob, cnic, contactNo, email);
        this.emerContact = emerContact;
        this.address = address;
    }

    public Person(String fname, String lname, gender gen, Date dob, String cnic,
            String contactNo, String emerContact, String email, String address, 
            String bloodGrp, String allergy) {

        this(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address);
        this.bloodGrp = bloodGrp;
        this.allergy = allergy;
    }

    public Person(String fname, String lname, gender gender, String cnic, String contactNo, 
            String emerContact, String bloodGrp, String allergy) {
        
        this.fname = fname;
        this.lname = lname;
        this.gen = gender;
        this.cnic = cnic;
        this.contactNo = contactNo;
        this.emerContact = emerContact;
        this.bloodGrp = bloodGrp;
        this.allergy = allergy;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public gender getGen() {
        return gen;
    }

    public void setGen(gender gen) {
        this.gen = gen;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmerContact() {
        return emerContact;
    }

    public void setEmerContact(String emerContact) {
        this.emerContact = emerContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
}