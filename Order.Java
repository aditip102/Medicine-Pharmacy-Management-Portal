import java.util.ArrayList;

public class Order {

    int orderId;
    ArrayList<Cart> items;
    String status;

    public Order(int orderId,
                 ArrayList<Cart> items) {

        this.orderId = orderId;

        this.items =
            new ArrayList<>(items);

        this.status = "Pending";
    }


    public void displayOrder() {

        System.out.println("\n=== ORDER ===");

        System.out.println(
            "Order ID: " + orderId
        );

        System.out.println(
            "Status: " + status
        );

        System.out.println("Medicines:");

        for (Cart item : items) {

            item.display();
        }
    }
}
