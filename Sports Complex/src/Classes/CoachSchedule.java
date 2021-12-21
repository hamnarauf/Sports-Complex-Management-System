package Classes;

import java.sql.*;

/**
 *
 * @author Sana Zehra
 */

public class CoachSchedule {
    private String coach_id;
    private String day;
    private Time startTime;
    private Time endTime;
    private int attendees;

    public CoachSchedule(String coach_id, String day, Time startTime, Time endTime) {

        this.coach_id = coach_id;
        this.setDay(day);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }

    public CoachSchedule(String coach_id, String day, Time startTime, Time endTime, int attendees) {
        this(coach_id, day, startTime, endTime);
        this.attendees = attendees;
    }

    public int getAttendees() {
        return attendees;
    }


    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCoach_id() {
        return coach_id;
    }
    
    public void setCoach_id(String coach_id) {
        this.coach_id = coach_id;
    }
}