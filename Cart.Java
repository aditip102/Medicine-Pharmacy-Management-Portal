public class Cart {

    int medicineId;
    String medicineName;
    double price;
    int quantity;

    public Cart(int medicineId, String medicineName,
                double price, int quantity) {

        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    public void display() {

        System.out.println(
            medicineId + " | " +
            medicineName + " | ₹" +
            price + " | Quantity: " +
            quantity + " | Total: ₹" +
            getTotal()
        );
    }
}
