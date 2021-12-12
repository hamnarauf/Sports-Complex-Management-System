/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author Hamna Rauf
 */
public class Attendance extends Employee {

    private Date date;
    private String attendance;

    public Attendance(Employee e, Date date, String attendance) {

        super(e.getFname(), e.getLname(), e.getGen(), e.getDob(), e.getCnic(), e.getContactNo(), e.getEmerContact(),
                e.getEmail(), e.getAddress(), e.getBloodGrp(), e.getAllergy(), e.getEmp_id(), e.getDept_id(),
                e.getSalary(), "");
        this.attendance = attendance;
        this.date = date;
    }

    public Attendance(String fname, String lname, gender gen, Date dob, String cnic, String contactNo, String emerContact,
            String email, String address, String bloodGrp, String allergy, String emp_id, String dept_id,
            float salary, String role, Date date, String attendance) {

        super(fname, lname, gen, dob, cnic, contactNo, emerContact,
                email, address, bloodGrp, allergy, emp_id, dept_id,
                salary, "");
        this.attendance = attendance;
        this.date = date;

    }

    public Date getDate() {
        return date;
    }

    public String getAttendance() {
        return attendance;
    }

}
