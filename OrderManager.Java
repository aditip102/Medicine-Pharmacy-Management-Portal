import java.util.ArrayList;

public class OrderManager {

    ArrayList<CartOrder> orders =
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


        CartOrder order =
            new CartOrder(
                nextOrderId,
                cart.getCartList()
            );


        orders.add(order);


        System.out.println(
            "\nOrder placed successfully!"
        );

        System.out.println(
            "Order ID: " + nextOrderId
        );


        nextOrderId++;


        // Clear cart after order
        cart.clearCart();
    }


    // Display previous orders
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


        for (CartOrder order : orders) {

            order.displayOrder();
        }
    }


    // Update order status
    public void updateStatus(
        int orderId,
        String newStatus) {


        for (CartOrder order : orders) {

            if (order.orderId == orderId) {

                order.status = newStatus;


                System.out.println(
                    "Order status updated successfully."
                );

                return;
            }
        }


        System.out.println(
            "Order not found."
        );
    }
