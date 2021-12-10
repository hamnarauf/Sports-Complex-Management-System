package Classes;

import java.sql.Time;

public class Trainee {
    private String member_id;
    private String sport;
    private Time startTime;

    public Trainee(String member_id, String sport, Time startTime){
        this.member_id = member_id;
        this.sport = sport;
        this.startTime = startTime;
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
