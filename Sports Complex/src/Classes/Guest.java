package Classes;
/**
 *
 * @author Sana Zehra
 */

public class Guest {
    String cnic;
    String member_id;
    String firstName;
    String lastName;

    public Guest(String cnic, String member_id, String firstName, String lastName){
        this.cnic = cnic;
        this.member_id = member_id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getCnic() {
        return cnic;
    }
    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}