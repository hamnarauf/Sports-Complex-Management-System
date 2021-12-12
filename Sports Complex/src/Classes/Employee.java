package Classes;

import java.util.Date;

public class Employee extends Person {

    private String emp_id;
    private String deptName;
    private String dept_id;

    
    public Employee(){}


    public Employee(String fname, String lname, gender gen, Date dob, String cnic, String contactNo, String emerContact,
            String email, String address, String bloodGrp, String allergy, String emp_id, String dept_id) {
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address, bloodGrp, allergy);

        this.emp_id = emp_id;
        this.setDept_id(dept_id);
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public Employee(String fname, String lname, gender gen, Date dob, String cnic, String contactNo,
    String emerContact, String email, String address, String emp_id, String deptName){
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address);
        this.emp_id = emp_id;
        this.deptName = deptName;
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
}
