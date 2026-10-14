import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@param <T> 
public class LibraryCatalog<T> {

    private final Map<String, LibraryItem<T>> items;

    public LibraryCatalog() {
        items = new LinkedHashMap<>();
    }

   
      @param item the library item to add
      @throws IllegalArgumentException if the item is null
   
    public void addItem(LibraryItem<T> item) {

        if (item == null) {
            throw new IllegalArgumentException(
                    "Cannot add a null item.");
        }

        String itemID = item.getItemID();

        if (items.containsKey(itemID)) {
            throw new IllegalArgumentException(
                    "An item with ID " + itemID
                            + " already exists.");
        }

        items.put(itemID, item);
    }

    /**
     * Removes an item using its ID.
     *
     * @param itemID the ID of the item to remove
     * @return the removed item
     * @throws IllegalArgumentException if the ID is invalid
     * @throws ItemNotFoundException if the item does not exist
     */
    public LibraryItem<T> removeItem(String itemID)
            throws ItemNotFoundException {

        if (itemID == null || itemID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Item ID cannot be empty.");
        }

        LibraryItem<T> removedItem = items.remove(itemID);

        if (removedItem == null) {
            throw new ItemNotFoundException(
                    "No item found with ID: " + itemID);
        }

        return removedItem;
    }

    /**
     * Retrieves an item using its ID.
     *
     * @param itemID the ID of the item to retrieve
     * @return the requested library item
     * @throws ItemNotFoundException if the item does not exist
     */
    public LibraryItem<T> getItem(String itemID)
            throws ItemNotFoundException {

        LibraryItem<T> item = items.get(itemID);

        if (item == null) {
            throw new ItemNotFoundException(
                    "No item found with ID: " + itemID);
        }

        return item;
    }

    /**
     * Returns all items currently in the catalog.
     */
    public Collection<LibraryItem<T>> getAllItems() {
        return java.util.Collections.unmodifiableCollection(
                items.values());
    }

    /**
     * Returns the number of items in the catalog.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Checks whether the catalog is empty.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Custom exception for missing library items.
     */
    public static class ItemNotFoundException
            extends Exception {

        public ItemNotFoundException(String message) {
            super(message);
        }
    }
}