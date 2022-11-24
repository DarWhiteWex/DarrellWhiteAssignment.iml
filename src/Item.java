import java.util.ArrayList;
public class Item {

    private String itemDescription;
    private boolean isItemCompleted;
    public Item(String itemDescription)
    {
        this.itemDescription = utils.Utilities.truncateString(itemDescription, 50);
    }
    public Item(String itemDescription, boolean isTrue)
    {
        this.itemDescription =  utils.Utilities.truncateString(itemDescription, 50);
        this.isItemCompleted =  isTrue;
    }

    public String getItemDescription()

    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public boolean isItemCompleted()
    {
        if(isItemCompleted == true)
        {
            return isItemCompleted;
        }
        return false;
    }


    public void setItemCompleted(boolean itemCompleted)
    {
        this.isItemCompleted = itemCompleted;
    }

    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public String toString()
    {
        return "Item Description is: " + getItemDescription() +
                "/n"
                + "And Is Item Completed: " + isItemCompleted() +
                "/n";
    }
}