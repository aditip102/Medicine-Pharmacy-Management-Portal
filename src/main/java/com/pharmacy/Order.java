package com.pharmacy;

public class Order {

    private int orderId;
    private String customerName;
    private Medicine medicine;
    private int quantity;

    public Order(int orderId, String customerName,
                 Medicine medicine, int quantity) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return medicine.getPrice() * quantity;
    }
}