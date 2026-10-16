
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

        Medicine paracetamol = new Medicine(
                1, "Paracetamol", "Pain Relief",
                50.0, 100, "2027-12-31");

        Medicine amoxicillin = new Medicine(
                2, "Amoxicillin", "Antibiotic",
                120.0, 50, "2027-06-30");

        Medicine cetirizine = new Medicine(
                3, "Cetirizine", "Allergy",
                80.0, 25, "2026-11-30");

        // Existing sample orders
        orders.add(new Order(
                101, "Rahul Sharma", paracetamol, 2));

        orders.add(new Order(
                102, "Priya Patil", amoxicillin, 1));

        orders.add(new Order(
                103, "Amit Kulkarni", cetirizine, 3));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Customer Orders</title>");

        out.println("<style>");
        out.println("*{box-sizing:border-box}");
        out.println("body{margin:0;font-family:Arial,sans-serif;background:#f4f7fb;color:#1f2937}");
        out.println(".navbar{background:#1769aa;color:white;padding:20px 5%}");
        out.println(".container{width:90%;max-width:1200px;margin:40px auto}");
        out.println(".header{margin-bottom:25px}");
        out.println(".header p{color:#6b7280;margin-top:8px}");
        out.println(".table-container{background:white;padding:25px;border-radius:12px;box-shadow:0 4px 12px rgba(0,0,0,0.08);overflow-x:auto}");
        out.println("table{width:100%;border-collapse:collapse}");
        out.println("th{background:#1769aa;color:white;padding:14px;text-align:left}");
        out.println("td{padding:14px;border-bottom:1px solid #e5e7eb;vertical-align:top}");
        out.println("tr:hover{background:#f8fafc}");
        out.println(".order-id{font-weight:bold;color:#1769aa}");
        out.println(".total{color:#15803d;font-weight:bold}");
        out.println(".status{color:#15803d;font-weight:bold}");
        out.println(".back{display:inline-block;margin-top:25px;padding:12px 20px;background:#1769aa;color:white;text-decoration:none;border-radius:8px;margin-right:8px}");
        out.println("@media(max-width:700px){.container{width:95%}.table-container{padding:10px}th,td{padding:8px;font-size:13px}}");
        out.println("</style></head><body>");

        out.println("<div class='navbar'>");
        out.println("<h1>Medicine & Pharmacy Management System</h1>");
        out.println("</div>");

        out.println("<div class='container'>");
        out.println("<div class='header'>");
        out.println("<h2>Customer Orders</h2>");
        out.println("<p>View sample orders and confirmed orders placed through the cart.</p>");
        out.println("</div>");

        out.println("<div class='table-container'><table>");

        out.println("<tr>");
        out.println("<th>Order ID</th>");
        out.println("<th>Customer Name</th>");
        out.println("<th>Medicine</th>");
        out.println("<th>Quantity</th>");
        out.println("<th>Unit Price</th>");
        out.println("<th>Total Price</th>");
        out.println("<th>Status</th>");
        out.println("</tr>");

        // Display existing sample orders.
        for (Order order : orders) {
            Medicine medicine = order.getMedicine();

            out.println("<tr>");
            out.println("<td class='order-id'>"
                    + order.getOrderId() + "</td>");
            out.println("<td>"
                    + escapeHtml(order.getCustomerName()) + "</td>");
            out.println("<td>"
                    + escapeHtml(medicine.getName()) + "</td>");
            out.println("<td>" + order.getQuantity() + "</td>");
            out.println("<td>₹"
                    + String.format("%.2f", medicine.getPrice()) + "</td>");
            out.println("<td class='total'>₹"
                    + String.format("%.2f", order.getTotalPrice()) + "</td>");
            out.println("<td class='status'>Confirmed</td>");
            out.println("</tr>");
        }

        // Retrieve confirmed orders from the application context.
        @SuppressWarnings("unchecked")
        List<ConfirmedOrder> cartOrders =
                (List<ConfirmedOrder>) getServletContext()
                        .getAttribute("confirmedCartOrders");

        if (cartOrders != null) {
            synchronized (cartOrders) {
                for (ConfirmedOrder order : cartOrders) {

                    List<CartItem> items = order.getItems();

                    // One row per medicine, with a shared order ID.
                    for (CartItem item : items) {
                        out.println("<tr>");

                        out.println("<td class='order-id'>"
                                + escapeHtml(order.getOrderId()) + "</td>");

                        out.println("<td>"
                                + escapeHtml(order.getCustomerName()) + "</td>");

                        out.println("<td>"
                                + escapeHtml(item.getMedicineName()) + "</td>");

                        out.println("<td>" + item.getQuantity() + "</td>");

                        out.println("<td>₹"
                                + String.format("%.2f", item.getPrice())
                                + "</td>");

                        out.println("<td class='total'>₹"
                                + String.format("%.2f", item.getSubtotal())
                                + "</td>");

                        out.println("<td class='status'>"
                                + escapeHtml(order.getStatus()) + "</td>");

                        out.println("</tr>");
                    }
                }
            }
        }

        out.println("</table></div>");

        out.println("<a class='back' href='index.jsp'>Back to Dashboard</a>");
        out.println("<a class='back' href='cart'>Open Cart</a>");

        out.println("</div></body></html>");
    }

    private String escapeHtml(String value) {
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
