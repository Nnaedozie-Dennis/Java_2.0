public class LibraryCatalogTest {

    public static void main(String[] args) throws Exception {

        testLibraryItem();
        testAddAndRetrieveItem();
        testRemoveItem();
        testMissingItem();
        testDuplicateID();
        testDifferentCategoryTypes();
        testInvalidItem();

        System.out.println(
                "\nAll library catalog tests passed.");
    }

    private static void testLibraryItem() {

        LibraryItem<String> book =
                new LibraryItem<>(
                        "Things Fall Apart",
                        "Chinua Achebe",
                        "B001",
                        "Book");

        assert book.getTitle().equals("Things Fall Apart");
        assert book.getAuthor().equals("Chinua Achebe");
        assert book.getItemID().equals("B001");
        assert book.getCategory().equals("Book");

        System.out.println("PASS: Library item creation");
    }

    private static void testAddAndRetrieveItem()
            throws Exception {

        LibraryCatalog<String> catalog =
                new LibraryCatalog<>();

        LibraryItem<String> book =
                new LibraryItem<>(
                        "Java Programming",
                        "John Smith",
                        "B002",
                        "Book");

        catalog.addItem(book);

        assert catalog.getItemCount() == 1;
        assert catalog.getItem("B002").getTitle()
                .equals("Java Programming");

        System.out.println("PASS: Add and retrieve item");
    }

    private static void testRemoveItem()
            throws Exception {

        LibraryCatalog<String> catalog =
                new LibraryCatalog<>();

        LibraryItem<String> dvd =
                new LibraryItem<>(
                        "Java Tutorial",
                        "Jane Brown",
                        "D001",
                        "DVD");

        catalog.addItem(dvd);

        LibraryItem<String> removed =
                catalog.removeItem("D001");

        assert removed.getTitle().equals("Java Tutorial");
        assert catalog.getItemCount() == 0;
        assert catalog.isEmpty();

        System.out.println("PASS: Remove item");
    }

    private static void testMissingItem()
            throws Exception {

        LibraryCatalog<String> catalog =
                new LibraryCatalog<>();

        boolean exceptionThrown = false;

        try {
            catalog.removeItem("UNKNOWN");
        } catch (
                LibraryCatalog.ItemNotFoundException exception) {

            exceptionThrown = true;
        }

        assert exceptionThrown;

        System.out.println("PASS: Missing item error handling");
    }

    private static void testDuplicateID() {

        LibraryCatalog<String> catalog =
                new LibraryCatalog<>();

        LibraryItem<String> first =
                new LibraryItem<>(
                        "Book One",
                        "Author One",
                        "B003",
                        "Book");

        LibraryItem<String> second =
                new LibraryItem<>(
                        "Book Two",
                        "Author Two",
                        "B003",
                        "Book");

        catalog.addItem(first);

        boolean exceptionThrown = false;

        try {
            catalog.addItem(second);
        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }

        assert exceptionThrown;
        assert catalog.getItemCount() == 1;

        System.out.println("PASS: Duplicate ID prevention");
    }

    private static void testDifferentCategoryTypes()
            throws Exception {

        LibraryCatalog<Integer> numericCatalog =
                new LibraryCatalog<>();

        LibraryItem<Integer> magazine =
                new LibraryItem<>(
                        "Science Monthly",
                        "Science Editors",
                        "M001",
                        2026);

        numericCatalog.addItem(magazine);

        assert numericCatalog.getItem("M001")
                .getCategory() == 2026;

        System.out.println(
                "PASS: Generic catalog with Integer category");

        LibraryCatalog<String> textCatalog =
                new LibraryCatalog<>();

        LibraryItem<String> book =
                new LibraryItem<>(
                        "History of Rome",
                        "Mary Jones",
                        "B004",
                        "Book");

        textCatalog.addItem(book);

        assert textCatalog.getItem("B004")
                .getCategory().equals("Book");

        System.out.println(
                "PASS: Generic catalog with String category");
    }

    private static void testInvalidItem() {

        boolean exceptionThrown = false;

        try {
            new LibraryItem<>(
                    "",
                    "Unknown Author",
                    "B005",
                    "Book");

        } catch (IllegalArgumentException exception) {
            exceptionThrown = true;
        }

        assert exceptionThrown;

        System.out.println("PASS: Invalid item validation");
    }
}