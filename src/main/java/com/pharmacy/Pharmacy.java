package com.pharmacy;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {

    private List<Medicine> medicines;
    private List<Supplier> suppliers;
    public Pharmacy() {
        medicines = new ArrayList<>();
        suppliers = new ArrayList<>();
    }
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public Medicine searchMedicine(String name) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(name)) {
                return medicine;
            }
        }
        return null;
    }

    public boolean checkStock(String name) {
        Medicine medicine = searchMedicine(name);
        return medicine != null && medicine.getStock() > 0;
    }
    public void checkExpiry() {

        System.out.println("\n=== EXPIRY TRACKING ===");

        for (Medicine medicine : medicines) {
            System.out.println(
                    medicine.getName() +
                            " | Expiry Date: " +
                            medicine.getExpiryDate()
            );
        }
    }
    public void checkLowStock() {

        int threshold = 20;

        System.out.println("\n=== LOW STOCK ALERTS ===");

        boolean found = false;

        for (Medicine medicine : medicines) {

            if (medicine.getStock() < threshold) {

                System.out.println(
                        "LOW STOCK: " +
                                medicine.getName() +
                                " | Remaining: " +
                                medicine.getStock()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No medicines are low on stock.");
        }
    }
}