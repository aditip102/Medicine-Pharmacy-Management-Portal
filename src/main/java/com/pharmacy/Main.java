package com.pharmacy;

public class Main {

    public static void main(String[] args) {

        Pharmacy pharmacy = new Pharmacy();
        Supplier supplier1 = new Supplier(
                1,
                "ABC Pharma",
                "9876543210",
                "ABC Pharmaceuticals"
        );

        Supplier supplier2 = new Supplier(
                2,
                "Medico Suppliers",
                "9123456780",
                "Medico Healthcare"
        );

        pharmacy.addSupplier(supplier1);
        pharmacy.addSupplier(supplier2);
        Medicine medicine1 = new Medicine(
                1,
                "Paracetamol",
                "Pain Relief",
                50.0,
                100,
                "2027-12-31"
        );

        Medicine medicine2 = new Medicine(
                2,
                "Amoxicillin",
                "Antibiotic",
                120.0,
                50,
                "2027-06-30"
        );

        pharmacy.addMedicine(medicine1);
        pharmacy.addMedicine(medicine2);

        System.out.println("=== PHARMACY MANAGEMENT SYSTEM ===");

        System.out.println("\nAvailable Medicines:");

        for (Medicine medicine : pharmacy.getMedicines()) {
            System.out.println(
                    medicine.getId() + " | " +
                            medicine.getName() + " | ₹" +
                            medicine.getPrice() + " | Stock: " +
                            medicine.getStock()
            );
        }

        System.out.println("\nSearching for Paracetamol...");

        Medicine result = pharmacy.searchMedicine("Paracetamol");

        if (result != null) {
            System.out.println("Medicine found: " + result.getName());
            System.out.println("Stock available: " + result.getStock());
        } else {
            System.out.println("Medicine not found.");
        }

        System.out.println("\nChecking Amoxicillin stock...");

        if (pharmacy.checkStock("Amoxicillin")) {
            System.out.println("Amoxicillin is in stock.");
        } else {
            System.out.println("Amoxicillin is out of stock.");
        }
        pharmacy.checkExpiry();
        pharmacy.checkLowStock();
        System.out.println("\n=== SUPPLIER MANAGEMENT ===");

        for (Supplier supplier : pharmacy.getSuppliers()) {

            System.out.println(
                    "ID: " + supplier.getSupplierId() +
                            " | Name: " + supplier.getSupplierName() +
                            " | Contact: " + supplier.getContact() +
                            " | Company: " + supplier.getCompany()
            );
        }
        Order order = new Order(
                101,
                "Rahul",
                medicine1,
                2
        );

        System.out.println("\n=== CUSTOMER ORDER ===");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Medicine: " + order.getMedicine().getName());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total Price: ₹" + order.getTotalPrice());
    }
}
