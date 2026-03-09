package com.setec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MyMethods {
    static Scanner cin = new Scanner(System.in);

    public static int showMenu() {
        System.out.println("\n--------- PRODUCT MANAGEMENT ---------");
        System.out.println("1. Add Product");
        System.out.println("2. Edit Product by ID");
        System.out.println("3. Delete Product by ID");
        System.out.println("4. Show All Products");
        System.out.println("5. Exit");
        printLine('-', 60);
        return inputInt("Choose (1-5): ", 1, 5);
    }

    public static int inputInt(String message, int min, int max) {
        int value;
        while (true) {
            try {
                System.out.print(message);
                String raw = cin.nextLine().trim();
                //reject "-0" explicitly
                if (raw.equals("-0")) {
                    System.out.println("Invalid input! Please enter a whole number.");
                    continue;
                }

                value = Integer.parseInt(raw);
                if (value < min || value > max) {
                    System.out.println("Value must be between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    public static double inputDouble(String message, double min) {
        double value;
        while (true) {
            try {
                System.out.print(message);
                value = Double.parseDouble(cin.nextLine().trim());
                if (value < min) {
                    System.out.println("Value must be >= " + min);
                } else {
                    return value;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static void addProduct(List<Product> products) {
        int n = inputInt("How many products to add? ", 1, 100);

        List<Integer> batchIds = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\nProduct № " + (i + 1));
            
            int id;
            while (true) {
                id = inputInt("Enter ID (1-999): ", 1, 999);
                boolean duplicate = false;
                for (Product existing : products) {
                    if (existing.getId() == id) {
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate && batchIds.contains(id)) {
                    duplicate = true;
                }
                if (duplicate) {
                    System.out.println("ID " + id + " already exists! Please enter a different ID.");
                } else {
                    batchIds.add(id);
                    break;
                }
            }
            // Validate non-empty name
            String name;
            do {
                System.out.print("Enter Name: ");
                name = cin.nextLine().trim();
                if (name.isEmpty()) System.out.println("Name cannot be empty!");
            } while (name.isEmpty());

            double price = inputDouble("Enter Price: $", 0.01);
            int qty = inputInt("Enter Qty: ", 0, 10000);
            products.add(new Product(id, name, price, qty));
            System.out.println("Product added successfully!");
        }
    }

    public static void editProduct(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products to edit!");
            return;
        }

        int id = inputInt("Enter ID to edit: ", 1, Integer.MAX_VALUE / 2);
        for (Product p : products) {
            if (p.getId() == id) {
                // Validate non-empty name
                String name;
                do {
                    System.out.print("New Name: ");
                    name = cin.nextLine().trim();
                    if (name.isEmpty()) System.out.println("Name cannot be empty!");
                } while (name.isEmpty());
                p.setName(name);
                p.setPrice(inputDouble("New Price: $", 0.01));
                p.setQty(inputInt("New Qty: ", 0, 10000));
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product ID " + id + " not found!");
    }

    public static void deleteProduct(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products to delete!");
            return;
        }

        int id = inputInt("Enter ID to delete: ", 1, Integer.MAX_VALUE / 2);
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getId() == id) {
                iterator.remove();
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        System.out.println("Product ID " + id + " not found!");
    }

    //blank line
    public static void printLine(char ch, int length) {
        System.out.println(String.valueOf(ch).repeat(length));
    }

    public static void showAllProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found!");
            return;
        }

        System.out.println();
        printLine('-', 60);
        System.out.printf("%-5s %-20s %-12s %-8s %-12s%n",
                "ID", "NAME", "PRICE ($)", "QTY", "AMOUNT ($)");
        printLine('-', 60);

        for (Product p : products) {
            System.out.println(p); //calls toString()
        }

        printLine('-', 60);
    }
}