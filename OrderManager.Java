import java.util.ArrayList;

public class OrderManager {

    ArrayList<Order> orders =
        new ArrayList<>();

    int nextOrderId = 1001;


    // Place order
    public void placeOrder(CartManager cart) {

        if (cart.getCartList().isEmpty()) {

            System.out.println(
                "Cannot place order. Cart is empty."
            );

            return;
        }

        Order order = new Order(
            nextOrderId,
            cart.getCartList()
        );

        orders.add(order);

        System.out.println(
            "Order placed successfully!"
        );

        System.out.println(
            "Order ID: " + nextOrderId
        );

        nextOrderId++;

        cart.clearCart();
    }


    // Display all orders
    public void displayOrders() {

        if (orders.isEmpty()) {

            System.out.println(
                "No previous orders."
            );

            return;
        }

        System.out.println(
            "\n=== MY ORDERS ==="
        );

        for (Order order : orders) {

            order.displayOrder();
        }
    }


    // Update order status
    public void updateStatus(
        int orderId,
        String newStatus) {

        for (Order order : orders) {

            if (order.orderId == orderId) {

                order.status = newStatus;

                System.out.println(
                    "Order status updated."
                );

                return;
            }
        }

        System.out.println(
            "Order not found."
        );
    }
}
