
package com.pharmacy;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/db-test")
public class DatabaseTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            List<Medicine> medicines =
                    new MedicineDAO().getAllMedicines();

            try (var out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h1>MySQL Connection Successful!</h1>");
                out.println("<h2>Medicines found: "
                        + medicines.size() + "</h2>");

                for (Medicine m : medicines) {
                    out.println("<p>" + m.getName()
                            + " | Stock: " + m.getStock()
                            + " | Price: " + m.getPrice()
                            + "</p>");
                }

                out.println("</body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            try (var out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h1>Database Test Failed</h1>");
                out.println("<pre>");
                out.println(e.getClass().getName());
                out.println(": " + e.getMessage());
                out.println("</pre>");
                out.println("</body></html>");
            }
        }
    }
}
