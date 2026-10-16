
package com.pharmacy;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedOrder {

    private final String orderId;
    private final String customerName;
    private final List<CartItem> items;
    private final double total;
    private final String status;

    public ConfirmedOrder(String orderId, String customerName,
                          List<CartItem> items, double total) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();

        for (CartItem item : items) {
            this.items.add(new CartItem(
                    item.getMedicineId(),
                    item.getMedicineName(),
                    item.getPrice(),
                    item.getQuantity()
            ));
        }

        this.total = total;
        this.status = "Confirmed";
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}
