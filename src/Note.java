import java.util.ArrayList;
import java.util.Objects;


public class Note {
    private String NoteTitle;
    private int NotePriority;
    private String NoteCategory;

    private boolean noteArchived = false;
    private ArrayList <Item> items = new ArrayList<>();


    public Note(String NoteTitle, int NotePriority, String NoteCategory)
    {
        this.NoteTitle = NoteTitle;
        this.NotePriority = NotePriority;
        this.NoteCategory = NoteCategory;
    }

    public String getNoteTitle()
    {
        return NoteTitle;
    }

    public int getNotePriority()
    {
        return NotePriority;
    }

    public String getNoteCategory()
    {
        return NoteCategory;
    }

    public boolean isNoteArchived()
    {
        return noteArchived;
    }

    //set Title, Priority, Category and Archive Status for Notes
    public void setNoteTitle(String noteTitlein)
    {
            NoteTitle = noteTitlein;
    }

    public void setNotePriority(int notePriority) {
        NotePriority = notePriority;
    }

    public void setNoteCategory(String noteCategory) {
        NoteCategory = noteCategory;
    }

    public void setNoteArchived(boolean isNoteArchived)
    {
         noteArchived = isNoteArchived;
    }

    //Array list of type Item, called items
    public  ArrayList<Item> getItems()
    {
        return items;
    }

    public ArrayList<Item> setItem(ArrayList<Item> in)
    {
       return setItem(in);
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public int numberOfItems()
    {
        return items.size();
    }

    public boolean isIndexValid(int index)
    {
        return (index >= 0) && (index < items.size());
    }


    public Item findItem(int index) {
        if (isIndexValid(index)) {
            return items.get(index);
        }
        return null;
    }

    public String listItems()
    {
        if (items.isEmpty())
        {
            return "No items in the store";
        }
        else
        {
            String listOfItems = "";

            for (int i = 0; i < items.size(); i++)
            {
                listOfItems += i + ": " + items.get(i) + "\n";
            }
            return listOfItems;
        }
    }

    public Item deleteItem(int indexToDelete)
    {
        if (isIndexValid(indexToDelete))
        {
            items.remove(indexToDelete);
            return findItem(indexToDelete);
        }

        return findItem(indexToDelete);
    }

    public boolean updateItem(int index, String itemDescription, boolean itemCompleted)
    {
        Item foundItem = findItem (index);

        if (foundItem != null)
        {
            foundItem.setItemDescription(itemDescription);
            foundItem.setItemCompleted(itemCompleted);

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkNoteCompletionStatus()
    {
        for(Item item : items)
        {
            if(item.isItemCompleted()==false)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public  boolean equals(Object o)
    {
        if(this ==o)
         return true;

        if(o == null || getClass() != o.getClass())
         return false;

        Note note = (Note) o;

        return NotePriority == note.NotePriority
                && noteArchived == note.noteArchived
                && Objects.equals(NoteTitle, note.NoteTitle)
                && Objects.equals(NoteCategory, note.NoteCategory)
                && Objects.equals(items,note.getItems());
    }


    /**
     *
     * converts date to a string output
     */
    public String toString ()
    {
        return "Note Title is: " + NoteTitle
                + ", Note Priority is: " + NotePriority
                + ", Note Category is: " + NoteCategory
                + ", Note is Archived: " + noteArchived;
    }
}
