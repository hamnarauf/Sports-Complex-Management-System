
package Classes;

/**
 *
 * @author Hamna Rauf
 */
public class Report {
    private String details;
    private String status;

    public Report(String details){
        this.details = details;
    }

    public Report(String details, String status) {
        this.setDetails(details);
        this.setStatus(status);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
