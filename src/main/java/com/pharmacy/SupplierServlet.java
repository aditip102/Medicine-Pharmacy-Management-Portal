package com.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {

    private List<Supplier> suppliers;

    @Override
    public void init() throws ServletException {

        suppliers = new ArrayList<>();

        // Add suppliers
        suppliers.add(
                new Supplier(
                        1,
                        "ABC Pharma",
                        "9876543210",
                        "ABC Pharmaceuticals"
                )
        );

        suppliers.add(
                new Supplier(
                        2,
                        "Medico Suppliers",
                        "9876501234",
                        "Medico Healthcare"
                )
        );

        suppliers.add(
                new Supplier(
                        3,
                        "HealthCare Distributors",
                        "9823456789",
                        "HealthCare India Pvt. Ltd."
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

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>Supplier Management</title>");

        out.println("<style>");

        out.println("* { box-sizing: border-box; }");

        out.println("body {");
        out.println("    margin: 0;");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background: #f4f7fb;");
        out.println("    color: #1f2937;");
        out.println("}");

        // Navbar
        out.println(".navbar {");
        out.println("    background: #1769aa;");
        out.println("    color: white;");
        out.println("    padding: 20px 50px;");
        out.println("}");

        out.println(".navbar h1 {");
        out.println("    margin: 0;");
        out.println("    font-size: 25px;");
        out.println("}");

        // Container
        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 1200px;");
        out.println("    margin: 40px auto;");
        out.println("}");

        // Header
        out.println(".header {");
        out.println("    margin-bottom: 25px;");
        out.println("}");

        out.println(".header h2 {");
        out.println("    margin-bottom: 8px;");
        out.println("}");

        out.println(".header p {");
        out.println("    color: #6b7280;");
        out.println("}");

        // Table container
        out.println(".table-container {");
        out.println("    background: white;");
        out.println("    padding: 25px;");
        out.println("    border-radius: 12px;");
        out.println("    box-shadow: 0 4px 12px rgba(0,0,0,0.08);");
        out.println("    overflow-x: auto;");
        out.println("}");

        // Table
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

        // Contact styling
        out.println(".contact {");
        out.println("    color: #1769aa;");
        out.println("    font-weight: bold;");
        out.println("}");

        // Company styling
        out.println(".company {");
        out.println("    font-weight: bold;");
        out.println("}");

        // Back button
        out.println(".back {");
        out.println("    display: inline-block;");
        out.println("    margin-top: 25px;");
        out.println("    padding: 12px 20px;");
        out.println("    background: #1769aa;");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    border-radius: 8px;");
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

        out.println("<h2>🏭 Supplier Management</h2>");

        out.println("<p>"
                + "View and manage suppliers connected to the pharmacy."
                + "</p>");

        out.println("</div>");

        // Supplier table
        out.println("<div class='table-container'>");

        out.println("<table>");

        out.println("<tr>");

        out.println("<th>Supplier ID</th>");

        out.println("<th>Supplier Name</th>");

        out.println("<th>Contact</th>");

        out.println("<th>Company</th>");

        out.println("<th>Status</th>");

        out.println("</tr>");

        // Display suppliers
        for (Supplier supplier : suppliers) {

            out.println("<tr>");

            out.println("<td>"
                    + supplier.getSupplierId()
                    + "</td>");

            out.println("<td><strong>"
                    + supplier.getSupplierName()
                    + "</strong></td>");

            out.println("<td class='contact'>📞 "
                    + supplier.getContact()
                    + "</td>");

            out.println("<td class='company'>"
                    + supplier.getCompany()
                    + "</td>");

            out.println("<td style='color:#15803d;font-weight:bold;'>"
                    + "🟢 Active"
                    + "</td>");

            out.println("</tr>");
        }

        out.println("</table>");

        out.println("</div>");

        // Back button
        out.println("<a class='back' href='index.jsp'>"
                + "← Back to Dashboard"
                + "</a>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}