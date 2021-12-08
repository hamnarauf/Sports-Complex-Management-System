package Classes;

public class Trainee {
    private String cnic;
    private String sport;

    public Trainee(String cnic, String sport){
        this.cnic = cnic;
        this.sport = sport;
    }

    public String getCnic(){
        return this.cnic;
    }

    public void setCnic(String cnic){
        this.cnic = cnic;
    }

    public String getSport(){
        return this.sport;
    }

    public void setSport(String sport){
        this.sport = sport;
    }
}
