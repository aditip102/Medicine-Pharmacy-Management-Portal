
package com.pharmacy;

import java.util.ArrayList;

public class LowStockManager {

    private final ArrayList<MedicineStock> medicines = new ArrayList<>();

    public void addMedicine(int medicineId, String medicineName, int quantity) {
        medicines.add(new MedicineStock(medicineId, medicineName, quantity));
    }

    public void showLowStock() {
        boolean found = false;

        System.out.println("\n=== LOW STOCK MEDICINES ===");

        for (MedicineStock medicine : medicines) {
            if (medicine.quantity <= 10) {
                found = true;
                System.out.println("Medicine ID: " + medicine.medicineId);
                System.out.println("Medicine Name: " + medicine.medicineName);
                System.out.println("Quantity: " + medicine.quantity);
                System.out.println("Status: LOW STOCK");
                System.out.println("----------------------");
            }
        }

        if (!found) {
            System.out.println("No medicines have low stock.");
        }
    }

    public void displayLowStock() {
        showLowStock();
    }
}
