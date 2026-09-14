import com.ecommerce.Customer;
import com.ecommerce.Product;
import com.ecommerce.orders.Order;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println("======================================");
            System.out.println("       SIMPLE E COMMERCE SYSTEM       ");
            System.out.println("======================================");

            // Create products
            Product laptop = new Product(
                "P001",
                "Laptop",
                850.00,
                "Electronics"
            );

            Product headphones = new Product(
                "P002",
                "Wireless Headphones",
                75.50,
                "Electronics"
            );

            Product backpack = new Product(
                "P003",
                "Laptop Backpack",
                45.00,
                "Accessories"
            );

            // Display available products
            System.out.println("\n----- Available Products -----");
            System.out.println(laptop);
            System.out.println(headphones);
            System.out.println(backpack);

            // Create customer
            Customer customer = new Customer(
                "C001",
                "Dennis Chukwuneta"
            );

            System.out.println("\n----- Customer Information -----");
            System.out.println(customer);

            // Add products to shopping cart
            customer.addProduct(laptop);
            customer.addProduct(headphones);
            customer.addProduct(backpack);

            // Display shopping cart
            customer.displayCart();

            // Create and place order
            Order order = customer.placeOrder("ORD001");

            System.out.println("\nOrder created successfully.");

            order.placeOrder();

            // Display order information
            System.out.println(order.generateOrderSummary());

            // Update order status
            order.updateOrderStatus("Processing");

            System.out.println("Updated Order Status: "
                + order.getOrderStatus());

            System.out.println("\n======================================");
            System.out.println("       E COMMERCE DEMO COMPLETE       ");
            System.out.println("======================================");

        } catch (IllegalArgumentException | IllegalStateException exception) {

            System.out.println(
                "\nError: " + exception.getMessage()
            );
        }
    }
}