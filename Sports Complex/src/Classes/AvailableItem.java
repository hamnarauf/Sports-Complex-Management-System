package Classes;

public class AvailableItem {
    private String itemName;
    private int qty;
    
    public AvailableItem(String itemName, int qty) {
        this.setItemName(itemName);
        this.setQty(qty);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    
}
