import java.util.ArrayList;

public class LowStockManager {

    ArrayList<MedicineStock> medicines =
        new ArrayList<>();


    // Add medicine
    public void addMedicine(
        int id,
        String name,
        int quantity) {


        MedicineStock medicine =
            new MedicineStock(
                id,
                name,
                quantity
            );


        medicines.add(medicine);
    }


    // Show low stock medicines
    public void showLowStock() {

        System.out.println(
            "\n=== LOW STOCK ALERTS ==="
        );


        boolean found = false;


        for (MedicineStock medicine : medicines) {

            if (medicine.quantity < 10) {

                found = true;


                System.out.println(
                    "Medicine ID: " +
                    medicine.medicineId
                );


                System.out.println(
                    "Medicine: " +
                    medicine.medicineName
                );


                System.out.println(
                    "Quantity: " +
                    medicine.quantity
                );


                System.out.println(
                    " LOW STOCK"
                );


                System.out.println(
                    "------"
                );
            }
        }


        if (!found) {

            System.out.println(
                "No medicines have low stock."
            );
        }
    }
