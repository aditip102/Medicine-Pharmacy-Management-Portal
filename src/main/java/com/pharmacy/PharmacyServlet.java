package com.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/pharmacy")
public class PharmacyServlet extends HttpServlet {

    private Pharmacy pharmacy;

    @Override
    public void init() throws ServletException {

        pharmacy = new Pharmacy();

        // Add medicines to the pharmacy

        pharmacy.addMedicine(
                new Medicine(
                        1,
                        "Paracetamol",
                        "Pain Relief",
                        50.0,
                        100,
                        "2027-12-31"
                )
        );

        pharmacy.addMedicine(
                new Medicine(
                        2,
                        "Amoxicillin",
                        "Antibiotic",
                        120.0,
                        50,
                        "2027-06-30"
                )
        );

        pharmacy.addMedicine(
                new Medicine(
                        3,
                        "Cetirizine",
                        "Allergy",
                        80.0,
                        25,
                        "2026-11-30"
                )
        );

        pharmacy.addMedicine(
                new Medicine(
                        4,
                        "Azithromycin",
                        "Antibiotic",
                        150.0,
                        8,
                        "2026-10-20"
                )
        );
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

        out.println("<title>Medicine Management</title>");

        out.println("<style>");

        out.println("* { box-sizing: border-box; }");

        out.println("html { scroll-behavior: smooth; }");

        out.println("body {");
        out.println("    margin: 0;");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background: #f4f7fb;");
        out.println("    color: #1f2937;");
        out.println("}");

        out.println(".navbar {");
        out.println("    background: #1769aa;");
        out.println("    color: white;");
        out.println("    padding: 20px 50px;");
        out.println("}");

        out.println(".navbar h1 {");
        out.println("    margin: 0;");
        out.println("    font-size: 25px;");
        out.println("}");

        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 1200px;");
        out.println("    margin: 40px auto;");
        out.println("}");

        out.println(".header {");
        out.println("    margin-bottom: 25px;");
        out.println("}");

        out.println(".header h2 {");
        out.println("    margin-bottom: 8px;");
        out.println("}");

        out.println(".header p {");
        out.println("    color: #6b7280;");
        out.println("}");

        out.println(".table-container {");
        out.println("    background: white;");
        out.println("    padding: 25px;");
        out.println("    border-radius: 12px;");
        out.println("    box-shadow: 0 4px 12px rgba(0,0,0,0.08);");
        out.println("    overflow-x: auto;");
        out.println("}");

        out.println("table {");
        out.println("    width: 100%;");
        out.println("    border-collapse: collapse;");
        out.println("}");

        out.println("th {");
        out.println("    background: #1769aa;");
        out.println("    color: white;");
        out.println("    padding: 14px;");
        out.println("    text-align: left;");
        out.println("}");

        out.println("td {");
        out.println("    padding: 14px;");
        out.println("    border-bottom: 1px solid #e5e7eb;");
        out.println("}");

        out.println("tr:hover {");
        out.println("    background: #f8fafc;");
        out.println("}");

        out.println(".stock-good {");
        out.println("    color: #15803d;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".stock-low {");
        out.println("    color: #dc2626;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".back {");
        out.println("    display: inline-block;");
        out.println("    margin-top: 25px;");
        out.println("    padding: 12px 20px;");
        out.println("    background: #1769aa;");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    border-radius: 8px;");
        out.println("}");

        out.println(".back:hover {");
        out.println("    background: #12598f;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        // Navbar

        out.println("<div class='navbar'>");

        out.println("<h1>🏥 Pharmacy Management Portal</h1>");

        out.println("</div>");


        out.println("<div class='container'>");


        // Header

        out.println("<div class='header'>");

        out.println("<h2>💊 Available Medicines</h2>");

        out.println("<p>View medicines currently available in the pharmacy inventory.</p>");

        out.println("</div>");


        // ================= SEARCH SECTION =================

        out.println("<div id='search'>");

        out.println("<form method='get' action='pharmacy' style='margin-bottom:25px;'>");

        out.println(
                "<input type='text' name='search' placeholder='Enter medicine name...'"
                        + " style='padding:12px;width:300px;border:1px solid #d1d5db;"
                        + "border-radius:8px;font-size:15px;'>"
        );

        out.println(
                "<button type='submit'"
                        + " style='padding:12px 20px;margin-left:10px;"
                        + "background:#1769aa;color:white;border:none;"
                        + "border-radius:8px;cursor:pointer;'>"
        );

        out.println("🔍 Search");

        out.println("</button>");

        out.println("</form>");

        out.println("</div>");


        // ================= STOCK SECTION =================

        out.println("<div id='stock'>");

        out.println("<form method='get' action='pharmacy' style='margin-bottom:25px;'>");

        out.println(
                "<input type='text' name='stockCheck' "
                        + "placeholder='Enter medicine to check stock...'"
                        + " style='padding:12px;width:300px;border:1px solid #d1d5db;"
                        + "border-radius:8px;font-size:15px;'>"
        );

        out.println(
                "<button type='submit'"
                        + " style='padding:12px 20px;margin-left:10px;"
                        + "background:#15803d;color:white;border:none;"
                        + "border-radius:8px;cursor:pointer;'>"
        );

        out.println("📦 Check Stock");

        out.println("</button>");

        out.println("</form>");

        out.println("</div>");


        // ================= REQUEST PARAMETERS =================

        String search = request.getParameter("search");

        Medicine searchedMedicine = null;

        String stockCheck = request.getParameter("stockCheck");

        Medicine stockMedicine = null;


        // Search medicine

        if (search != null && !search.trim().isEmpty()) {

            searchedMedicine = pharmacy.searchMedicine(search.trim());

        }


        // Check stock

        if (stockCheck != null && !stockCheck.trim().isEmpty()) {

            stockMedicine = pharmacy.searchMedicine(stockCheck.trim());

        }


        // ================= STOCK RESULT =================

        if (stockCheck != null && !stockCheck.trim().isEmpty()) {

            out.println(
                    "<div style='background:white;padding:20px;"
                            + "border-radius:10px;margin-bottom:20px;"
                            + "box-shadow:0 4px 12px rgba(0,0,0,0.08);'>"
            );

            if (stockMedicine == null) {

                out.println(
                        "<h3 style='color:#dc2626;'>"
                                + "❌ Medicine not found</h3>"
                );

            } else if (pharmacy.checkStock(stockCheck.trim())) {

                out.println(
                        "<h3 style='color:#15803d;'>"
                                + "✅ " + stockMedicine.getName()
                                + " is in stock</h3>"
                );

                out.println(
                        "<p>Available quantity: <strong>"
                                + stockMedicine.getStock()
                                + "</strong></p>"
                );

            } else {

                out.println(
                        "<h3 style='color:#dc2626;'>"
                                + "⚠️ " + stockMedicine.getName()
                                + " is out of stock</h3>"
                );
            }

            out.println("</div>");
        }


        // ================= EXPIRY TRACKING =================

        out.println(
                "<div id='expiry' class='table-container' "
                        + "style='margin-bottom:25px;'>"
        );

        out.println("<h2 style='margin-top:0;'>⏰ Expiry Tracking</h2>");

        out.println(
                "<p style='color:#6b7280;margin-bottom:20px;'>"
                        + "Monitor medicine expiry dates and identify medicines "
                        + "that need attention.</p>"
        );

        out.println("<table>");

        out.println("<tr>");

        out.println("<th>Medicine</th>");

        out.println("<th>Expiry Date</th>");

        out.println("<th>Status</th>");

        out.println("</tr>");


        LocalDate today = LocalDate.now();


        for (Medicine medicine : pharmacy.getMedicines()) {

            LocalDate expiryDate =
                    LocalDate.parse(medicine.getExpiryDate());

            long daysRemaining =
                    ChronoUnit.DAYS.between(today, expiryDate);

            String status;

            String statusColor;


            if (daysRemaining < 0) {

                status = "🔴 Expired";

                statusColor = "#dc2626";

            } else if (daysRemaining <= 30) {

                status = "🟠 Expiring Soon";

                statusColor = "#ea580c";

            } else {

                status = "🟢 Safe";

                statusColor = "#15803d";
            }


            out.println("<tr>");

            out.println(
                    "<td><strong>"
                            + medicine.getName()
                            + "</strong></td>"
            );

            out.println(
                    "<td>"
                            + medicine.getExpiryDate()
                            + "</td>"
            );

            out.println(
                    "<td style='color:" + statusColor
                            + ";font-weight:bold;'>"
                            + status
                            + "</td>"
            );

            out.println("</tr>");
        }


        out.println("</table>");

        out.println("</div>");


        // ================= LOW STOCK ALERTS =================

        out.println(
                "<div id='lowstock' class='table-container' "
                        + "style='margin-bottom:25px;'>"
        );

        out.println("<h2 style='margin-top:0;'>⚠️ Low Stock Alerts</h2>");

        out.println(
                "<p style='color:#6b7280;margin-bottom:20px;'>"
                        + "Medicines with stock of 10 or less require restocking."
                        + "</p>"
        );


        boolean lowStockFound = false;


        out.println("<table>");

        out.println("<tr>");

        out.println("<th>Medicine</th>");

        out.println("<th>Category</th>");

        out.println("<th>Current Stock</th>");

        out.println("<th>Status</th>");

        out.println("</tr>");


        for (Medicine medicine : pharmacy.getMedicines()) {

            if (medicine.getStock() <= 10) {

                lowStockFound = true;

                out.println("<tr>");

                out.println(
                        "<td><strong>"
                                + medicine.getName()
                                + "</strong></td>"
                );

                out.println(
                        "<td>"
                                + medicine.getCategory()
                                + "</td>"
                );

                out.println(
                        "<td style='color:#dc2626;font-weight:bold;'>"
                                + medicine.getStock()
                                + "</td>"
                );

                out.println(
                        "<td style='color:#dc2626;font-weight:bold;'>"
                                + "⚠️ Low Stock - Restock Required"
                                + "</td>"
                );

                out.println("</tr>");
            }
        }


        if (!lowStockFound) {

            out.println("<tr>");

            out.println(
                    "<td colspan='4' style='text-align:center;"
                            + "padding:25px;color:#15803d;font-weight:bold;'>"
                            + "✅ All medicines have sufficient stock."
                            + "</td>"
            );

            out.println("</tr>");
        }


        out.println("</table>");

        out.println("</div>");


        // ================= MEDICINE MANAGEMENT =================

        out.println(
                "<div id='medicines' class='table-container'>"
        );

        out.println("<table>");

        out.println("<tr>");

        out.println("<th>ID</th>");

        out.println("<th>Medicine</th>");

        out.println("<th>Category</th>");

        out.println("<th>Price</th>");

        out.println("<th>Stock</th>");

        out.println("<th>Expiry Date</th>");

        out.println("<th>Days Remaining</th>");

        out.println("</tr>");


        // ================= SEARCH RESULT =================

        if (search != null && !search.trim().isEmpty()) {

            if (searchedMedicine != null) {

                Medicine medicine = searchedMedicine;

                String stockClass =
                        medicine.getStock() <= 10
                                ? "stock-low"
                                : "stock-good";


                // Calculate days remaining

                LocalDate expiryDate =
                        LocalDate.parse(medicine.getExpiryDate());

                long daysRemaining =
                        ChronoUnit.DAYS.between(
                                LocalDate.now(),
                                expiryDate
                        );


                out.println("<tr>");

                out.println(
                        "<td>"
                                + medicine.getId()
                                + "</td>"
                );

                out.println(
                        "<td><strong>"
                                + medicine.getName()
                                + "</strong></td>"
                );

                out.println(
                        "<td>"
                                + medicine.getCategory()
                                + "</td>"
                );

                out.println(
                        "<td>₹"
                                + medicine.getPrice()
                                + "</td>"
                );

                out.println(
                        "<td class='" + stockClass + "'>"
                                + medicine.getStock()
                                + "</td>"
                );

                out.println(
                        "<td>"
                                + medicine.getExpiryDate()
                                + "</td>"
                );

                out.println("<td>");

                if (daysRemaining < 0) {

                    out.println(
                            "<span style='color:#dc2626;font-weight:bold;'>"
                                    + "Expired"
                                    + "</span>"
                    );

                } else {

                    out.println(daysRemaining + " days");
                }

                out.println("</td>");

                out.println("</tr>");

            } else {

                out.println("<tr>");

                out.println(
                        "<td colspan='7' style='text-align:center;"
                                + "padding:25px;color:#dc2626;font-weight:bold;'>"
                );

                out.println("❌ Medicine not found");

                out.println("</td>");

                out.println("</tr>");
            }

        } else {

            // Display all medicines

            for (Medicine medicine : pharmacy.getMedicines()) {

                String stockClass =
                        medicine.getStock() <= 10
                                ? "stock-low"
                                : "stock-good";


                // Calculate days remaining

                LocalDate expiryDate =
                        LocalDate.parse(medicine.getExpiryDate());

                long daysRemaining =
                        ChronoUnit.DAYS.between(
                                LocalDate.now(),
                                expiryDate
                        );


                out.println("<tr>");

                out.println(
                        "<td>"
                                + medicine.getId()
                                + "</td>"
                );

                out.println(
                        "<td><strong>"
                                + medicine.getName()
                                + "</strong></td>"
                );

                out.println(
                        "<td>"
                                + medicine.getCategory()
                                + "</td>"
                );

                out.println(
                        "<td>₹"
                                + medicine.getPrice()
                                + "</td>"
                );

                out.println(
                        "<td class='" + stockClass + "'>"
                                + medicine.getStock()
                                + "</td>"
                );

                out.println(
                        "<td>"
                                + medicine.getExpiryDate()
                                + "</td>"
                );

                out.println("<td>");

                if (daysRemaining < 0) {

                    out.println(
                            "<span style='color:#dc2626;font-weight:bold;'>"
                                    + "Expired"
                                    + "</span>"
                    );

                } else {

                    out.println(daysRemaining + " days");
                }

                out.println("</td>");

                out.println("</tr>");
            }
        }


        out.println("</table>");

        out.println("</div>");


        // ================= BACK BUTTON =================

        out.println(
                "<a class='back' href='index.jsp'>"
                        + "← Back to Dashboard"
                        + "</a>"
        );


        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}