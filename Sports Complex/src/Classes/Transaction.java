package Classes;

/**
 *
 * @author Hamna Rauf
 */
public class Transaction {
    private String id;
    private String type;
    private String amount;

    public Transaction(String id, String type, String amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    
    
}
