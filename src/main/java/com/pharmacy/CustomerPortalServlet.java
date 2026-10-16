
package com.pharmacy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {
        "/register", "/login", "/logout",
        "/customer-dashboard", "/profile", "/my-orders"
})
public class CustomerPortalServlet extends HttpServlet {

    private String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    private void pageStart(PrintWriter out, String title) {
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<title>" + title + "</title><style>");
        out.println("*{box-sizing:border-box}body{margin:0;background:#f4f7fb;font-family:Arial;color:#1f2937}");
        out.println("header{background:#1769aa;color:white;padding:22px 5%}");
        out.println("main{max-width:900px;width:92%;margin:35px auto}");
        out.println(".panel{background:white;padding:25px;margin:20px 0;border-radius:12px;box-shadow:0 3px 12px #0001}");
        out.println("input,textarea{display:block;width:100%;padding:12px;margin:10px 0 16px;border:1px solid #cbd5e1;border-radius:6px}");
        out.println("button,.btn{display:inline-block;background:#1769aa;color:white;padding:11px 16px;border:0;border-radius:6px;text-decoration:none;cursor:pointer;margin:4px 4px 4px 0}");
        out.println("table{width:100%;border-collapse:collapse}th,td{text-align:left;padding:12px;border-bottom:1px solid #ddd}");
        out.println(".message{color:#b91c1c}.success{color:#15803d}.muted{color:#64748b}");
        out.println("</style></head><body><header><h1>Medicine & Pharmacy Management System</h1></header><main>");
    }

    private void pageEnd(PrintWriter out) {
        out.println("</main></body></html>");
    }

    private void link(PrintWriter out, String url, String label) {
        out.println("<a class='btn' href='" + url + "'>" + label + "</a>");
    }

    private Customer currentCustomer(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;
        Object email = session.getAttribute("customerEmail");
        return email == null ? null : CustomerStore.find(email.toString());
    }

