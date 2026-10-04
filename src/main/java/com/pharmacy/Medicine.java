package com.pharmacy;

    public class Medicine {

        private int id;
        private String name;
        private String category;
        private double price;
        private int stock;
        private String expiryDate;

        public Medicine(int id, String name, String category,
                        double price, int stock, String expiryDate) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
            this.stock = stock;
            this.expiryDate = expiryDate;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }

        public String getExpiryDate() {
            return expiryDate;
        }
    }

