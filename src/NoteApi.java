import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import utils.CategoryUtils;


public class NoteApi {

    private ArrayList<Note> notes = new ArrayList<>();

    /**
     * @return number of notes in the array
     */
    public int numberOfNotes() {
        return notes.size();
    }

    public int numberOfItems() {
        int numItems = 0;
        for (int i = 0; i < notes.size(); i++) {
            notes.get(i).numberOfItems();
            numItems = i;
        }
        return numItems;
    }

    /**
     * @param index
     * @return this is what we are using to validate
     */
    public boolean isIndexValid(int index) {
        return (index >= 0) && (index < notes.size());
    }

    public boolean addNote(Note note)
    {
        return notes.add(note);
    }

    public boolean addItemtoNote(int i, String description)
    {
        Item item = new Item(description);
        if (isIndexValid(i))
        {
            notes.get(i).addItem(item);
            return true;
        }
        return false;
    }

    public boolean archiveNote(int note) {
        Note isArch = findNote(note);

        if (archiveNote(note)) {
            isArch.setNoteArchived(true);
            return true;
        } else {
            return false;
        }
    }

    public String searchNotebyTitle(String Title) {
        if (notes.isEmpty()) {
            System.out.println("No Notes Stored");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).getNoteTitle().equals(Title)) {
                    return Title;
                } else {
                    System.out.println("No Notes found at " + i);
                }
            }
        }
        return "";
    }


    public String listNotesbySelectedCategory(Note category) {
        String[] arrayOfCate = {"Home", "Work", "Hobby", "Holiday", "College"};
        String lowerCategoryName = category.getNoteCategory().toLowerCase();

        for (int i = 0; i < arrayOfCate.length; i++) {
            if (lowerCategoryName.equals(arrayOfCate[i].toLowerCase())) {
                return arrayOfCate[i];
            } else {
                return "";
            }
        }
        return "";
    }

    public boolean deleteItemfromNote(int noteToDelete, int indexToDelete) {
        if (isIndexValid(indexToDelete))
        {
            notes.get(noteToDelete).deleteItem(indexToDelete);

            return true;
        }

        return false;
    }


    public int listNotesbySelectedPriority(int prior)
    {
        int [] arrayOfprior = {1, 2, 3, 4, 5};

        for (int i = 0; i < arrayOfprior.length; i++)
        {
            if (prior == arrayOfprior[i])
            {
                System.out.println("Note Priority is" + notes.get(i).getNotePriority());
            }
            else
            {
                return -999;

            }
        }
        return -999;
    }


    public int numberOfArchivedNotes()
    {
        int totalArchivedNotes = 0;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).isNoteArchived()) {
                totalArchivedNotes++;
            }
        }
        return totalArchivedNotes;
    }


    public String archiveNotesWithAllItemsComplete()
    {
        for (int i = 0; i < notes.size(); i++)
        {
            if (notes.get(i).checkNoteCompletionStatus())
            {
                return "No Item on Note";
            }
            else
            {
                notes.get(i).checkNoteCompletionStatus();
            }
        }
        return null;
    }

    public int numberOfActiveNotes() {
        int totalActiveNotes = 0;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).isNoteArchived()) {
                totalActiveNotes++;
            }
        }
        return totalActiveNotes;


    }

    public int numberOfCompleteItems() {
        int numberCompleteItems = 0;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).checkNoteCompletionStatus()) {
                numberCompleteItems++;
            }
        }
        return numberCompleteItems;
    }

    public int numberOfItemsTODO() {
        int numberItemsTODO = 0;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).checkNoteCompletionStatus() == false) {
                numberItemsTODO++;
            }
        }
        return numberItemsTODO;
    }

    public Note deleteNote(int indexToDelete) {
        if (isIndexValid(indexToDelete)) {
            notes.remove(indexToDelete);
            return findNote(indexToDelete);
        }

        return null;
    }


    public boolean updateNote(int indexToUpdate, String NoteTitle, int notePriority, String noteCategory) {
        Note foundNote = findNote(indexToUpdate);

        if (foundNote != null) {
            foundNote.setNoteTitle(NoteTitle);
            foundNote.setNotePriority(notePriority);
            foundNote.setNoteCategory(noteCategory);
            return true;
        } else {
            return false;
        }
    }


    public Note findNote(int index) {
        if (isIndexValid(index)) {
            return notes.get(index);
        }
        return null;
    }

    public String listAllNotes() {
        if (notes.isEmpty()) {
            return "No notes in the store";
        } else {
            String listOfNotes = "";

            for (int i = 0; i < notes.size(); i++) {
                listOfNotes += i + ": " + notes.get(i) + "\n";
            }
            return listOfNotes;
        }
    }

    public String listActiveNotes() {
        if (notes.isEmpty()) {
            return "No notes in the store";
        } else {
            String listOfActiveNotes = "";

            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).isNoteArchived()) {
                    listOfActiveNotes += i + ": " + notes.get(i) + "\n";
                }

            }
            return listOfActiveNotes;
        }
    }

    public String listArchivedNotes()
    {
        if (notes.isEmpty()) {
            return "No notes in the store";
        } else {
            String listOfArchivedNotes = "";

            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).isNoteArchived()) {
                    listOfArchivedNotes = listOfArchivedNotes + notes.get(i).toString();
                }
            }
            return listOfArchivedNotes;
        }
    }


    public  String listItemTodo()
    {
        if (notes.isEmpty())
        {
            return "No items on any note";
        }
        else
        {
            String listOfItemsTodo = "";

            for (int i = 0; i < notes.size(); i++)
            {
                listOfItemsTodo += i + ": " + notes.get(i) + "\n";
            }
            return listOfItemsTodo;
        }
    }

    public String searchNoteByTitle(String titleIn)
    {
            if (notes.isEmpty())
            {
                return "No Notes Found";
            }
            else
            {
                String Title ="";
                for (int i = 0; i < notes.size(); i++)
                {
                    if (notes.get(i).getNoteTitle().equals(titleIn) == true)
                    {
                        Title = String.valueOf(notes.get(i).equals(Title));
                    }
                    else
                    {
                        return "Note Title not found";
                    }
                }
            }
            return "Not Found";
    }

    public String searchItemByDescription(String ItemDes)
    {
        if (notes.isEmpty())
        {
            return "No Items Found";
        }
        else
        {
            String Description ="";
            for (int i = 0; i < notes.size(); i++)
            {
                if (notes.get(i).equals(ItemDes) == true)
                {
                    Description = String.valueOf(notes.get(i).equals(Description));
                }
                else
                {
                    return "Item Description not found";
                }
            }
        }
        return "Not Found";
    }

    public String listItemStatusbyCath(String itemCath)
    {
        if(notes.isEmpty())
        {
            return "No Notes found";
        }
        else
        {
            String Status = "";
            for(int i = 0; i<notes.size();i++)
            {
                if(notes.get(i).getNoteCategory().equals(itemCath))
                {
                    Status = String.valueOf(notes.get(i).equals(itemCath));
                }
            }
            return Status;
        }
    }

    public void save() throws Exception
    {
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("notes.xml"));
        out.writeObject(notes);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        Class<?>[] classes = new Class[]{Note.class};

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("notes.xml"));
        notes = (ArrayList<Note>) is.readObject();
        is.close();
    }
}
