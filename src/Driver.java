import utils.ScannerInput;
import utils.Utilities;

public class Driver {


        private NoteApi note = new NoteApi();

        public static void main(String[] args) {
                new Driver();
        }

        public Driver() {
                runMenu();
        }

        private int mainMenu() {
                return ScannerInput.readNextInt("""
                        ------------------------------------------------------------------
                        |                            NOTE KEEPER APP                     |
                        ------------------------------------------------------------------
                        | NOTE MENU                                                      |
                        |   1) Add a note                                                |
                        |   2) List all notes(all, active, archived)                     |
                        |   3) Update a note                                             |
                        |   4) Delete a note                                             |
                        |   5) Archive a note                                            |
                        ------------------------------------------------------------------
                        | ITEM MENU                                                      |
                        |   6) Add an item to a note                                     |
                        |   7) Update item description on a note                         |
                        |   8) Delete an item from a note                                |
                        |   9) Mark item as complete / to do                             |
                        ------------------------------------------------------------------
                        | REPORT MENU FOR NOTES                                          |
                        |   10) All notes and their items (active & archived)            |
                        |   11) Overall number of items to do / complete                 |
                        |   12) All notes within a selected Category                     |
                        |   13) All notes within a selected Proiority                    |
                        |   14) Search for all notes (by notes Title)                    |
                        ------------------------------------------------------------------
                        | REPORT MENU FOR ITEMS                                          |
                        |   15) All items that are to do (with note Title)               |
                        |   16) Overall number of items to do / complete                 |
                        |   17) To do / Complete items by specific Category              |
                        |   18) Search for all items (by Item description)               |
                        ------------------------------------------------------------------
                        |   20) SAVE                                                     |
                        |   21) LOAD                                                     |
                        ------------------------------------------------------------------
                        |   0)  Exit                                                     |
                        ------------------------------------------------------------------
                        ==>>""");
        }

        private void runMenu() {
                int option = mainMenu();
                while (option != 0) {
                        switch (option) {
                                case 1 -> addNote();
                                case 2 -> listAllNotes();
                                case 3 -> updateNote();
                                case 4 -> deleteNote();
                                case 5 -> archiveNote();
                                case 6 -> addItemtoNote();
                                case 7 -> updateItem();
                                case 8 -> deleteItem();
                                //case 9 -> markItemAsCompleteToDo();
                                case 10 -> allNotesAndItems();
                              //  case 11 -> noteTodoComplete();
                                case 12 -> listNotesBySelectedCategory();
                                case 13 -> listNotesbySelectedPriority();
                                case 14 -> searchNote();
                                //case 15 -> allItemTodo();
                               // case 16 -> itemToDoComplete();
                                //case 17 -> todoCath();
                                //case 18 -> searchItem();
                                case 20 -> save();
                                case 21 -> load();
                                default -> System.out.println("Invalid option entered: " + option);
                        }

                        //pause the program so that the user can read what we just printed to the terminal window
                        ScannerInput.readNextLine("\nPress enter key to continue...");

                        //display the main menu again
                        option = mainMenu();
                }
        }

        private void addNote()
        {
                String noteTitle = ScannerInput.readNextLine("Enter the Note Title: ");
                int notePriority = ScannerInput.readNextInt("Enter the Priority Code: ");
                String noteCategory = ScannerInput.readNextLine("Please enter the Category: ");

                boolean isAdded = note.addNote(new Note(noteTitle,notePriority,noteCategory));
                if (isAdded)
                {
                        System.out.println("Note Added Successfully ");
                } else
                {
                        System.out.println("Note was not added");
                }
        }
        private void archiveNote()
        {
                if(note.numberOfArchivedNotes() > 0)
                {
                        int notePriority = ScannerInput.readNextInt("Enter the Priority Code: ");
                        boolean realIndex = note.archiveNote(notePriority);

                        if (realIndex == false)
                        {
                                System.out.println("Not Archived!");
                        }
                        else
                        {
                                        System.out.println("Note Archived");
                        }

                }
        }

        private void listAllNotes() {
                if (note.numberOfNotes() != 0)
                {
                        int noteOption = ScannerInput.readNextInt("Select Notes to view, 1 for All, " +
                                "2 for Active, 3 for Archived ");
                        if(noteOption ==1)
                        {
                                System.out.println(note.listAllNotes());
                        }
                        if(noteOption==2)
                        {
                                System.out.println(note.listActiveNotes());
                        }
                        if(noteOption==3)
                        {
                                System.out.println(note.listArchivedNotes());
                        }

                }
                else {
                        System.out.println(note.listAllNotes());
                }
        }

