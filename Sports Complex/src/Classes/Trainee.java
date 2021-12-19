package Classes;

import java.sql.Time;

/**
 *
 * @author Sana Zehra
 */

public class Trainee extends Member{
    private String member_id;
    private String sport;
    private Time startTime;
    private String day;
    private String fname;
    private String lname;

    public Trainee(String member_id, String sport, Time startTime, String day){
        this.member_id = member_id;
        this.sport = sport;
        this.startTime = startTime;
        this.day = day;;
    }

    public Trainee(String member_id, String fname, String lname){
        this.member_id = member_id;
        this.fname = fname;
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getMember_id(){
        return this.member_id;
    }

    public void setMember_id(String member_id){
        this.member_id = member_id;
    }

    public String getSport(){
        return this.sport;
    }

    public void setSport(String sport){
        this.sport = sport;
    }
}
