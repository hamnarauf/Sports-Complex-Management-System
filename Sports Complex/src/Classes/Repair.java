package Classes;

public class Repair {
    String purpose;
    String sport;
    String amount;

    public Repair(String purpose, String sport, String amount) {
        this.purpose = purpose;
        this.sport = sport;
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getSport() {
        return sport;
    }

    public String getAmount() {
        return amount;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    
    
}