    private boolean requireLogin(HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException {
        if (currentCustomer(request) == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String path = request.getServletPath();
        String ctx = request.getContextPath();

        if ("/logout".equals(path)) {
            HttpSession session = request.getSession(false);
            if (session != null) session.invalidate();
            response.sendRedirect(ctx + "/login?loggedout=1");
            return;
        }

        if ("/register".equals(path)) {
            pageStart(out, "Customer Registration");
            out.println("<div class='panel'><h2>Create Customer Account</h2>");
            out.println("<form method='post' action='register'>");
            out.println("Full Name<input name='name' required maxlength='100'>");
            out.println("Email<input type='email' name='email' required>");
            out.println("Phone<input name='phone' required maxlength='20'>");
            out.println("Address<textarea name='address' required maxlength='300'></textarea>");
            out.println("Password<input type='password' name='password' required minlength='8'>");
            out.println("<button type='submit'>Register</button></form>");
            link(out, ctx + "/login", "Already registered? Login");
            out.println("</div>");
            pageEnd(out);
            return;
        }

        if ("/login".equals(path)) {
            pageStart(out, "Customer Login");
            out.println("<div class='panel'><h2>Customer Login</h2>");
            if ("1".equals(request.getParameter("loggedout"))) {
                out.println("<p class='success'>You have logged out successfully.</p>");
            }
            String error = request.getParameter("error");
            if (error != null) {
                out.println("<p class='message'>"
                        + esc(error.equals("1")
                        ? "Invalid email or password." : "Please log in first.")
                        + "</p>");
            }
            out.println("<form method='post' action='login'>");
            out.println("Email<input type='email' name='email' required>");
            out.println("Password<input type='password' name='password' required>");
            out.println("<button type='submit'>Login</button></form>");
            link(out, ctx + "/register", "Create Account");
            link(out, ctx + "/index.jsp", "Main Dashboard");
            out.println("</div>");
            pageEnd(out);
            return;
        }

        if (!requireLogin(request, response)) return;

        Customer customer = currentCustomer(request);

        if ("/customer-dashboard".equals(path)) {
            pageStart(out, "Customer Dashboard");
            out.println("<div class='panel'><h2>Welcome, "
                    + esc(customer.getName()) + "!</h2>");
            out.println("<p>Manage your account and view your medicine orders.</p>");
            link(out, ctx + "/profile", "My Profile");
            link(out, ctx + "/my-orders", "My Orders");
            link(out, ctx + "/cart", "Shop Medicines");
            link(out, ctx + "/logout", "Logout");
            out.println("</div>");
            pageEnd(out);
            return;
        }

        if ("/profile".equals(path)) {
            pageStart(out, "Customer Profile");
            out.println("<div class='panel'><h2>My Profile</h2>");
            String message = request.getParameter("saved");
            if ("1".equals(message)) {
                out.println("<p class='success'>Profile updated successfully.</p>");
            }
            out.println("<form method='post' action='profile'>");
            out.println("Full Name<input name='name' value='"
                    + esc(customer.getName()) + "' required>");
            out.println("Email<input value='" + esc(customer.getEmail())
                    + "' disabled>");
            out.println("Phone<input name='phone' value='"
                    + esc(customer.getPhone()) + "' required>");
            out.println("Address<textarea name='address' required>"
                    + esc(customer.getAddress()) + "</textarea>");
            out.println("<button type='submit'>Update Profile</button></form>");
            link(out, ctx + "/customer-dashboard", "Back to Dashboard");
            link(out, ctx + "/logout", "Logout");
            out.println("</div>");
            pageEnd(out);
            return;
        }

        if ("/my-orders".equals(path)) {
            pageStart(out, "My Orders");
            out.println("<div class='panel'><h2>My Orders</h2>");
            out.println("<p class='muted'>Orders placed under your registered name.</p>");
            out.println("<div style='overflow-x:auto'><table>");
            out.println("<tr><th>Order ID</th><th>Medicine</th><th>Quantity</th><th>Unit Price</th><th>Total</th><th>Status</th></tr>");

            @SuppressWarnings("unchecked")
            List<ConfirmedOrder> orders =
                    (List<ConfirmedOrder>) getServletContext()
                            .getAttribute("confirmedCartOrders");

            boolean found = false;

            if (orders != null) {
                synchronized (orders) {
                    for (ConfirmedOrder order : orders) {
                        if (order.getCustomerName() != null &&
                                order.getCustomerName().equalsIgnoreCase(
                                        customer.getName())) {
                            for (CartItem item : order.getItems()) {
                                found = true;
                                out.println("<tr><td>" + esc(order.getOrderId())
                                        + "</td><td>" + esc(item.getMedicineName())
                                        + "</td><td>" + item.getQuantity()
                                        + "</td><td>₹" + String.format("%.2f", item.getPrice())
                                        + "</td><td>₹" + String.format("%.2f", item.getSubtotal())
                                        + "</td><td>" + esc(order.getStatus())
                                        + "</td></tr>");
                            }
                        }
                    }
                }
            }

            if (!found) {
                out.println("<tr><td colspan='6'>No confirmed orders found for your name yet.</td></tr>");
            }

            out.println("</table></div>");
            link(out, ctx + "/customer-dashboard", "Back to Dashboard");
            link(out, ctx + "/cart", "Shop Medicines");
            out.println("</div>");
            pageEnd(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String path = request.getServletPath();
        String ctx = request.getContextPath();

        if ("/register".equals(path)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String password = request.getParameter("password");

            if (name == null || email == null || phone == null ||
                    address == null || password == null ||
                    name.trim().isEmpty() || email.trim().isEmpty() ||
                    phone.trim().isEmpty() || address.trim().isEmpty() ||
                    password.length() < 8) {
                response.sendRedirect(ctx + "/register");
                return;
            }

            Customer customer = new Customer(
                    name.trim(), email.trim(), phone.trim(),
                    address.trim(), CustomerStore.hashPassword(password));

            if (!CustomerStore.register(customer)) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                pageStart(out, "Registration");
                out.println("<div class='panel'><p class='message'>An account with this email already exists.</p>");
                link(out, ctx + "/register", "Try Again");
                link(out, ctx + "/login", "Login");
                out.println("</div>");
                pageEnd(out);
                return;
            }

            response.sendRedirect(ctx + "/login");
            return;
        }

        if ("/login".equals(path)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (CustomerStore.authenticate(email, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("customerEmail", email.toLowerCase().trim());
                session.setMaxInactiveInterval(30 * 60);
                response.sendRedirect(ctx + "/customer-dashboard");
            } else {
                response.sendRedirect(ctx + "/login?error=1");
            }
            return;
        }

        if ("/profile".equals(path)) {
            Customer customer = currentCustomer(request);
            if (customer == null) {
                response.sendRedirect(ctx + "/login?error=2");
                return;
            }

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            if (name != null && phone != null && address != null &&
                    !name.trim().isEmpty() && !phone.trim().isEmpty() &&
                    !address.trim().isEmpty()) {
                customer.updateProfile(name.trim(), phone.trim(), address.trim());
            }

            response.sendRedirect(ctx + "/profile?saved=1");
        }
    }
}
