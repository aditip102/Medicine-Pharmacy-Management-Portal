
package com.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/pharmacy")
public class PharmacyServlet extends HttpServlet {

    private Pharmacy pharmacy;

    @Override
    public void init() throws ServletException {
        pharmacy = new Pharmacy();

        // Add demo medicines only when the shared inventory is empty.
        if (pharmacy.getMedicines().isEmpty()) {
            pharmacy.addMedicine(new Medicine(
                    1, "Paracetamol", "Pain Relief", 50, 100, "2027-12-31"));
            pharmacy.addMedicine(new Medicine(
                    2, "Amoxicillin", "Antibiotic", 120, 50, "2027-06-30"));
            pharmacy.addMedicine(new Medicine(
                    3, "Cetirizine", "Allergy", 80, 25, "2026-11-30"));
            pharmacy.addMedicine(new Medicine(
                    4, "Azithromycin", "Antibiotic", 150, 8, "2026-10-20"));
            pharmacy.addMedicine(new Medicine(
                    5, "Ibuprofen", "Pain Relief", 65, 40, "2027-08-15"));
            pharmacy.addMedicine(new Medicine(
                    6, "Omeprazole", "Acidity", 90, 35, "2027-09-10"));
            pharmacy.addMedicine(new Medicine(
                    7, "Metformin", "Diabetes", 75, 60, "2027-11-20"));
            pharmacy.addMedicine(new Medicine(
                    8, "Vitamin C", "Supplement", 110, 45, "2028-01-15"));
            pharmacy.addMedicine(new Medicine(
                    9, "ORS", "Rehydration", 25, 80, "2027-07-31"));
            pharmacy.addMedicine(new Medicine(
                    10, "Loratadine", "Allergy", 70, 30, "2027-10-15"));
            pharmacy.addMedicine(new Medicine(
                    11, "Pantoprazole", "Acidity", 95, 20, "2027-12-10"));
            pharmacy.addMedicine(new Medicine(
                    12, "Diclofenac", "Pain Relief", 85, 18, "2027-05-30"));
            pharmacy.addMedicine(new Medicine(
                    13, "Multivitamin", "Supplement", 140, 55, "2028-03-31"));
            pharmacy.addMedicine(new Medicine(
                    14, "Calcium Tablets", "Supplement", 100, 32, "2027-09-25"));
            pharmacy.addMedicine(new Medicine(
                    15, "Cough Syrup", "Cough and Cold", 95, 22, "2027-04-30"));
            pharmacy.addMedicine(new Medicine(
                    16, "Antacid Tablets", "Acidity", 45, 6, "2027-08-20"));
            pharmacy.addMedicine(new Medicine(
                    17, "Nasal Saline Spray", "Nasal Care", 85, 28, "2027-11-30"));
            pharmacy.addMedicine(new Medicine(
                    18, "Antiseptic Solution", "First Aid", 75, 15, "2027-06-15"));
            pharmacy.addMedicine(new Medicine(
                    19, "Bandages", "First Aid", 30, 50, "2028-01-31"));
            pharmacy.addMedicine(new Medicine(
                    20, "Zinc Tablets", "Supplement", 60, 9, "2027-10-31"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String keyword = request.getParameter("search");
        List<Medicine> medicines;

        if (keyword != null && !keyword.trim().isEmpty()) {
            medicines = pharmacy.searchMedicines(keyword);
        } else {
            medicines = pharmacy.getMedicines();
        }

        List<Medicine> allMedicines = pharmacy.getMedicines();

        int lowStockCount = 0;
        int expiredCount = 0;
        LocalDate today = LocalDate.now();

        for (Medicine medicine : allMedicines) {
            if (medicine.getStock() <= 10) {
                lowStockCount++;
            }

            try {
                if (!LocalDate.parse(medicine.getExpiryDate()).isAfter(today)) {
                    expiredCount++;
                }
            } catch (Exception ignored) {
                // Invalid expiry dates are handled in the table.
            }
        }

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<title>Medicine Management</title>");

        out.println("<style>");
        out.println("*{box-sizing:border-box}");
        out.println("body{font-family:Arial,sans-serif;background:#f3f6fa;"
                + "color:#243047;margin:0;padding:24px}");
        out.println(".container{max-width:1250px;margin:auto}");
        out.println(".panel{background:white;padding:22px;margin:18px 0;"
                + "border-radius:12px;box-shadow:0 2px 10px #0000000d}");
        out.println("h1{color:#145c4b;margin-bottom:8px}");
        out.println("h2{font-size:20px;margin-top:0}");
        out.println(".stats{display:flex;gap:12px;flex-wrap:wrap}");
        out.println(".stat{flex:1;min-width:150px;background:#eaf4f0;"
                + "padding:18px;border-radius:9px}");
        out.println(".number{font-size:25px;font-weight:bold;color:#145c4b}");
        out.println("input{padding:10px;margin:5px 3px;border:1px solid #ccd4df;"
                + "border-radius:6px;max-width:100%}");
        out.println("button{padding:10px 14px;margin:5px 3px;border:0;"
                + "border-radius:6px;background:#16785e;color:white;cursor:pointer}");
        out.println(".danger{background:#c0392b}.edit{background:#2867b2}");
        out.println(".tablewrap{overflow-x:auto}");
        out.println("table{border-collapse:collapse;width:100%;background:white}");
        out.println("th,td{padding:11px;text-align:left;"
                + "border-bottom:1px solid #e4e9ef;white-space:nowrap}");
        out.println("th{background:#eaf4f0}");
        out.println(".low{color:#c0392b;font-weight:bold}");
        out.println(".expired{color:#c0392b;font-weight:bold}");
        out.println(".soon{color:#a65b00;font-weight:bold}");
        out.println(".message{padding:12px;background:#e5f6ec;"
                + "border-radius:6px;margin:12px 0}");
        out.println(".actions form{display:inline}");
        out.println("a{color:#16785e}");
        out.println("</style></head><body><div class='container'>");

        out.println("<h1>Medicine & Pharmacy Management</h1>");
        out.println("<p>Manage medicines, inventory, expiry dates and stock levels.</p>");

        String message = request.getParameter("message");
        if (message != null) {
            out.println("<div class='message'>" + escape(message) + "</div>");
        }

        // Dashboard summary
        out.println("<div class='panel'><div class='stats'>");
        out.println("<div class='stat'>Total Medicines<div class='number'>"
                + allMedicines.size() + "</div></div>");
        out.println("<div class='stat'>Low Stock<div class='number'>"
                + lowStockCount + "</div></div>");
        out.println("<div class='stat'>Expired / Expiring Today<div class='number'>"
                + expiredCount + "</div></div>");
        out.println("</div></div>");

        // Add medicine
        out.println("<div class='panel'><h2 id='add'>Add Medicine</h2>");
        out.println("<form method='post' action='pharmacy'>");
        out.println("<input type='hidden' name='action' value='add'>");
        out.println("<input type='number' name='id' placeholder='Medicine ID' min='1' required>");
        out.println("<input name='name' placeholder='Medicine Name' required>");
        out.println("<input name='category' placeholder='Category' required>");
        out.println("<input type='number' name='price' placeholder='Price' min='0.01' step='0.01' required>");
        out.println("<input type='number' name='stock' placeholder='Stock Quantity' min='0' required>");
        out.println("<input type='date' name='expiryDate' required>");
        out.println("<button type='submit'>Add Medicine</button>");
        out.println("</form></div>");

        // Search and view medicines
        out.println("<div class='panel'><h2>Search / View Medicines</h2>");
        out.println("<form method='get' action='pharmacy'>");
        out.println("<input name='search' placeholder='Search by ID, name or category' value='"
                + escape(keyword == null ? "" : keyword) + "'>");
        out.println("<button type='submit'>Search</button>");
        out.println("<a href='pharmacy'>View All Medicines</a>");
        out.println("</form></div>");

        // Edit medicine
        out.println("<div class='panel'><h2 id='edit'>Edit Medicine</h2>");
        out.println("<p>Enter the existing ID and all updated details.</p>");
        out.println("<form method='post' action='pharmacy'>");
        out.println("<input type='hidden' name='action' value='edit'>");
        out.println("<input type='number' name='id' placeholder='Existing Medicine ID' min='1' required>");
        out.println("<input name='name' placeholder='Updated Name' required>");
        out.println("<input name='category' placeholder='Updated Category' required>");
        out.println("<input type='number' name='price' placeholder='Updated Price' min='0.01' step='0.01' required>");
        out.println("<input type='number' name='stock' placeholder='Updated Stock' min='0' required>");
        out.println("<input type='date' name='expiryDate' required>");
        out.println("<button class='edit' type='submit'>Update Medicine</button>");
        out.println("</form></div>");

        // Update stock only
        out.println("<div class='panel'><h2 id='stock'>Update Stock Quantity</h2>");
        out.println("<form method='post' action='pharmacy'>");
        out.println("<input type='hidden' name='action' value='stock'>");
        out.println("<input type='number' name='id' placeholder='Medicine ID' min='1' required>");
        out.println("<input type='number' name='stock' placeholder='New Stock Quantity' min='0' required>");
        out.println("<button type='submit'>Update Stock</button>");
        out.println("</form>");
        out.println("<p>Medicines with 10 or fewer units are marked as low stock.</p>");
        out.println("</div>");

        // Inventory table
        out.println("<div class='panel'><h2>Medicine Inventory ("
                + medicines.size() + ")</h2>");

        if (keyword != null && !keyword.trim().isEmpty()) {
            out.println("<p>Search results for: <b>" + escape(keyword) + "</b></p>");
        }

        out.println("<div class='tablewrap'><table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Category</th>"
                + "<th>Price</th><th>Stock</th><th>Expiry Date</th><th>Actions</th></tr>");

        if (medicines.isEmpty()) {
            out.println("<tr><td colspan='7'>No medicines found.</td></tr>");
        }

        for (Medicine medicine : medicines) {
            out.println("<tr>");
            out.println("<td>" + medicine.getId() + "</td>");
            out.println("<td>" + escape(medicine.getName()) + "</td>");
            out.println("<td>" + escape(medicine.getCategory()) + "</td>");
            out.println("<td>₹" + String.format("%.2f", medicine.getPrice()) + "</td>");

            if (medicine.getStock() <= 10) {
                out.println("<td class='low'>" + medicine.getStock()
                        + " (Low Stock)</td>");
            } else {
                out.println("<td>" + medicine.getStock() + "</td>");
            }

            String expiry = medicine.getExpiryDate();

            try {
                LocalDate expiryDate = LocalDate.parse(expiry);

                if (!expiryDate.isAfter(today)) {
                    out.println("<td class='expired'>" + escape(expiry)
                            + " (Expired)</td>");
                } else if (!expiryDate.isAfter(today.plusDays(30))) {
                    out.println("<td class='soon'>" + escape(expiry)
                            + " (Expiring Soon)</td>");
                } else {
                    out.println("<td>" + escape(expiry) + "</td>");
                }
            } catch (Exception e) {
                out.println("<td>Invalid date</td>");
            }

            // Delete action
            out.println("<td class='actions'>");
            out.println("<form method='post' action='pharmacy' "
                    + "onsubmit=\"return confirm('Delete this medicine?')\">");
            out.println("<input type='hidden' name='action' value='delete'>");
            out.println("<input type='hidden' name='id' value='"
                    + medicine.getId() + "'>");
            out.println("<button class='danger' type='submit'>Delete</button>");
            out.println("</form></td>");

            out.println("</tr>");
        }

        out.println("</table></div></div>");
        out.println("<p><a href='index.jsp'>Back to Dashboard</a></p>");
        out.println("</div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String message;

        try {
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name").trim();
                String category = request.getParameter("category").trim();
                double price = Double.parseDouble(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String expiryDate = request.getParameter("expiryDate");

                validateMedicine(name, category, price, stock, expiryDate);

                pharmacy.addMedicine(new Medicine(
                        id, name, category, price, stock, expiryDate));

                message = "Medicine added successfully.";

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name").trim();
                String category = request.getParameter("category").trim();
                double price = Double.parseDouble(request.getParameter("price"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String expiryDate = request.getParameter("expiryDate");

                validateMedicine(name, category, price, stock, expiryDate);

                message = pharmacy.editMedicine(
                        id, name, category, price, stock, expiryDate)
                        ? "Medicine updated successfully."
                        : "Medicine ID not found.";

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));

                message = pharmacy.deleteMedicine(id)
                        ? "Medicine deleted successfully."
                        : "Medicine ID not found.";

            } else if ("stock".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int stock = Integer.parseInt(request.getParameter("stock"));

                if (stock < 0) {
                    throw new IllegalArgumentException(
                            "Stock quantity cannot be negative.");
                }

                message = pharmacy.updateStock(id, stock)
                        ? "Stock quantity updated successfully."
                        : "Medicine ID not found or invalid stock quantity.";

            } else {
                message = "Invalid action.";
            }

        } catch (NumberFormatException e) {
            message = "Enter valid numeric values.";
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }

        response.sendRedirect(
                request.getContextPath() + "/pharmacy?message="
                        + URLEncoder.encode(message, StandardCharsets.UTF_8));
    }

    private void validateMedicine(String name, String category,
                                  double price, int stock,
                                  String expiryDate) {
        if (name.isEmpty() || category.isEmpty()) {
            throw new IllegalArgumentException(
                    "Name and category cannot be empty.");
        }

        if (price <= 0 || stock < 0) {
            throw new IllegalArgumentException(
                    "Price must be positive and stock cannot be negative.");
        }

        if (expiryDate == null || expiryDate.isBlank()) {
            throw new IllegalArgumentException(
                    "Please select an expiry date.");
        }

        try {
            LocalDate.parse(expiryDate);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Please enter a valid expiry date.");
        }
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
