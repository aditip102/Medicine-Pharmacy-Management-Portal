
package com.pharmacy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pharmacy {

    private static final List<Medicine> medicines = new ArrayList<>();
    private static final List<Supplier> suppliers = new ArrayList<>();

    public synchronized void addMedicine(Medicine medicine) {
        if (getMedicineById(medicine.getId()) != null) {
            throw new IllegalArgumentException("Medicine ID already exists.");
        }
        medicines.add(medicine);
    }

    public synchronized List<Medicine> getMedicines() {
        return new ArrayList<>(medicines);
    }

    public synchronized Medicine getMedicineById(int id) {
        for (Medicine medicine : medicines) {
            if (medicine.getId() == id) {
                return medicine;
            }
        }
        return null;
    }

    public synchronized boolean editMedicine(
            int id, String name, String category,
            double price, int stock, String expiryDate) {

        Medicine medicine = getMedicineById(id);

        if (medicine == null) {
            return false;
        }

        medicine.setName(name);
        medicine.setCategory(category);
        medicine.setPrice(price);
        medicine.setStock(stock);
        medicine.setExpiryDate(expiryDate);

        return true;
    }

    public synchronized boolean deleteMedicine(int id) {
        return medicines.removeIf(m -> m.getId() == id);
    }

    // Compatible with Main.java: returns one Medicine.
    public synchronized Medicine searchMedicine(String keyword) {
        if (keyword == null) {
            return null;
        }

        String search = keyword.trim().toLowerCase();

        for (Medicine medicine : medicines) {
            if (medicine.getName().toLowerCase().contains(search)
                    || medicine.getCategory().toLowerCase().contains(search)
                    || String.valueOf(medicine.getId()).equals(search)) {
                return medicine;
            }
        }

        return null;
    }

    // Used by the web page to display all matching medicines.
    public synchronized List<Medicine> searchMedicines(String keyword) {
        List<Medicine> results = new ArrayList<>();

        if (keyword == null) {
            return results;
        }

        String search = keyword.trim().toLowerCase();

        for (Medicine medicine : medicines) {
            if (medicine.getName().toLowerCase().contains(search)
                    || medicine.getCategory().toLowerCase().contains(search)
                    || String.valueOf(medicine.getId()).equals(search)) {
                results.add(medicine);
            }
        }

        return results;
    }

    public synchronized boolean checkStock(String name) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(name)) {
                return medicine.getStock() > 0;
            }
        }
        return false;
    }

    public synchronized boolean updateStock(int id, int quantity) {
        Medicine medicine = getMedicineById(id);

        if (medicine == null || quantity < 0) {
            return false;
        }

        medicine.setStock(quantity);
        return true;
    }

    public synchronized void checkExpiry() {
        System.out.println("\n=== EXPIRY CHECK ===");
        LocalDate today = LocalDate.now();

        for (Medicine medicine : medicines) {
            try {
                LocalDate expiry = LocalDate.parse(medicine.getExpiryDate());

                if (!expiry.isAfter(today)) {
                    System.out.println(
                            medicine.getName() + " has expired or expires today.");
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid expiry date for " + medicine.getName());
            }
        }
    }

    public synchronized void checkLowStock() {
        System.out.println("\n=== LOW STOCK ALERT ===");
        boolean found = false;

        for (Medicine medicine : medicines) {
            if (medicine.getStock() <= 10) {
                System.out.println(
                        medicine.getName() + " - Stock: " + medicine.getStock());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No medicines are low in stock.");
        }
    }

    public synchronized void addSupplier(Supplier supplier) {
        for (Supplier existing : suppliers) {
            if (existing.getSupplierId() == supplier.getSupplierId()) {
                throw new IllegalArgumentException("Supplier ID already exists.");
            }
        }
        suppliers.add(supplier);
    }

    public synchronized List<Supplier> getSuppliers() {
        return new ArrayList<>(suppliers);
    }
}
