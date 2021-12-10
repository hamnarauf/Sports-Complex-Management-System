package Classes;

import java.util.Date;

public class Employee extends Person {

    private String emp_id;
    private String dept_id;
    private float salary;
    private String role;

    public Employee(String fname, String lname, gender gen, Date dob, String cnic, String contactNo, String emerContact,
            String email, String address, String bloodGrp, String allergy, String emp_id, String dept_id,
            float salary, String role) {
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address, bloodGrp, allergy);

        this.emp_id = emp_id;
        this.dept_id = dept_id;
        this.salary = salary;
        this.role = role;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
