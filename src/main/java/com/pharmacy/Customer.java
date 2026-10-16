
package com.pharmacy;

public class Customer {
    private final String email;
    private String name;
    private String phone;
    private String address;
    private String passwordHash;

    public Customer(String name, String email, String phone,
                    String address, String passwordHash) {
        this.name = name;
        this.email = email.toLowerCase().trim();
        this.phone = phone;
        this.address = address;
        this.passwordHash = passwordHash;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getPasswordHash() { return passwordHash; }

    public void updateProfile(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