        private void searchNote()
        {
               String noteTitle = ScannerInput.readNextLine("Enter Note Title: ");

                System.out.println(note.searchNotebyTitle(noteTitle));
        }

        private void deleteNote()
        {
        listAllNotes();

        if (note.numberOfNotes() > 0)
        {
                int indexToDelete = ScannerInput.readNextInt("Enter the index of the Note to delete: ");

                if (note.isIndexValid(indexToDelete))
                {
                        note.deleteNote(indexToDelete);
                        System.out.println("Delete Successful! Note Deleted: ");
                }
                else
                {
                        System.out.println("Delete Not Successful ");
                }
        }
        }

        private void updateNote()
        {
                listAllNotes();

                if (note.numberOfNotes() > 0) {
                        int indexToUpdate = ScannerInput.readNextInt("Enter the Index of the Note to update");
                        if (note.isIndexValid(indexToUpdate)) {
                                String noteTitle = ScannerInput.readNextLine("Enter the Note Title:");
                                int notePriority = ScannerInput.readNextInt("Enter the Priority Code: ");
                                String noteCategory = ScannerInput.readNextLine("Enter Note Category");
                                char noteArchived = ScannerInput.readNextChar("Is this archived (y/n):");

                                if ((noteArchived == 'y') || ((noteArchived == 'Y'))) {
                                        if (note.updateNote(indexToUpdate, noteTitle, notePriority, noteCategory)) {
                                                System.out.println("Update Successful");
                                        } else {
                                                System.out.println("Update not successful");
                                        }
                                } else System.out.println("There are no notes for this number");
                        }
                }
        }

        private void addItemtoNote() {
                int i = ScannerInput.readNextInt("Enter the index of the Note");

                if (note.isIndexValid(i)) {
                        String itemDescription = ScannerInput.readNextLine("Enter the Item Description: ");
                        boolean isAdded = note.addItemtoNote(i, itemDescription);

                        if (isAdded) {
                                System.out.println("Item Added Successfully ");
                        } else {
                                System.out.println("Item was not added");
                        }

                }
        }

        private void updateItem()
        {
                if (note.numberOfItems() > 0) {
                        int index = ScannerInput.readNextInt("Enter the Index of the Item to update");
                        if (note.isIndexValid(index))
                        {
                                String itemDescription = ScannerInput.readNextLine("Enter the Item Description:");

                                char itemComplete = ScannerInput.readNextChar("Is this archived (y/n):");

                                if ((itemComplete == 'y') || ((itemComplete == 'Y')))
                                {
                                        //note.updateItem(index,itemDescription);
                                        System.out.println("Update Successful");
                                }
                                else
                                {
                                        System.out.println("Update not successful");
                                }
                        }
                        else
                        {
                                System.out.println("There are no items for this number");
                        }
                }
        }
        private void deleteItem()
        {

                if (note.numberOfItems() > 0)
                {
                        int indexToDelete = ScannerInput.readNextInt("Enter the index of the Item to delete: ");

                        //Note itemToDelete = note.deleteItemfromNote(noteIndex, indexToDelete);

                        if (indexToDelete != 0)
                        {
                               // System.out.println("Delete Successful! Item Deleted: ") +Note.deleteItem(indexToDelete);
                        } else
                        {
                                System.out.println("Delete Not Successful ");
                        }
                }

        }


        private void allNotesAndItems()
        {
                System.out.println("List of Notes are: ");
                System.out.println(note.listAllNotes());
        }


        private void listNotesBySelectedCategory()
        {
                double cate = ScannerInput.readNextDouble("Enter the Category you wish to view");
                //System.out.println(note.listNotesbySelectedCategory(cate));
        }

        private void listNotesbySelectedPriority()
        {
                int prior = ScannerInput.readNextInt("Enter the priority you wish to view: ");
                System.out.println(note.listNotesbySelectedPriority(prior));
        }

        private void save()
        {
                try
                {
                        save();
                }
                catch (Exception e)
                {
                        System.err.println("Error writing to file: " + e);
                }
        }


        private void load()
        {
                try
                {
                        load();
                }
                catch (Exception e)
                {
                        System.err.println("Error reading from file " +e);
                }
        }
}


