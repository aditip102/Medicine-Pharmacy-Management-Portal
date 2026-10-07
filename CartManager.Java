import java.util.ArrayList;

public class CartManager {

    ArrayList<Cart> cartList = new ArrayList<>();


    // Add medicine to cart
    public void addToCart(int id, String name,
                          double price, int quantity) {

        Cart item = new Cart(
            id, name, price, quantity
        );

        cartList.add(item);

        System.out.println("Medicine added to cart.");
    }


    // Display cart
    public void displayCart() {

        if (cartList.isEmpty()) {

            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n========== MY CART ==========");

        for (Cart item : cartList) {
            item.display();
        }
    }


    // Remove medicine
    public void removeFromCart(int medicineId) {

        for (Cart item : cartList) {

            if (item.medicineId == medicineId) {

                cartList.remove(item);

                System.out.println(
                    "Medicine removed from cart."
                );

                return;
            }
        }

        System.out.println(
            "Medicine not found in cart."
        );
    }


    // Get cart
    public ArrayList<Cart> getCartList() {
        return cartList;
    }


    // Clear cart
    public void clearCart() {
        cartList.clear();
    }
}
