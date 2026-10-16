
package com.pharmacy;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerStore {
    private static final Map<String, Customer> customers =
            new ConcurrentHashMap<>();

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(
                    password.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Password hashing failed.", e);
        }
    }

    public static boolean register(Customer customer) {
        return customers.putIfAbsent(
                customer.getEmail(), customer) == null;
    }

    public static Customer find(String email) {
        if (email == null) return null;
        return customers.get(email.toLowerCase().trim());
    }

    public static boolean authenticate(String email, String password) {
        Customer customer = find(email);
        return customer != null && MessageDigest.isEqual(
                customer.getPasswordHash().getBytes(StandardCharsets.UTF_8),
                hashPassword(password).getBytes(StandardCharsets.UTF_8));
    }
}
