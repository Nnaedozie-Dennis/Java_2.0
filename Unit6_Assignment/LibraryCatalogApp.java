import java.util.Scanner;

public class LibraryCatalogApp {

    public static void main(String[] args) {

        LibraryCatalog<String> catalog =
                new LibraryCatalog<>();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        System.out.println("================================");
        System.out.println("     GENERIC LIBRARY CATALOG");
        System.out.println("================================");

        while (running) {

            System.out.println("\n1. Add a library item");
            System.out.println("2. Remove a library item");
            System.out.println("3. Retrieve item details");
            System.out.println("4. View current catalog");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            try {

                switch (choice) {

                    case "1":
                        System.out.print("Enter item ID: ");
                        String itemID = scanner.nextLine().trim();

                        System.out.print("Enter title: ");
                        String title = scanner.nextLine().trim();

                        System.out.print("Enter author: ");
                        String author = scanner.nextLine().trim();

                        System.out.print(
                                "Enter category (Book, DVD, Magazine): ");
                        String category =
                                scanner.nextLine().trim();

                        LibraryItem<String> newItem =
                                new LibraryItem<>(
                                        title,
                                        author,
                                        itemID,
                                        category);

                        catalog.addItem(newItem);

                        System.out.println(
                                "Item added successfully.");
                        break;

                    case "2":
                        System.out.print(
                                "Enter the ID of the item to remove: ");

                        String removeID =
                                scanner.nextLine().trim();

                        LibraryItem<String> removedItem =
                                catalog.removeItem(removeID);

                        System.out.println(
                                "Item removed successfully:");
                        System.out.println(removedItem);
                        break;

                    case "3":
                        System.out.print(
                                "Enter the ID of the item to retrieve: ");

                        String searchID =
                                scanner.nextLine().trim();

                        LibraryItem<String> foundItem =
                                catalog.getItem(searchID);

                        System.out.println(
                                "\nItem details:");
                        System.out.println(foundItem);
                        break;

                    case "4":
                        System.out.println(
                                "\nCURRENT LIBRARY CATALOG");
                        System.out.println("----------------------");

                        if (catalog.isEmpty()) {
                            System.out.println(
                                    "The catalog is empty.");
                        } else {
                            for (LibraryItem<String> item
                                    : catalog.getAllItems()) {

                                System.out.println(item);
                            }

                            System.out.println(
                                    "Total items: "
                                            + catalog.getItemCount());
                        }
                        break;

                    case "5":
                        running = false;
                        System.out.println(
                                "Thank you for using the library catalog.");
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Please choose 1 to 5.");
                }

            } catch (
                    LibraryCatalog.ItemNotFoundException
                    | IllegalArgumentException exception) {

                System.out.println(
                        "Error: " + exception.getMessage());

            } catch (Exception exception) {

                System.out.println(
                        "An unexpected error occurred: "
                                + exception.getMessage());
            }
        }

        scanner.close();
    }
}