package Classes;

public class Emergency {
    private String patient_id;
    private String name;
    private String problem;
    private String itemName;
    private String status;
    
    public Emergency(String patient_id, String name, String problem, String itemName, String status) {
        this.setPatient_id(patient_id);
        this.setName(name);
        this.setProblem(problem);
        this.setItemName(itemName);
        this.setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    
}
