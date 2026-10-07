import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CartManager cart =
            new CartManager();

        OrderManager order =
            new OrderManager();

        LowStockManager stock =
            new LowStockManager();


        // Sample medicines for testing
        stock.addMedicine(
            101,
            "Paracetamol 500mg",
            50
        );

        stock.addMedicine(
            102,
            "Cetirizine",
            25
        );

        stock.addMedicine(
            103,
            "Azithromycin",
            4
        );


        while (true) {

            System.out.println(
                "\n=== MEDICINE PORTAL ==="
            );

            System.out.println("1. Add Medicine to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Remove from Cart");
            System.out.println("4. Place Order");
            System.out.println("5. My Orders");
            System.out.println("6. Update Order Status");
            System.out.println("7. Low Stock Alerts");
            System.out.println("8. Exit");

            System.out.print(
                "Enter your choice: "
            );

            int choice = sc.nextInt();


            switch (choice) {

                case 1:

                    System.out.print(
                        "Enter Medicine ID: "
                    );

                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                        "Enter Medicine Name: "
                    );

                    String name = sc.nextLine();

                    System.out.print(
                        "Enter Price: "
                    );

                    double price = sc.nextDouble();

                    System.out.print(
                        "Enter Quantity: "
                    );

                    int quantity = sc.nextInt();

                    cart.addToCart(
                        id,
                        name,
                        price,
                        quantity
                    );

                    break;


                case 2:

                    cart.displayCart();

                    break;


                case 3:

                    System.out.print(
                        "Enter Medicine ID to remove: "
                    );

                    int removeId = sc.nextInt();

                    cart.removeFromCart(
                        removeId
                    );

                    break;


                case 4:

                    order.placeOrder(cart);

                    break;


                case 5:

                    order.displayOrders();

                    break;


                case 6:

                    System.out.print(
                        "Enter Order ID: "
                    );

                    int orderId = sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                        "Enter new status: "
                    );

                    String status =
                        sc.nextLine();

                    order.updateStatus(
                        orderId,
                        status
                    );

                    break;


                case 7:

                    stock.showLowStock();

                    break;


                case 8:

                    System.out.println(
                        "Thank you for using Medicine Portal!"
                    );

                    sc.close();

                    return;


                default:

                    System.out.println(
                        "Invalid choice."
                    );
            }
        }
    }
}
