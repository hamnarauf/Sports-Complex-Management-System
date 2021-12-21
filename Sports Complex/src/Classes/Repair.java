package Classes;

public class Repair {
    private String purpose;
    private String sport;
    private String amount;
    private String status;

    public Repair(String purpose, String sport, String amount, String status) {
        this.purpose = purpose;
        this.sport = sport;
        this.amount = amount;
        this.setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
