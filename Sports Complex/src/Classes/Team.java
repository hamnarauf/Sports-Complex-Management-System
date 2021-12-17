package Classes;

import java.sql.Time;

/**
 *
 * @author Sana Zehra
 */

public class Team {
    private String team_id;
    private String sport;
    private int members;
    private String pack;
    private Time startTime;
    private String coachName;

    public Team(String team_id, String sport, int members, String pack) {
        this.team_id = team_id;
        this.sport = sport;
        this.members = members;
        this.pack = pack;
    }

    public Team(String team_id, String sport, int members, String pack, Time startTime){
        this(team_id, sport, members, pack);
        this.startTime = startTime;
    }

    public Team(String team_id, int members, String pack, String coachName){
        this(team_id, "", members, pack);
        this.coachName = coachName;
    }
        
    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public int getMembers() {
        return members;
    }
    public String getSport() {
        return sport;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    public void setMembers(int members) {
        this.members = members;
    }
     
}
