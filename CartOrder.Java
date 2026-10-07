import java.util.ArrayList;

public class CartOrder {

    int orderId;
    ArrayList<Cart> items;
    String status;


    public CartOrder(int orderId,
                     ArrayList<Cart> items) {

        this.orderId = orderId;

        this.items =
            new ArrayList<>(items);

        status = "Pending";
    }


    // Display order
    public void displayOrder() {

        System.out.println(
            "\n=== ORDER ==="
        );

        System.out.println(
            "Order ID: " + orderId
        );

        System.out.println(
            "Status: " + status
        );

        System.out.println(
            "Medicines:"
        );

        for (Cart item : items) {

            item.display();
        }
    }
}
