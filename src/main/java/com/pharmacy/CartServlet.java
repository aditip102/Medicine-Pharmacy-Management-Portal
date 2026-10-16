
package com.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static class Product {
        int id;
        String name;
        double price;
        int stock;

        Product(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    private static final Map<Integer, Product> inventory =
            new LinkedHashMap<>();

    static {
        inventory.put(1, new Product(1, "Paracetamol", 50.0, 100));
        inventory.put(2, new Product(2, "Amoxicillin", 120.0, 50));
        inventory.put(3, new Product(3, "Cetirizine", 80.0, 25));
        inventory.put(4, new Product(4, "Azithromycin", 150.0, 8));
        inventory.put(5, new Product(5, "Ibuprofen", 65.0, 40));
        inventory.put(6, new Product(6, "Omeprazole", 90.0, 35));
        inventory.put(7, new Product(7, "Metformin", 75.0, 60));
        inventory.put(8, new Product(8, "Vitamin C", 110.0, 45));
        inventory.put(9, new Product(9, "ORS", 25.0, 80));
        inventory.put(10, new Product(10, "Loratadine", 70.0, 30));
        inventory.put(11, new Product(11, "Pantoprazole", 95.0, 20));
        inventory.put(12, new Product(12, "Diclofenac", 85.0, 18));
        inventory.put(13, new Product(13, "Multivitamin", 140.0, 55));
        inventory.put(14, new Product(14, "Calcium Tablets", 100.0, 32));
        inventory.put(15, new Product(15, "Cough Syrup", 95.0, 22));
        inventory.put(16, new Product(16, "Antacid Tablets", 45.0, 6));
        inventory.put(17, new Product(17, "Nasal Saline Spray", 85.0, 28));
        inventory.put(18, new Product(18, "Antiseptic Solution", 75.0, 15));
        inventory.put(19, new Product(19, "Bandages", 30.0, 50));
        inventory.put(20, new Product(20, "Zinc Tablets", 60.0, 9));
    }

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    @SuppressWarnings("unchecked")
    private List<String> getOrders(HttpSession session) {
        List<String> orders =
                (List<String>) session.getAttribute("cartOrders");

        if (orders == null) {
            orders = new ArrayList<>();
            session.setAttribute("cartOrders", orders);
        }

        return orders;
    }

    @SuppressWarnings("unchecked")
    private List<ConfirmedOrder> getConfirmedOrders() {
        ServletContext context = getServletContext();

        synchronized (context) {
            List<ConfirmedOrder> orders =
                    (List<ConfirmedOrder>) context.getAttribute(
                            "confirmedCartOrders");

            if (orders == null) {
                orders = new ArrayList<>();
                context.setAttribute("confirmedCartOrders", orders);
            }

            return orders;
        }
    }

    private String esc(String value) {
        if (value == null) return "";

        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        List<CartItem> cart = getCart(session);
        List<String> orders = getOrders(session);

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html lang='en'><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<title>Cart and Orders</title>");

        out.println("<style>");
        out.println("*{box-sizing:border-box}");
        out.println("body{margin:0;font-family:Arial,sans-serif;background:#f4f7fb;color:#1f2937}");
        out.println(".navbar{background:#1769aa;color:white;padding:20px 5%}");
        out.println(".container{width:94%;max-width:1200px;margin:30px auto}");
        out.println(".panel{background:white;padding:22px;margin-bottom:22px;border-radius:12px;box-shadow:0 3px 12px #00000010}");
        out.println("h2{margin-top:0}");
        out.println("table{width:100%;border-collapse:collapse;min-width:650px}");
        out.println(".table-wrap{overflow-x:auto}");
        out.println("th,td{text-align:left;padding:12px;border-bottom:1px solid #e5e7eb}");
        out.println("th{background:#eaf4ff}");
        out.println("input{padding:10px;border:1px solid #cbd5e1;border-radius:6px;max-width:100%;margin:3px}");
        out.println("button,.btn{display:inline-block;padding:10px 14px;border:0;border-radius:6px;background:#1769aa;color:white;text-decoration:none;cursor:pointer;margin:3px}");
        out.println(".remove{background:#dc2626}.green{background:#15803d}.muted{color:#64748b}");
        out.println(".total{text-align:right;font-size:20px;font-weight:bold;margin-top:20px}");
        out.println("@media(max-width:650px){table{font-size:13px}th,td{padding:8px}.panel{padding:12px}}");
        out.println("</style></head><body>");

        out.println("<div class='navbar'><h1>💊 Medicine & Pharmacy Management System</h1>");
        out.println("<p>Cart, checkout and order history</p></div>");

        out.println("<div class='container'>");
        out.println("<p><a class='btn' href='index.jsp'>Dashboard</a>");
        out.println("<a class='btn' href='pharmacy'>Medicine Management</a>");
        out.println("<a class='btn' href='orders'>Customer Orders</a></p>");

        String message = (String) session.getAttribute("cartMessage");

        if (message != null) {
            out.println("<div class='panel'>" + esc(message) + "</div>");
            session.removeAttribute("cartMessage");
        }

        // Available medicines
        out.println("<div class='panel'><h2>Available Medicines (20)</h2>");
        out.println("<p class='muted'>Select a medicine and quantity to add it to your cart.</p>");
        out.println("<div class='table-wrap'><table>");
        out.println("<tr><th>ID</th><th>Medicine</th><th>Price</th><th>Stock</th><th>Add to Cart</th></tr>");

        synchronized (inventory) {
            for (Product p : inventory.values()) {
                out.println("<tr><td>" + p.id + "</td>");
                out.println("<td>" + esc(p.name) + "</td>");
                out.println("<td>₹" + String.format("%.2f", p.price) + "</td>");
                out.println("<td>" + p.stock + "</td><td>");

                if (p.stock > 0) {
                    out.println("<form method='post' action='cart'>");
                    out.println("<input type='hidden' name='action' value='add'>");
                    out.println("<input type='hidden' name='medicineId' value='" + p.id + "'>");
                    out.println("<input type='number' name='quantity' min='1' max='" + p.stock + "' value='1' required style='width:70px'>");
                    out.println("<button type='submit'>Add</button></form>");
                } else {
                    out.println("Out of stock");
                }

                out.println("</td></tr>");
            }
        }

        out.println("</table></div></div>");

        // Cart
        out.println("<div class='panel'><h2>Your Cart</h2>");

        if (cart.isEmpty()) {
            out.println("<p class='muted'>Your cart is empty. Add a medicine above.</p>");
        } else {
            out.println("<div class='table-wrap'><table>");
            out.println("<tr><th>Medicine</th><th>Unit Price</th><th>Quantity</th><th>Subtotal</th><th>Action</th></tr>");

            double total = 0;

            for (CartItem item : cart) {
                total += item.getSubtotal();

                out.println("<tr><td>" + esc(item.getMedicineName()) + "</td>");
                out.println("<td>₹" + String.format("%.2f", item.getPrice()) + "</td>");
                out.println("<td><form method='post' action='cart'>");
                out.println("<input type='hidden' name='action' value='update'>");
                out.println("<input type='hidden' name='medicineId' value='" + item.getMedicineId() + "'>");
                out.println("<input type='number' name='quantity' min='1' value='" + item.getQuantity() + "' required style='width:70px'>");
                out.println("<button type='submit'>Update</button></form></td>");
                out.println("<td>₹" + String.format("%.2f", item.getSubtotal()) + "</td>");
                out.println("<td><form method='post' action='cart'>");
                out.println("<input type='hidden' name='action' value='remove'>");
                out.println("<input type='hidden' name='medicineId' value='" + item.getMedicineId() + "'>");
                out.println("<button class='remove' type='submit'>Remove</button></form></td></tr>");
            }

            out.println("</table></div>");
            out.println("<div class='total'>Total: ₹" + String.format("%.2f", total) + "</div>");

            out.println("<form method='post' action='cart' style='text-align:right;margin-top:15px'>");
            out.println("<input type='hidden' name='action' value='checkout'>");
            out.println("<input type='text' name='customerName' placeholder='Enter customer name' required>");
            out.println("<button class='green' type='submit'>Confirm Order</button>");
            out.println("</form>");
        }

        out.println("</div>");

        // Session history
        out.println("<div class='panel'><h2>Order History</h2>");

        if (orders.isEmpty()) {
            out.println("<p class='muted'>No orders placed in this session yet.</p>");
        } else {
            out.println("<div class='table-wrap'><table><tr><th>Order Details</th></tr>");

            for (String order : orders) {
                out.println("<tr><td>" + esc(order) + "</td></tr>");
            }

            out.println("</table></div>");
        }

        out.println("</div></div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        List<CartItem> cart = getCart(session);
        String action = request.getParameter("action");

        try {
            if ("add".equals(action) || "update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("medicineId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                if (quantity < 1) {
                    throw new IllegalArgumentException("Quantity must be at least 1.");
                }

                Product product;

                synchronized (inventory) {
                    product = inventory.get(id);

                    if (product == null) {
                        throw new IllegalArgumentException("Medicine not found.");
                    }

                    int requested = quantity;

                    if ("add".equals(action)) {
                        for (CartItem item : cart) {
                            if (item.getMedicineId() == id) {
                                requested += item.getQuantity();
                                break;
                            }
                        }
                    }

                    if (requested > product.stock) {
                        throw new IllegalArgumentException(
                                "Only " + product.stock + " units of "
                                        + product.name + " are available.");
                    }
                }

                CartItem existing = null;

                for (CartItem item : cart) {
                    if (item.getMedicineId() == id) {
                        existing = item;
                        break;
                    }
                }

                if (existing != null) {
                    existing.setQuantity("add".equals(action)
                            ? existing.getQuantity() + quantity : quantity);
                } else {
                    synchronized (inventory) {
                        product = inventory.get(id);

                        cart.add(new CartItem(
                                product.id, product.name,
                                product.price, quantity));
                    }
                }

                session.setAttribute("cartMessage", "Cart updated successfully.");

            } else if ("remove".equals(action)) {
                int id = Integer.parseInt(request.getParameter("medicineId"));

                cart.removeIf(item -> item.getMedicineId() == id);

                session.setAttribute("cartMessage", "Medicine removed from cart.");

            } else if ("checkout".equals(action)) {
                if (cart.isEmpty()) {
                    throw new IllegalArgumentException("Your cart is empty.");
                }

                String customerName = request.getParameter("customerName");

                if (customerName == null || customerName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Please enter the customer name.");
                }

                double total = 0;
                StringBuilder details = new StringBuilder();

                synchronized (inventory) {
                    // Validate stock before deducting anything.
                    for (CartItem item : cart) {
                        Product p = inventory.get(item.getMedicineId());

                        if (p == null || item.getQuantity() > p.stock) {
                            throw new IllegalArgumentException(
                                    "Insufficient stock for "
                                            + item.getMedicineName() + ".");
                        }
                    }

                    for (CartItem item : cart) {
                        Product p = inventory.get(item.getMedicineId());

                        p.stock -= item.getQuantity();
                        total += item.getSubtotal();

                        if (details.length() > 0) details.append(", ");

                        details.append(item.getMedicineName())
                                .append(" x ")
                                .append(item.getQuantity());
                    }
                }

                List<ConfirmedOrder> confirmedOrders = getConfirmedOrders();
                String orderId;

                synchronized (confirmedOrders) {
                    // Three predefined sample orders have IDs 101–103.
                    orderId = String.valueOf(104 + confirmedOrders.size());

                    ConfirmedOrder confirmedOrder = new ConfirmedOrder(
                            orderId, customerName.trim(), cart, total);

                    confirmedOrders.add(confirmedOrder);
                }

                getOrders(session).add(
                        "Order " + orderId + " — " + details
                                + " — Total ₹" + String.format("%.2f", total)
                                + " — Confirmed");

                cart.clear();

                session.setAttribute(
                        "cartMessage",
                        "Order confirmed successfully! Order ID: " + orderId);

            } else {
                throw new IllegalArgumentException("Invalid action.");
            }

        } catch (NumberFormatException e) {
            session.setAttribute("cartMessage", "Please enter a valid quantity.");

        } catch (IllegalArgumentException e) {
            session.setAttribute("cartMessage", e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
