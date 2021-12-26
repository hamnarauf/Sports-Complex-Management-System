package Classes;

import java.sql.Date;

/**
 *
 * @author Sana Zehra
 */
public class User extends Employee {

    private String uname;
    private String pass;
    private String secQs;
    private String secAns;

    public User(String uname, String pass) {
        this.uname = uname;
        this.pass = pass;
    }

    public User(String uname, String pass, String dept_id) {
        this(uname, pass);
        this.setDept_id(dept_id);
    }

    public User(String uname, String pass, String dept_id, String emp_id) {
        this(uname, pass, dept_id);
        setEmp_id(emp_id);
    }

    public User(String fname, String lname, gender gen, Date dob, String cnic, String contactNo,
            String emerContact, String email, String address, String emp_id, String deptName,
            String secQs, String secAns) {
        super(fname, lname, gen, dob, cnic, contactNo, emerContact, email, address, emp_id, deptName);
        this.secQs = secQs;
        this.secAns = secAns;
    }

    public User(Employee e, String secQs, String secAns) {
        super(e.getFname(), e.getLname(), e.getGen(), e.getDob(), e.getCnic(), e.getContactNo(), 
                e.getEmerContact(), e.getEmail(), e.getAddress(), e.getEmp_id(), e.getDeptName());
        this.setSportName(e.getSportName());
        this.setDept_id(e.getDept_id());
        this.secQs = secQs;
        this.secAns = secAns;
    }

    public String getSecQs() {
        return secQs;
    }

    public void setSecQs(String secQs) {
        this.secQs = secQs;
    }

    public String getSecAns() {
        return secAns;
    }

    public void setSecAns(String secAns) {
        this.secAns = secAns;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
