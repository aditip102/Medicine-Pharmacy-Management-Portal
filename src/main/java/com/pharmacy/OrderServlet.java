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

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    private List<Order> orders;

    @Override
    public void init() throws ServletException {

        orders = new ArrayList<>();

        // Medicines used for customer orders
        Medicine paracetamol = new Medicine(
                1,
                "Paracetamol",
                "Pain Relief",
                50.0,
                100,
                "2027-12-31"
        );

        Medicine amoxicillin = new Medicine(
                2,
                "Amoxicillin",
                "Antibiotic",
                120.0,
                50,
                "2027-06-30"
        );

        Medicine cetirizine = new Medicine(
                3,
                "Cetirizine",
                "Allergy",
                80.0,
                25,
                "2026-11-30"
        );

        // Add customer orders
        orders.add(
                new Order(
                        101,
                        "Rahul Sharma",
                        paracetamol,
                        2
                )
        );

        orders.add(
                new Order(
                        102,
                        "Priya Patil",
                        amoxicillin,
                        1
                )
        );

        orders.add(
                new Order(
                        103,
                        "Amit Kulkarni",
                        cetirizine,
                        3
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

        out.println("<title>Customer Orders</title>");

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

        // Order ID
        out.println(".order-id {");
        out.println("    font-weight: bold;");
        out.println("    color: #1769aa;");
        out.println("}");

        // Customer
        out.println(".customer {");
        out.println("    font-weight: bold;");
        out.println("}");

        // Quantity
        out.println(".quantity {");
        out.println("    font-weight: bold;");
        out.println("}");

        // Total
        out.println(".total {");
        out.println("    color: #15803d;");
        out.println("    font-weight: bold;");
        out.println("}");

        // Status
        out.println(".status {");
        out.println("    color: #15803d;");
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

        out.println("<h2>🛒 Customer Orders</h2>");

        out.println("<p>"
                + "View customer orders and their total purchase amounts."
                + "</p>");

        out.println("</div>");

        // Orders table
        out.println("<div class='table-container'>");

        out.println("<table>");

        out.println("<tr>");

        out.println("<th>Order ID</th>");

        out.println("<th>Customer Name</th>");

        out.println("<th>Medicine</th>");

        out.println("<th>Quantity</th>");

        out.println("<th>Unit Price</th>");

        out.println("<th>Total Price</th>");

        out.println("<th>Status</th>");

        out.println("</tr>");

        // Display orders
        for (Order order : orders) {

            Medicine medicine = order.getMedicine();

            out.println("<tr>");

            out.println("<td class='order-id'>"
                    + order.getOrderId()
                    + "</td>");

            out.println("<td class='customer'>"
                    + order.getCustomerName()
                    + "</td>");

            out.println("<td><strong>"
                    + medicine.getName()
                    + "</strong></td>");

            out.println("<td class='quantity'>"
                    + order.getQuantity()
                    + "</td>");

            out.println("<td>₹"
                    + medicine.getPrice()
                    + "</td>");

            out.println("<td class='total'>₹"
                    + order.getTotalPrice()
                    + "</td>");

            out.println("<td class='status'>"
                    + "🟢 Confirmed"
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