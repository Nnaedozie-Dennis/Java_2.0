
public class LibraryItem<T> {

    private final String title;
    private final String author;
    private final String itemID;
    private final T category;

    public LibraryItem(String title, String author,
                       String itemID, T category) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Title cannot be empty.");
        }

        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Author cannot be empty.");
        }

        if (itemID == null || itemID.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Item ID cannot be empty.");
        }

        if (category == null) {
            throw new IllegalArgumentException(
                    "Category cannot be null.");
        }

        this.title = title;
        this.author = author;
        this.itemID = itemID;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getItemID() {
        return itemID;
    }

    public T getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemID
                + " | Title: " + title
                + " | Author: " + author
                + " | Category: " + category;
    }
}