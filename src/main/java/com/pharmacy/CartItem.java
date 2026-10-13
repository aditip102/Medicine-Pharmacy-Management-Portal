
package com.pharmacy;

public class CartItem {
    private final int medicineId;
    private final String medicineName;
    private final double price;
    private int quantity;

    public CartItem(int medicineId, String medicineName,
                    double price, int quantity) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public double getSubtotal() {
        return price * quantity;
    }
}

