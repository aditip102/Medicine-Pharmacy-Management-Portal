
package com.pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/pharmacy_db";
    private static final String USER = "root";
    private static final String PASSWORD = "MySQL@123";

    public List<Medicine> getAllMedicines() throws SQLException {
        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT id, name, category, price, stock, expiry_date "
                + "FROM medicines";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }

        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Medicine medicine = new Medicine(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        resultSet.getString("expiry_date")
                );

                medicines.add(medicine);
            }
        }

        return medicines;
    }
}
