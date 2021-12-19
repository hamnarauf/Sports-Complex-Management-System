package Classes;

import java.sql.Date;
import java.sql.Time;

public class Schedule {
    private String event_id;
    private String eventName;
    private Date date;
    private Time time;
    private String venue;
    
    public Schedule(String event_id, String eventName, Date date, Time time, String venue) {
        this.setEvent_id(event_id);
        this.setEventName(eventName);
        this.setDate(date);
        this.setTime(time);
        this.setVenue(venue);
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
}
