package com.pharmacy;

public class Supplier {

    private int supplierId;
    private String supplierName;
    private String contact;
    private String company;

    public Supplier(int supplierId, String supplierName,
                    String contact, String company) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contact = contact;
        this.company = company;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getContact() {
        return contact;
    }

    public String getCompany() {
        return company;
    }
}