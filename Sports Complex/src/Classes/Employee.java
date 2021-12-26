package Classes;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Sana Zehra
 */
public class Employee extends Person {

    private String emp_id;
    private String deptName;
    private String dept_id;
    private String salary;
    private int sport_id;
    private String sportName;
    private String time;

    public Employee() {
    }

    public Employee(String fname, String lname, gender gen, Date dob, String cnic, String contactNo, String emerContact,
    String email, String address, String bloodGrp, String allergy, String emp_id, String dept_id, int sport_id){
        this(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address, bloodGrp, allergy, emp_id, dept_id);
        this.setSport_id(sport_id);
    }    

    public Employee(String fname, String lname, gender gen, Date dob, String cnic, String contactNo, String emerContact,
            String email, String address, String bloodGrp, String allergy, String emp_id, String dept_id) {
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address, bloodGrp, allergy);

        this.emp_id = emp_id;
        this.dept_id = (dept_id);
    }
    
    public Employee(String fname, String lname, gender gen, Date dob, String cnic, String contactNo, String emerContact,
            String email, String address, String bloodGrp, String allergy, String emp_id, String dept_id, String deptName) {
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address, bloodGrp, allergy);

        this.emp_id = emp_id;
        this.setDept_id(dept_id);
        this.deptName = deptName;
    }

    public Employee(String fname, String lname, gender gen, Date dob, String cnic, String contactNo,
            String emerContact, String email, String address, String emp_id, String deptName) {
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address);
        this.emp_id = emp_id;
        this.deptName = deptName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public void setSportName(String sport){
        this.sportName = sport;
    
    }
    
    public String getSportName(){
        return sportName;
    }


    public int getSport_id() {
        return sport_id;
    }

    public void setSport_id(int sport_id) {
        this.sport_id = sport_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

}
